package com.nio.websocket.engin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.nio.websocket.IEventListener;
import com.nio.websocket.ServerEngin;
import com.nio.websocket.event.EventType;

public abstract class AbstractServerEngin implements ServerEngin {
	
	private volatile static Boolean isStartServer;
	
	private static Object startLocker = new Object();
	
	private ServerSocketChannel serverSocketChannel;
	
	private Selector selector;
	
	/**
	 * 事件监听器
	 */
	private Set<IEventListener> eventListenerSet = new HashSet<IEventListener>();

	public void startServer(Integer port) {

		if(isStartServer == null || !isStartServer){
			synchronized(startLocker){
				if(isStartServer == null || !isStartServer){
					if(isStartServer == null || !isStartServer){
						try {
							selector = Selector.open();
							serverSocketChannel = ServerSocketChannel.open();
							serverSocketChannel.configureBlocking(false);
							serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
							SocketAddress address = new InetSocketAddress(port);
							serverSocketChannel.bind(address);
							doStart(selector);
							isStartServer = true;
						} catch (IOException e) {
							e.printStackTrace();
							System.out.println("Server start failed!");
							stopServer();
						}
					}
				}
			}
		}
	}
	
	public void doStart(Selector selector){
		if(selector != null){
			ServerRunner serverRunner = new ServerRunner();
			new Thread(serverRunner).start();
		}
	}
	
	class ServerRunner implements Runnable{
//		private Selector runnerSelector;
//		public ServerRunner(Selector runnerSelector){
//			this.runnerSelector = runnerSelector;
//		}
		public void run() {
//			final Selector localSelector = runnerSelector;
			while(isStartServer){
				int select = 0;
				try {
					select = selector.select();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(select > 0){
					dispatchHandler(selector);
				}
			}
		}
		
		/**
		 * 事件分发处理程序
		 * @param selector
		 * @throws IOException 
		 */
		private void dispatchHandler(final Selector selector){
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIt = keys.iterator();
			SelectionKey selectionKey = null;
			try{
				while(keyIt.hasNext()){
					selectionKey = keyIt.next();
					keyIt.remove();
					if(selectionKey.isConnectable()){
						doFireEvent(EventType.CONNECTED,selectionKey);
						System.out.println("Nio 已连接事件！");
					}else if(selectionKey.isAcceptable()){
						doFireEvent(EventType.ACCEPT,selectionKey);
						System.out.println("Nio 可接收事件！");
					}else{
						if(selectionKey.isReadable()){
							doFireEvent(EventType.READ,selectionKey);
							System.out.println("Nio 读事件！");
						}else if(selectionKey.isWritable()){
							SocketChannel channel = (SocketChannel) selectionKey.channel();
							writeMessage(channel);
							System.out.println("Nio 写事件！");
							//取消写事件
							selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_WRITE));
							selectionKey.interestOps(SelectionKey.OP_READ);
						}else if(selectionKey.isValid()){
							doFireEvent(EventType.VALID,selectionKey);
							System.out.println("Nio 通道已关闭或事件已取消！");
						}
					}
				}
			}catch(IOException e){
				System.out.println("信道出现IO异常，关闭信道！");
				if(selectionKey != null){
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					try {
						channel.close();
					} catch (IOException e1) {
					}
					selectionKey.cancel();
				}
			}
		}
		
		private void doFireEvent(EventType eventType,SelectionKey selectionKey) throws IOException{
			Iterator<IEventListener> iterator = eventListenerSet.iterator();
			while(iterator.hasNext()){
				IEventListener listener = iterator.next();
				switch(eventType){
				case CONNECTED :
					listener.fireConnectedEvent(selectionKey);
					break;
				case ACCEPT :
					ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socket = channel.accept();
					if(socket != null){
						socket.configureBlocking(false);
						socket.register(selector, SelectionKey.OP_READ);
						listener.fireAcceptEvent(selectionKey);
					}
					break;
				case READ :
					listener.fireReadEvent(selectionKey);
					break;
				case WRITE :
//					listener.fireWriteEvent(selectionKey);
					break;
				case VALID :
					listener.fireValidEvent();
					break;
				default :
					break;
				}
			}
		}
	}

	public void stopServer() {
		if(isStartServer){
			synchronized(startLocker){
				if(isStartServer){
					try {
						isStartServer = false;
						if(serverSocketChannel.isOpen()) serverSocketChannel.close();
						if(selector.isOpen()) selector.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 注册时间监听器，此方法同步
	 * @param listener
	 */
	public synchronized void registeEventLister(IEventListener listener){
		eventListenerSet.add(listener);
	}
	
	/**
	 * 写消息
	 * @param buffer
	 */
	public abstract void writeMessage(SocketChannel channel);
	
	public Selector getSelector() {
		return selector;
	};
	
	public ServerSocketChannel getServerSocketChannel(){
		return serverSocketChannel;
	}
	
}

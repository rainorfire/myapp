package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

public class NioClient {
	
	private SocketChannel channel;
	Selector selector ;
	SocketAddress address;
	
	LinkedBlockingDeque<String> writeQueue = new LinkedBlockingDeque<String>();

	public NioClient(){
		init();
	}
	
	public void init(){
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		try {
			selector = Selector.open();
			channel = SocketChannel.open();
			address = new InetSocketAddress("127.0.0.1",9096);
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_WRITE);
			channel.connect(address);
			
			if(channel.finishConnect() ){
//				byteBuffer.clear();
//				byteBuffer.put("客户端准备发送数据。。。".getBytes());
//				byteBuffer.flip();
//				channel.write(byteBuffer);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while(iterator.hasNext()){
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					if(selectionKey.isValid() && selectionKey.isConnectable()){
						System.out.println("连接服务器完成！！！");
					}else if(selectionKey.isValid() && selectionKey.isAcceptable()){
						selectionKey.interestOps(SelectionKey.OP_READ);
						System.out.println("accept就绪！！！");
					}else if(selectionKey.isValid() && selectionKey.isReadable()){
//						selectionKey.interestOps(SelectionKey.OP_WRITE);
						System.out.println("read就绪！！！");
					}
				}
				
				new Thread(new ClientWriteRunner()).start();
//				byteBuffer.clear();
//				byteBuffer.put("Hello Test!".getBytes());
//				channel.write(byteBuffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class ClientWriteRunner implements Runnable{
		private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
		public void run() {
			while(channel.isOpen()){
				try {
					String str = writeQueue.take();
					if(str != null){
						writeBuffer.clear();
						System.out.println("准备发送数据为："+str);
						writeBuffer.put(str.getBytes());
						writeBuffer.flip();
						while(writeBuffer.hasRemaining()){
							int writeLength = channel.write(writeBuffer);
							System.out.println("写入信道数据长度-->"+writeLength);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void sendData(String msg){
		if(msg != null)
			writeQueue.add(msg);
	}
	
	public static void main(String[] args) {
		NioClient client = new NioClient();
		client.sendData("你好，2017...");
		client.sendData("UUM发来贺电");
	}
}

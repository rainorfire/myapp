package com.nio.websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.stereotype.Component;

import com.test.util.SHA1Utils;

@Component
public class WebSocketServer {
	
	private Selector selector ;
	
	private volatile Boolean isStart;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	private static Object startLocker = new Object();
	
	private static Map<String,SocketChannel> clientMap = new ConcurrentHashMap<String,SocketChannel>();

	/**
	 * @param port
	 */
	public void startServer(Integer port){
		try {
			if(isStart == null || !isStart){
				synchronized(startLocker){
					
					if(isStart == null || !isStart){
						System.out.println("start websocket server....");
						selector = Selector.open();
						ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
						serverSocketChannel.configureBlocking(false);
						serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
						SocketAddress address = new InetSocketAddress(port);
						serverSocketChannel.bind(address);
						isStart = true;
						ThreadPoolExecutor newFixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
						newFixedThreadPool.execute(new Runnable() {
							public void run() {
								try {
									polling();
								} catch (IOException e) {
									e.printStackTrace();
									System.out.println("start websocket server failed!");
								}
							}
						});
						System.out.println("finish websocket server!");
					}
				}
				latch.await();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void polling() throws IOException{
		while(isStart){
			if(latch.getCount() > 0)
				latch.countDown();
			int select = selector.select();
			if(select == 0)
				continue;
			
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIt = keys.iterator();
			while(keyIt.hasNext()){
				SelectionKey selectionKey = keyIt.next();
				if(selectionKey.isAcceptable()){
					acceptHandler(selectionKey);
				}else if(selectionKey.isReadable()){
					readHandler(selectionKey);
				}else if(selectionKey.isWritable()){
					System.out.println("Nio 写事件！");
				}
				keyIt.remove();
			}
		}
	}
	
	private void acceptHandler(SelectionKey selectionKey) throws IOException{
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
		try {
			SocketChannel accept = serverSocketChannel.accept();
			if(accept != null){
				if(!accept.isConnected()){
					System.out.println("该连接已断开！！");
					return;
				}
				accept.configureBlocking(false);
				accept.register(selector, SelectionKey.OP_READ);
				
				SocketAddress address = accept.getRemoteAddress();
				clientMap.put("127.0.0.1", accept);
			}
			System.out.println("Already accept message...");
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}
	
	private void readHandler(SelectionKey selectionKey) throws IOException{
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		SocketChannel channel = (SocketChannel) selectionKey.channel();
		int read = 0;
		String sb = "";
		while((read = channel.read(readBuffer)) > 0){
			if(readBuffer.hasRemaining()){
				sb += new String(readBuffer.array());
			}
		}
		if(sb.length() > 0){
			if (sb.contains("HTTP/1.1")) {
				System.out.println("WS : " + sb);
				/**处理协议*/
				ByteBuffer buffer = processProtocol(sb);
				writeHttp(channel, buffer);
			}
		}
	}
	
	/**
	 * 连接时处理协议
	 * @param request
	 * @return
	 */
	private ByteBuffer processProtocol(String request) {

		/**Sec-WebSocket-Key*/
		int keyindex=request.indexOf("Sec-WebSocket-Key:");
		String key=request.substring(keyindex+19,keyindex+43);
		System.out.println("Sec-WebSocket-Key:"+key);
		
		/**计算结果*/
		String new_key=key+"258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		byte[] key_sha1=SHA1Utils.SHA1(new_key);
		String result=new String(Base64.getEncoder().encode(key_sha1));
		
		/**返回协议*/
		StringBuilder sb = new StringBuilder("HTTP/1.1 101 Switching Protocols\r\n");
		sb.append("Upgrade: websocket\r\n");
		sb.append("Connection: Upgrade\r\n");
		sb.append("Sec-WebSocket-Accept:"+result+"\r\n\r\n");

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(sb.toString().getBytes());
		buffer.flip();
		System.out.println(sb.toString());
		return buffer;
	}
	
	public void writeHttp(SocketChannel currChannel,ByteBuffer buffer){
		try {
			currChannel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeMsg(String clientAddress,String msg){
		SocketChannel socketChannel = clientMap.get(clientAddress);
		try {
			socketChannel.write(ByteBuffer.wrap(packData(msg)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopServer(){
		if(isStart){
			synchronized(startLocker){
				if(isStart){
					isStart = false;
				}
			}
		}
	}
	
	/**
	 * 服务器发送的数据以0x81开头，紧接发送内容的长度（若长度在0-125，则1个byte表示长度；
	 * 若长度不超过0xFFFF，则后2个byte 作为无符号16位整数表示长度；
	 * 若超过0xFFFF，则后8个byte作为无符号64位整数表示长度），最后是内容的byte数组。
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static byte[] packData(String message) throws UnsupportedEncodingException
	{
	    byte[] contentBytes = null;
	    byte[] temp = message.getBytes("UTF-8");

	    if(temp.length < 126)
	    {
	        contentBytes = new byte[temp.length + 2];
	        contentBytes[0] = (byte) 0x81;
	        contentBytes[1] = (byte)temp.length;
	        System.arraycopy(temp, 0, contentBytes, 2, temp.length);
	    }
	    else if(temp.length < 0xFFFF)
	    {
	        contentBytes = new byte[temp.length + 4];
	        contentBytes[0] = (byte) 0x81;
	        contentBytes[1] = 126;
	        contentBytes[2] = (byte)(temp.length & 0xFF);
	        contentBytes[3] = (byte)(temp.length >> 8 & 0xFF);
	        System.arraycopy(temp, 0, contentBytes, 4, temp.length);
	    }
	    else
	    {
	        // 暂不处理超长内容
	    }

	    return contentBytes;
	}
	
}

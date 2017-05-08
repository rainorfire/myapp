package com.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MySocketServer {
	public static void main(String[] args) {
		
		MySocketServer mySocketServer = new MySocketServer();
		ServerSocketChannel ssc = mySocketServer.createSocketChannel(8080);
		
		try {
			while(true){
				SocketChannel socketChannel = ssc.accept();
				boolean isConned = socketChannel.isConnected();
				if(isConned){
//					byte[] byteArray = new byte[516];
//					Socket socket = socketChannel.socket();
//					InputStream is = socket.getInputStream();
//					int i = is.read(byteArray);
//					while(i>0){
//						String str = new String(byteArray,"UTF-8");
//						System.out.println(str);
//						i = is.read(byteArray);
//					}
//					
//					is.close();
//					socket.close();
					
					ByteBuffer buffRead = ByteBuffer.allocate(1024);
					long readSize = socketChannel.read(buffRead);
					while(readSize != -1){
						buffRead.flip();
						while(buffRead.hasRemaining()){
							char bt = (char) buffRead.get();
							System.out.print(bt);
						}
						buffRead.clear();
						readSize = socketChannel.read(buffRead);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	protected ServerSocketChannel createSocketChannel(int port){
		ServerSocketChannel ssc = null;
		try {
			ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("create SocketChannel failed.");
		}
		return ssc;
	}
	
	protected void readSocketChannel(ServerSocketChannel ssc){
		StringBuilder sb = new StringBuilder();
		try {
			while(true){
				SocketChannel socketChannel = ssc.accept();
				boolean isConned = socketChannel.isConnected();
				if(isConned){
					ByteBuffer buffRead = ByteBuffer.allocate(1024);
					long readSize = socketChannel.read(buffRead);
					while(readSize != -1){
						buffRead.flip();
						while(buffRead.hasRemaining()){
							char bt = (char) buffRead.get();
//							System.out.print(bt);
							sb.append(bt);
						}
						buffRead.clear();
						readSize = socketChannel.read(buffRead);
					}
					
					printFun(sb.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void printFun(String str){
		System.out.println(str);
	}
}

package com.nio.websocket.engin;

import java.net.ServerSocket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class WebsocketServerEngin extends AbstractServerEngin {
	
	@Override
	public void writeMessage(SocketChannel channel) {
		ServerSocketChannel ssc = getServerSocketChannel();
		Selector selector = getSelector();
		ServerSocket serverSocket = ssc.socket();
//		serverSocket.register(selector, ops)
//		serverSocket.
	}
	
}

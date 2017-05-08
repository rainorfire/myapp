package com.nio.websocket;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface Event {
	
	void onConnected(ServerSocketChannel serverChannel);
	
	void onAccept(ServerSocketChannel channel);

	void onRead(byte[] byteArray);
	
	void onWrite(SocketChannel channel);
	
	void onValid();
}

package com.nio.websocket.event;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.nio.websocket.Event;

public abstract class AbstractEvent implements Event {

	public void onConnected(ServerSocketChannel serverChannel) {
	}

	public void onAccept(ServerSocketChannel channel) {
	}

	public void onRead(byte[] byteArray) {
	}

	public void onWrite(SocketChannel channel) {
	}

	public void onValid() {
	}

}

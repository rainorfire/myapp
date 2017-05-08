package com.nio.websocket.listener;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import com.nio.websocket.Event;
import com.nio.websocket.IEventListener;

public class EventListener implements IEventListener {

	private static Object registeLocker = new Object();
	private List<Event> eventRegisterList = new ArrayList<Event>();
	public void registerEvent(Event event) {
		synchronized(registeLocker){
			eventRegisterList.add(event);
		}
	}

	public void fireConnectedEvent(SelectionKey selectionKey) {
		ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
		for(Event e:eventRegisterList){
			e.onConnected(channel);
		}
	}

	public void fireAcceptEvent(SelectionKey selectionKey) {
		ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
		for(Event e:eventRegisterList){
			e.onAccept(channel);
		}
	}

	public void fireReadEvent(SelectionKey selectionKey) {
		String str = "";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel channel = (SocketChannel) selectionKey.channel();
		try {
			while(channel.read(buffer) > 0){
				if(buffer.hasRemaining()){
					byte[] array = buffer.array();
					str += new String(array);
				}
				buffer.clear();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			try {
				channel.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		for(Event e:eventRegisterList){
			e.onRead(str.getBytes());
		}
	}

	public void fireWriteEvent(SelectionKey selectionKey) {
		SocketChannel channel = (SocketChannel) selectionKey.channel();
		for(Event e:eventRegisterList){
			e.onWrite(channel);
		}
	}

	public void fireValidEvent() {
		for(Event e:eventRegisterList){
			e.onValid();
		}
	}

}

package com.nio.websocket;

import java.nio.channels.SelectionKey;

public interface IEventListener {

	void registerEvent(Event event);
	
	void fireConnectedEvent(SelectionKey selectionKey);
	
	void fireAcceptEvent(SelectionKey selectionKey);
	
	void fireReadEvent(SelectionKey selectionKey);
	
	void fireWriteEvent(SelectionKey selectionKey);
	
	void fireValidEvent();
}

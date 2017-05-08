package com.nio.websocket.event;

public class ReadEvent extends AbstractEvent{

	@Override
	public void onRead(byte[] byteArray) {
		String string = new String(byteArray);
		System.out.println("触发读事件！！-->"+string);
	}

}

package com.nio.websocket.event;

import java.nio.channels.ServerSocketChannel;

public class AcceptEvent extends AbstractEvent{

	@Override
	public void onAccept(ServerSocketChannel channel) {
		super.onAccept(channel);
		System.out.println("已经准备好了接收数据！！！");
	}

}

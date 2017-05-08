package com.test.jms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.test.websocket.WebSocketHandlerCustom;

public class MqMessageReceiveDelegation implements MessageListener{
	
	@Resource
	private WebSocketHandlerCustom webSocketHandlerCustom;
	
	public void onMessage(Message msg) {
		TextMessage txtMsg = (TextMessage)msg;
		try {
			String txt = txtMsg.getText();
			webSocketHandlerCustom.sendTextMsg(txt);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

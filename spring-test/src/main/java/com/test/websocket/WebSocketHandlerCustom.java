package com.test.websocket;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonParser;
import com.test.util.JsonUtil;

public class WebSocketHandlerCustom implements WebSocketHandler {
	
	public static final String WEB_SOCKET_ENDPOINT_URL_PATTERNER = "/admin/webSocketHandler/{serverId}/{sessionId}/websocket";
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	public static Map<String,WebSocketSession> sessionMap = new HashMap<String,WebSocketSession>();

	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		URI uri = session.getUri();
		String uriPath = uri.getPath();
		Map<String, String> paramMap = antPathMatcher.extractUriTemplateVariables(WEB_SOCKET_ENDPOINT_URL_PATTERNER, uriPath);
		String userId = paramMap.get("serverId");
		System.out.println("2Connection Established....."+uriPath);
		sessionMap.put(userId, session);
	}

	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		System.out.println("Handle Message....");
	}



	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		
	}



	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("Connection Closed..................");
	}



	public boolean supportsPartialMessages() {
		return false;
	}
	
	public void sendTextMsg(String msg){
		try {
			Map<String,String> jsonMap = JsonUtil.json2Map(msg, String.class, String.class);
			String userId = jsonMap.get("userId");
			WebSocketSession session = sessionMap.get(userId);
			
			org.springframework.web.socket.TextMessage socketMsg = new org.springframework.web.socket.TextMessage(msg);
			if(session.isOpen()){
				session.sendMessage(socketMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

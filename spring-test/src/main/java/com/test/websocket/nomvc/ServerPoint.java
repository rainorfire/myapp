package com.test.websocket.nomvc;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

@ServerEndpoint(value = "/admin/webSocketHandler", configurator = SpringConfigurator.class)
public class ServerPoint {
	  private final EchoService echoService;  
	   
	  @Autowired  
	  public ServerPoint(EchoService echoService) {  
	    this.echoService = echoService;  
	  }  
	   
	  @OnMessage  
	  public void handleMessage(Session session, String message) {  
	    // ...  
		  System.out.println(message+"---------");
	  }  
}

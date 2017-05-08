package com.test.websocket.nomvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class ServerPointConfig {
//	@Bean  
//    public ServerPoint echoEndpoint() {  
//		return new ServerPoint(echoService());  
//    }  
	
    @Bean  
    public EchoService echoService() {  
    	return new EchoService();
    }  
   
    @Bean  
    public ServerEndpointExporter endpointExporter() {  
    	return new ServerEndpointExporter();  
    }  
}

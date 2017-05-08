package com.test.websocket.nomvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.socket.sockjs.SockJsService;
import org.springframework.web.socket.sockjs.support.SockJsHttpRequestHandler;
import org.springframework.web.socket.sockjs.transport.handler.DefaultSockJsService;

import com.test.websocket.WebSocketHandlerCustom;

@Configuration
public class WebConfig {
	@Bean  
	public SimpleUrlHandlerMapping handlerMapping() {  
   
	    SockJsService sockJsService = new DefaultSockJsService(taskScheduler());  
	   
	    Map<String, Object> urlMap = new HashMap<String, Object>();  
	    urlMap.put("/admin/webSocketHandler/**", new SockJsHttpRequestHandler(sockJsService, new WebSocketHandlerCustom()));  
	   
	    SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();  
	    hm.setUrlMap(urlMap);  
	    return hm;  
	}  
	
	@Bean  
    public ThreadPoolTaskScheduler taskScheduler() {
	    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();  
	    taskScheduler.setThreadNamePrefix("SockJS-");  
	    return taskScheduler;  
    }  
}

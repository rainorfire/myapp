package com.test.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nio.websocket.IEventListener;
import com.nio.websocket.WebSocketServer;
import com.nio.websocket.engin.WebsocketServerEngin;
import com.nio.websocket.event.ReadEvent;
import com.nio.websocket.listener.EventListener;

@Controller
@RequestMapping("/admin")
public class WebsocketServerController {
//	@Autowired
//	private WebSocketServer ws;
	
	private WebsocketServerEngin ws = new WebsocketServerEngin();

	@RequestMapping("/wbsocket-server/start")
	public ModelAndView webSocketServerStart(HttpServletRequest request){
		ws.startServer(9094);
		ReadEvent event = new ReadEvent();
		IEventListener listener = new EventListener();
		listener.registerEvent(event);
		ws.registeEventLister(listener);
		return new ModelAndView("/websocket.jsp");
	}
	
	@ResponseBody
	@RequestMapping("/wbsocket-server/write")
	public String webSocketServerStartWrite(HttpServletRequest request){
		String remoteAddr = "127.0.0.1";
//		remoteAddr = remoteAddr + ":" + request.getRemotePort();
		String msg = request.getParameter("msg");
		try {
			msg = new String(msg.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		ws.writeMsg(remoteAddr, msg);
		return "发送信息";
	}
	
	@RequestMapping("/wbsocket-server/stop")
	public ModelAndView webSocketServerStop(HttpServletRequest request){
		if(ws != null){
			ws.stopServer();
		}
		return new ModelAndView("/websocket.jsp");
	}
}

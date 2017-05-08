package com.test.controllers;

import javax.annotation.PostConstruct;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.beans.Human;

@Controller
//@MessageMapping("/stomp-test")
public class StompController {
	
	@PostConstruct
	public void init(){
		System.out.println("init stomp fun ------------------------");
	}
	
//	@MessageMapping("/test")
	@RequestMapping("/test")
	public Human sendMsg(){
		Human hm = new Human();
		hm.setAge(13);
		hm.setName("Haha");
		return hm;
	}
}

package com.spring.xml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.AntPathMatcher;

public class SpringParseXML {

	@SuppressWarnings("unchecked")
	public void parseXML(String path){
		Resource resource = new FileSystemResource(path);
		@SuppressWarnings("deprecation")
		BeanFactory factory = new XmlBeanFactory(resource);
		AntPathMatcher bean = (AntPathMatcher) factory.getBean("pathMatcher");
		System.out.println(bean);
	}
	
	public static void main(String[] args) {
		SpringParseXML xx = new SpringParseXML();
		xx.parseXML("E:/Zoom File/GitHub/repository/myproject/myproject/src/main/resources/spring_websocket_stomp.xml");
	}
}

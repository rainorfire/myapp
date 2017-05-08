package com.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
	
	private Object proxyTarget;
	
	public ProxyHandler(Object proxyTarget){
		this.proxyTarget = proxyTarget;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		System.out.println("Befor do proxy...");
		
		Object obj = method.invoke(proxyTarget, args);
		
		System.out.println("After do proxy...");
		
		return obj;
	}

}

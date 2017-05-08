package com.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		IPeople iPeople = (IPeople) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IPeople.class}, 
				new InvocationHandler() {
					
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println(proxy.getClass().getName() + "  "+ method.getName() + "  " + args);
						PeopleImpl peopleImpl = new PeopleImpl();
						method.invoke(peopleImpl, args);
						return null;
					}
				});
		
		iPeople.sayHello(" proxy");
	}
	
}

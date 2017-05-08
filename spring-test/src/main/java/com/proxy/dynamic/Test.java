package com.proxy.dynamic;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

public class Test {

	public static void main(String[] args) {
		ConcreteSubject concreteSubject = new ConcreteSubject();
		ProxyHandler proxyHandler = new ProxyHandler(concreteSubject);
		Subject proxySubject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(), 
								new Class[]{Subject.class}, 
								proxyHandler);
		
		proxySubject.doSomething();
		
		createProxyClassFile();
		
		System.out.println(Integer.toBinaryString(-1));
	}
	
	 public static void createProxyClassFile()   
	  {   
	    String name = "ProxySubject";   
	    byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );   
	    try  
	    {   
	      FileOutputStream out = new FileOutputStream( name + ".class" );   
	      out.write( data );   
	      out.close();   
	    }   
	    catch( Exception e )   
	    {   
	      e.printStackTrace();   
	    }   
	  }   
	
}

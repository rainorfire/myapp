package com.test.hashmap;

import java.util.HashMap;
import java.util.UUID;

public class HashMapInfiniteLoopTest {

	public static void main(String[] args) {
		
		final HashMap<String, String> map = new HashMap<String, String>(2);
		 
        for (int i = 0; i < 10000; i++) {
 
            new Thread(new Runnable() {
            	public void run() {
            		System.out.println(UUID.randomUUID().toString());
                    map.put(UUID.randomUUID().toString(), "");
            	}
            }, "线程Thread-" + i).start();
 
        }
        
//        for (int i = 0; i < 10000; i++) {
//        	 
//        	System.out.println(UUID.randomUUID().toString());
//            map.put(UUID.randomUUID().toString(), "");
// 
//        }

	}

}

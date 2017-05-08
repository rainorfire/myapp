package com.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestThreadPool {

	public static void main(String[] args) {
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3);
		ThreadPoolExecutor tpe = new ThreadPoolExecutorExt(3, 5, 0, TimeUnit.SECONDS, workQueue,new ThreadPoolExecutor.CallerRunsPolicy());
		
		for(int i = 0 ; i < 121; i++){
			TestThread tt = new TestThread();
			Thread thread = new Thread(tt);
			thread.setName("thread:"+i);
			tpe.execute(thread);
		}
		
		
		
//		ExecutorService es = Executors.newFixedThreadPool(3);
//		for(int i = 0 ; i < 3; i++){
//			TestThread tt = new TestThread();
//			Thread thread = new Thread(tt);
//			thread.setName("thread:"+i);
//			es.submit(thread);
//		}
//		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("线程池最后线程数："+tpe.getPoolSize());
//		
//		TestThread tt = new TestThread();
//		Thread thread = new Thread(tt);
//		thread.setName("thread:1312312");
//		es.submit(thread);
	}

}

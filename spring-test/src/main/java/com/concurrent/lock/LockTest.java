package com.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition1 = lock.newCondition();
	
	private Condition condition2 = lock.newCondition();
	
	public static void main(String[] args) {
		LockTest lockTest = new LockTest();
		Thread thread1 = new Thread(lockTest.new LockTest1());
		Thread thread2 = new Thread(lockTest.new LockTest2());
		
		thread1.start();
		thread2.start();
		
	}
	
	class LockTest1 implements Runnable{

		public void run() {
			lock.lock();
			
			System.out.println("LockTest1 执行并拿到锁");
			try {
				condition1.await();
				
				System.out.println("LockTest1 拿到一个信号");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			lock.unlock();
		}
		
	}
	
	class LockTest2 implements Runnable{

		public void run() {
			lock.lock();
			
			try {
				System.out.println("LockTest2 执行，沉睡3s...");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("LockTest2 拿到锁");
			condition1.signalAll();
			System.out.println("LockTest2 唤醒线程");
			
			lock.unlock();
		}
		
	}
	
}

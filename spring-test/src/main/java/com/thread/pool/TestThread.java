package com.thread.pool;

public class TestThread implements Runnable {

	public void run() {
		Thread currThread = Thread.currentThread();
		System.out.println("currThread name = " + currThread.getName());

	}

}

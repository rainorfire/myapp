package com.test.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	
	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("timer task execute 1 time.");
				
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 1000, 2000);
	}

}

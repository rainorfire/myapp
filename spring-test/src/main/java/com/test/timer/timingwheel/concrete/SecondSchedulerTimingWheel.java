package com.test.timer.timingwheel.concrete;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.test.timer.timingwheel.WheelCaller;
import com.test.timer.timingwheel.concrete.timing.AbstractConcreteTempTimingTask;
import com.test.timer.timingwheel.concrete.timing.AbstractTimingConcreteTempTaskHandler;

public class SecondSchedulerTimingWheel extends AbstractTimingConcreteTempTaskHandler{
	
	public SecondSchedulerTimingWheel() {
		this(60, 1, TimeUnit.SECONDS);
	}
	
	public SecondSchedulerTimingWheel(Integer ticketDuration) {
		this(60, ticketDuration, TimeUnit.SECONDS);
	}
	
	public SecondSchedulerTimingWheel(Integer ticketPerWheel, Integer ticketDuration) {
		this(ticketPerWheel, ticketDuration, TimeUnit.SECONDS);
	}

	private SecondSchedulerTimingWheel(Integer ticketPerWheel, Integer ticketDuration, TimeUnit timeUnit) {
		super(ticketPerWheel, ticketDuration, timeUnit);
	}

	@Override
	public void beforeStartWheel() {
		
	}

	@Override
	public void beforeStopWheel() {
		
	}

	@Override
	public void roundWeek(WheelCaller wheelCaller) {
		wheelCaller.doCaller();
	}
	
	@Override
	public void startWheel() {
		Date nowDate = new Date();
		Integer seconds = nowDate.getSeconds();
		this.setWheelCurrIndex(seconds);
		super.startWheel();
	}

	@Override
	public void putTimingTask(AbstractConcreteTempTimingTask task) {
		Date nowDate = new Date();
		Date currDate = task.getCurrDate();
		Date startExecuteDate = task.getStartExecuteDate();
		long startExecuteTime = startExecuteDate.getTime();
		long currTime = currDate.getTime();
		long interval = startExecuteTime - currTime;	//间隔毫秒数
		if(interval < 0){
			System.out.println("定时任务开始执行时间不能小于任务设置时间");
			throw new IllegalArgumentException("定时任务开始执行时间不能小于任务设置时间");
		}
		if((startExecuteTime - nowDate.getTime()) >= Long.valueOf((this.getTicketPerWheel() * 1000))){
			System.out.println("设置秒轮定时任务，只能在一个周期内，时间差为="+interval);
			throw new IllegalArgumentException("设置秒轮定时任务，只能在一个周期内");
		}
		
		Integer nextIndex = startExecuteDate.getSeconds();
		System.out.println("秒轮上当前下标："+getWheelCurrIndex()+",目标槽下标："+nextIndex);
		this.addTask(nextIndex, task);
	}

}

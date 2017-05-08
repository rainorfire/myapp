package com.test.timer.timingwheel.concrete;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.test.timer.timingwheel.TimingWheelTask;
import com.test.timer.timingwheel.WheelCaller;
import com.test.timer.timingwheel.concrete.timing.AbstractConcreteTempTimingTask;
import com.test.timer.timingwheel.concrete.timing.AbstractTimingConcreteTempTaskHandler;

public class MinuteSchedulerTimingWheel extends AbstractTimingConcreteTempTaskHandler {
	
	private SecondSchedulerTimingWheel scondTimingWheel;
	
	public MinuteSchedulerTimingWheel(){
		this(60, 1, TimeUnit.MINUTES);
	}
	
	public MinuteSchedulerTimingWheel(Integer ticketDuration){
		this(60, ticketDuration, TimeUnit.MINUTES);
	}

	private MinuteSchedulerTimingWheel(Integer ticketPerWheel, Integer ticketDuration, TimeUnit timeUnit) {
		super(ticketPerWheel, ticketDuration, timeUnit);
		scondTimingWheel = new SecondSchedulerTimingWheel();
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
		Integer minutes = nowDate.getMinutes();
		this.setWheelCurrIndex(minutes);
		scondTimingWheel.registeRoundWheelCaller(new WheelCaller(){

			@Override
			public void doCaller() {
				
				Integer minutesCurrIndex = MinuteSchedulerTimingWheel.this.getWheelCurrIndex();
				Integer minutesTicketPerWheel = MinuteSchedulerTimingWheel.this.getTicketPerWheel();
				
				if(minutesCurrIndex == minutesTicketPerWheel){
					MinuteSchedulerTimingWheel.this.roundWeek(MinuteSchedulerTimingWheel.this.getRoundWheelCaller());
				}
				
				MinuteSchedulerTimingWheel.this.setWheelCurrIndex(++minutesCurrIndex);
				
				Integer tmpMinutesCurrIndex = minutesCurrIndex % minutesTicketPerWheel;
				
				MinuteSchedulerTimingWheel.this.doTimingWheelTask(tmpMinutesCurrIndex);
				
				System.out.println("秒轮旋转一周，分轮当前指针index="+tmpMinutesCurrIndex);
			}
			
		});
		scondTimingWheel.startWheel();
	}
	
	@Override
	public void doTimingWheelTask(Integer wheelCurrIndex) {
		//如果分轮当前指针 下标=当前时间分钟，则把该分轮槽下的任务移到秒轮槽下
		Slot<TimingWheelTask> slot = MinuteSchedulerTimingWheel.this.wheelSlotList.get(wheelCurrIndex);
		ArrayList<TimingWheelTask> taskList = slot.getTaskList();
		Iterator<TimingWheelTask> iterator = taskList.iterator();
		while(iterator.hasNext()){
			TimingWheelTask task = iterator.next();
			AbstractConcreteTempTimingTask tmpTask = (AbstractConcreteTempTimingTask)task;
			scondTimingWheel.putTimingTask(tmpTask);
			iterator.remove();
		}
	}

	@Override
	public void putTimingTask(AbstractConcreteTempTimingTask task) {
		Date nowDate = new Date();
		Date startExecuteDate = task.getStartExecuteDate();
		Date currDate = task.getCurrDate();
		if(startExecuteDate.compareTo(currDate) < 0){
			System.out.println("定时任务开始执行时间不能小于任务设置时间");
			throw new IllegalArgumentException("定时任务开始执行时间不能小于任务设置时间");
		}
		long startExecuteTime = startExecuteDate.getTime();
		if((startExecuteTime - nowDate.getTime()) > Long.valueOf((this.getTicketPerWheel() * 60 * 1000))){	
			System.out.println("设置分轮定时任务，只能在一个周期内");
			throw new IllegalArgumentException("设置分轮定时任务，只能在一个周期内");
		}
		
		Integer targetSlotIndex = startExecuteDate.getMinutes();
		if(targetSlotIndex == getWheelCurrIndex()){	//如果执行分钟等于当前下标，则直接进入秒轮
			scondTimingWheel.putTimingTask(task);
		}else{
			this.addTask(targetSlotIndex, task);
		}
	}
}

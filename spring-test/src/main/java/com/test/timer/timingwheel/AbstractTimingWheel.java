package com.test.timer.timingwheel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTimingWheel implements TimingWheel {
	
	private Integer ticketPerWheel;	//时间轮周期数
	private Integer ticketDuration;	//时间精度
	private TimeUnit timeUnit;		//时间单位
	private Integer wheelCurrIndex = 0;	//时间轮当前指针
	
	private WheelCaller roundWheelCaller;	//指针旋转一周回调
	
	private ScheduledExecutorService schedule =  Executors.newSingleThreadScheduledExecutor();
	
	public AbstractTimingWheel(Integer ticketPerWheel,Integer ticketDuration,TimeUnit timeUnit){
		this.ticketPerWheel = ticketPerWheel;
		this.ticketDuration = ticketDuration;
		this.timeUnit = timeUnit;
	}

	/**
	 * 时间轮启动之前
	 */
	public abstract void beforeStartWheel();
	
//	/**
//	 * 时间轮启动完毕
//	 */
//	abstract void afterStartWheel();
	
	/**
	 * 时间轮停止之前
	 */
	public abstract void beforeStopWheel();
	
//	/**
//	 * 时间轮停止之后
//	 */
//	abstract void afterStopWheel();
	
	/**
	 * 绕行一周触发方法
	 */
	public abstract void roundWeek(WheelCaller wheelCaller);
	
//	public abstract void addTask(Integer slotIndex,TimingWheelTask task);
	
	public abstract void doTimingWheelTask(Integer wheelCurrIndex);
	
	public void startWheel(){
		beforeStartWheel();
		schedule.scheduleAtFixedRate(new Thread(new TicketWorker()), 0, ticketDuration, timeUnit);
//		afterStartWheel();
	}
	
	public void stopWheel() {
		
		beforeStopWheel();
		schedule.shutdown();
		
	}
	
	class TicketWorker implements Runnable{
		
		public void run() {
			if(wheelCurrIndex == ticketPerWheel){
				roundWeek(roundWheelCaller);
			}
			
			wheelCurrIndex = wheelCurrIndex % ticketPerWheel;
			
			doTimingWheelTask(wheelCurrIndex);
			
			wheelCurrIndex++;
		}
		
	}

	protected Integer getTicketPerWheel() {
		return ticketPerWheel;
	}

	protected Integer getTicketDuration() {
		return ticketDuration;
	}

	protected TimeUnit getTimeUnit() {
		return timeUnit;
	}

	protected Integer getWheelCurrIndex() {
		return wheelCurrIndex;
	}
	
	protected void setWheelCurrIndex(Integer wheelCurrIndex) {
		this.wheelCurrIndex = wheelCurrIndex;
	}

	protected ScheduledExecutorService getSchedule() {
		return schedule;
	}

	/**
	 * 注册指针回旋一周回调函数
	 * @param roundWheelCaller
	 */
	public void registeRoundWheelCaller(WheelCaller roundWheelCaller) {
		this.roundWheelCaller = roundWheelCaller;
	}
	
	/**
	 * 获取指针旋转一周回调函数
	 * @return
	 */
	protected WheelCaller getRoundWheelCaller() {
		return this.roundWheelCaller;
	}
}

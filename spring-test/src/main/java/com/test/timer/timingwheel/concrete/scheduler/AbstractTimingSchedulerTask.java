package com.test.timer.timingwheel.concrete.scheduler;

import com.test.timer.timingwheel.TimingWheelTask;

public abstract class AbstractTimingSchedulerTask implements TimingWheelTask {
	
	private Integer startExecuteTicket = 0;		//开始执行精度
	
	private Integer executeIntervalTicket = 1;	//执行间隔时间精度

	public Integer getStartExecuteTicket() {
		return startExecuteTicket;
	}

	public void setStartExecuteTicket(Integer startExecuteTicket) {
		this.startExecuteTicket = startExecuteTicket;
	}

	public Integer getExecuteIntervalTicket() {
		return executeIntervalTicket;
	}

	public void setExecuteIntervalTicket(Integer executeIntervalTicket) {
		this.executeIntervalTicket = executeIntervalTicket;
	}
}

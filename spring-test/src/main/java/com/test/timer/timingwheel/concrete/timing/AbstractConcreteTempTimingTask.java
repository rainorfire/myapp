package com.test.timer.timingwheel.concrete.timing;

import java.util.Date;

import com.test.timer.timingwheel.TimingWheelTask;

/**
 * 一次性定时任务抽象类
 * @author lenovo
 *
 */
public abstract class AbstractConcreteTempTimingTask implements TimingWheelTask {

	private Date startExecuteDate;	//开始执行日期
	
	private Date currDate = new Date();	//当前日期
	
	@Override
	public final boolean isTmpTask() {
		return true;
	}

	public Date getStartExecuteDate() {
		return startExecuteDate;
	}

	public void setStartExecuteDate(Date startExecuteDate) {
		this.startExecuteDate = startExecuteDate;
	}

	public Date getCurrDate() {
		return currDate;
	}
}

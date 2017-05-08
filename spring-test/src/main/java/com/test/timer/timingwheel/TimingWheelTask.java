package com.test.timer.timingwheel;

public interface TimingWheelTask {

	/**
	 * 执行时间轮任务
	 */
	void executeTask();
	
	/**
	 * 是否是临时任务，默认否
	 * @return
	 */
	default boolean isTmpTask(){
		return false;
	}
}

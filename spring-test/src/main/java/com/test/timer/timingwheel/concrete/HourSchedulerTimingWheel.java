package com.test.timer.timingwheel.concrete;

import java.util.concurrent.TimeUnit;

import com.test.timer.timingwheel.WheelCaller;
import com.test.timer.timingwheel.concrete.timing.AbstractConcreteTempTimingTask;
import com.test.timer.timingwheel.concrete.timing.AbstractTimingConcreteTempTaskHandler;

public class HourSchedulerTimingWheel extends AbstractTimingConcreteTempTaskHandler{
	
	private MinuteSchedulerTimingWheel scondTimingWheel;
	
	public HourSchedulerTimingWheel(Integer ticketDuration) {
		super(24, ticketDuration, TimeUnit.HOURS);
		scondTimingWheel = new MinuteSchedulerTimingWheel();
	}

	public HourSchedulerTimingWheel(Integer ticketPerWheel, Integer ticketDuration, TimeUnit timeUnit) {
		super(ticketPerWheel, ticketDuration, timeUnit);
		scondTimingWheel = new MinuteSchedulerTimingWheel();
	}

	@Override
	public void beforeStartWheel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeStopWheel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roundWeek(WheelCaller wheelCaller) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void startWheel(){
		
		
	}

	@Override
	public void putTimingTask(AbstractConcreteTempTimingTask task) {
		
	}

}

package com.test.timer.timingwheel.concrete.timing;

import java.util.concurrent.TimeUnit;

import com.test.timer.timingwheel.AbstractDelegateTimintWheel;
import com.test.timer.timingwheel.TimingWheelTask;

public abstract class AbstractTimingConcreteTempTaskHandler extends AbstractDelegateTimintWheel{

	public AbstractTimingConcreteTempTaskHandler(Integer ticketPerWheel, Integer ticketDuration, TimeUnit timeUnit) {
		super(ticketPerWheel, ticketDuration, timeUnit);
	}
	
	/**
	 * 定时任务
	 * @param startExecuteDate 开始执行时间
	 * @param intervalTicket 间隔执行时间
	 */
	public abstract void putTimingTask(AbstractConcreteTempTimingTask task);
	
	@Override
	public void doTimingWheelTask(Integer wheelCurrIndex) {
		doCurrSlotTask(wheelCurrIndex);
		Slot<TimingWheelTask> currSlot = wheelSlotList.get(wheelCurrIndex);
		currSlot.removeAll();
	}
	
	/**
	 * 添加新任务到时间槽
	 * @param slotIndex
	 * @param task
	 */
	protected void addTask(Integer slotIndex,AbstractConcreteTempTimingTask task){
		Slot<TimingWheelTask> slot = wheelSlotList.get(slotIndex);
		slot.add(task);
	}

}

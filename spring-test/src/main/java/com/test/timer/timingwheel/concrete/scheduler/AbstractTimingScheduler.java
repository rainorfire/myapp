package com.test.timer.timingwheel.concrete.scheduler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.test.timer.timingwheel.AbstractDelegateTimintWheel;
import com.test.timer.timingwheel.TimingWheelTask;

public abstract class AbstractTimingScheduler extends AbstractDelegateTimintWheel{

	public AbstractTimingScheduler(Integer ticketPerWheel, Integer ticketDuration, TimeUnit timeUnit) {
		super(ticketPerWheel, ticketDuration, timeUnit);
	}
	
	@Override
	public void doTimingWheelTask(Integer wheelCurrIndex) {
		doCurrSlotTask(wheelCurrIndex);
		copyTask2NextSlot(wheelCurrIndex);
		Slot<TimingWheelTask> currSlot = wheelSlotList.get(wheelCurrIndex);
		currSlot.removeAll();
//		System.out.println("执行wheelCurrIndex="+wheelCurrIndex+"槽下的schedule任务");
	}
	
	protected void copyTask2NextSlot(Integer wheelIndex){
		Slot<TimingWheelTask> slot = wheelSlotList.get(wheelIndex);
		ArrayList<TimingWheelTask> taskList = slot.getTaskList();
		if(taskList != null && !taskList.isEmpty()){
			for(TimingWheelTask task : taskList){
				if(!(task instanceof AbstractTimingSchedulerTask)){
					throw new RuntimeException("task in Slot must cast AbstractTimingTaskScheduler.");
				}
				if(task.isTmpTask()){
					continue;
				}
				
				AbstractTimingSchedulerTask scheduler = (AbstractTimingSchedulerTask)task;
				
				Integer nextSlotIndex = calcNextSlotIndexByIntervalTicket(scheduler.getExecuteIntervalTicket());
				
//				System.out.println("计算得到下一个槽下标为nextSlotIndex="+nextSlotIndex);
				
				Slot<TimingWheelTask> nextSlot = wheelSlotList.get(nextSlotIndex);
				nextSlot.add(scheduler);
			}
		}
	}
	
	/**
	 * 根据间隔执行时间计算下次执行槽下标
	 * @return
	 */
	protected Integer calcNextSlotIndexByIntervalTicket(Integer executeIntervalTicket){
		Integer tmp = getWheelCurrIndex() + executeIntervalTicket;
		return tmp % getTicketPerWheel();
	}
	
	/**
	 * 添加新任务到时间槽
	 * @param slotIndex
	 * @param task
	 */
	protected void addTask(Integer slotIndex,AbstractTimingSchedulerTask task){
		Slot<TimingWheelTask> slot = wheelSlotList.get(slotIndex);
		slot.add(task);
	}

}

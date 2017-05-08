package com.test.timer.timingwheel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.test.timer.timingwheel.concrete.timing.AbstractConcreteTempTimingTask;

public abstract class AbstractDelegateTimintWheel extends AbstractTimingWheel{
	
	protected List<Slot<TimingWheelTask>> wheelSlotList;

	public AbstractDelegateTimintWheel(Integer ticketPerWheel, Integer ticketDuration, TimeUnit timeUnit) {
		super(ticketPerWheel, ticketDuration, timeUnit);
		wheelSlotList = new LinkedList<Slot<TimingWheelTask>>();
		for(int i = 0;i < ticketPerWheel;i++){
			Slot<TimingWheelTask> slot = new Slot<TimingWheelTask>();
			slot.setSlotId(Long.valueOf(i));
			wheelSlotList.add(slot);	//添加时间轮槽
		}
	}
	
	
	/**
	 * 默认复制当前槽内任务到下一个周期，执行时间轮任务，可被子类覆盖此方法
	 */
	@Override
	public void doTimingWheelTask(Integer wheelCurrIndex) {
//		Slot<TimingWheelTask> slot = wheelSlotList.get(wheelCurrIndex);
//		Integer size = slot.getTaskList().size();
		doCurrSlotTask(wheelCurrIndex);
//		System.out.println("执行wheelCurrIndex="+wheelCurrIndex+"槽下的任务，任务数量="+size);
	}
	
	/**
	 * 执行任务，去除临时任务
	 * @param wheelCurrIndex
	 */
	protected void doCurrSlotTask(Integer wheelCurrIndex){
		Slot<TimingWheelTask> slot = wheelSlotList.get(wheelCurrIndex);
		ArrayList<TimingWheelTask> taskList = slot.getTaskList();
		if(taskList != null && !taskList.isEmpty()){
			Iterator<TimingWheelTask> iterator = taskList.iterator();
			while(iterator.hasNext()){
				TimingWheelTask task = iterator.next();
				task.executeTask();
				if(task.isTmpTask()){
					iterator.remove();
				}
			}
		}
	}
	
	/**
	 * 时间轮槽
	 * @author cyj
	 *
	 * @param <T>
	 */
	public class Slot<T>{
		private ReentrantLock lock = new ReentrantLock();
		
		private Long slotId;
		
		private ArrayList<T> taskList = new ArrayList<T>();
		
		public boolean add(T obj){
//			System.out.println("槽内增加任务，Task="+obj);
			lock.lock();
			boolean bool = this.taskList.add(obj);
			lock.unlock();
			return bool;
		}
		
		public boolean remove(T obj){
			lock.lock();
			boolean bool = this.taskList.remove(obj);
			lock.unlock();
			return bool;
		}
		
		public void removeAll(){
			lock.lock();
			taskList.clear();
			lock.unlock();
		}

		public Long getSlotId() {
			return slotId;
		}

		public void setSlotId(Long slotId) {
			this.slotId = slotId;
		}

		public ArrayList<T> getTaskList() {
			return taskList;
		}

		public void setTaskList(ArrayList<T> taskList) {
			this.taskList = taskList;
		}

	}


}

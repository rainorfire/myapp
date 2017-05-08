package timingwheel.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import com.test.timer.timingwheel.WheelCaller;
import com.test.timer.timingwheel.concrete.MinuteSchedulerTimingWheel;
import com.test.timer.timingwheel.concrete.timing.AbstractConcreteTempTimingTask;
import com.test.timer.timingwheel.concrete.timing.AbstractTimingConcreteTempTaskHandler;

public class SimpleSchedulerTimingWheel extends AbstractTimingConcreteTempTaskHandler{

	public SimpleSchedulerTimingWheel(Integer ticketPerWheel, Integer ticketDuration, TimeUnit timeUnit) {
		super(ticketPerWheel, ticketDuration, timeUnit);
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
	
	public static void main(String[] args) {
//		SimpleSchedulerTimingWheel simpleTimingWheel = new SimpleSchedulerTimingWheel(10,1,TimeUnit.SECONDS);
//		simpleTimingWheel.startWheel();
//		
//		TestSchedulerTask tsk = new TestSchedulerTask(3);
//		simpleTimingWheel.addTask(2, tsk);
//		
//		try {
//			Thread.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		TestSchedulerTask tsk1 = new TestSchedulerTask(2);
//		simpleTimingWheel.addTask(0, tsk1);
//		
//		try {
//			Thread.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		TestSchedulerTask tsk2 = new TestSchedulerTask(5);
//		simpleTimingWheel.addTask(3, tsk2);
		
		MinuteSchedulerTimingWheel mtw = new MinuteSchedulerTimingWheel();
		mtw.startWheel();
		
//		TestSchedulerTask mTsk1 = new TestSchedulerTask(1,"MM分轮正在执行任务***");
//		mtw.addTask(1, mTsk1);
//		mtw.putTimingTask(task);
//		
//		TestSchedulerTask mTsk0 = new TestSchedulerTask(1,"SS秒轮正在执行任务。。。");
//		mtw.secondWheelAddTask(1, mTsk0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TmpTask task = new TmpTask();
		TmpTask task1 = new TmpTask();
		try {
			task.setStartExecuteDate(sdf.parse("2017-04-24 18:02:32"));
			task1.setStartExecuteDate(sdf.parse("2017-04-24 18:02:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		mtw.putTimingTask(task);
		mtw.putTimingTask(task1);
	}

	@Override
	public void putTimingTask(AbstractConcreteTempTimingTask task) {
		// TODO Auto-generated method stub
		
	}
}

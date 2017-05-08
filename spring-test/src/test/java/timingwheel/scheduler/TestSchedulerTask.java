package timingwheel.scheduler;

import com.test.timer.timingwheel.concrete.scheduler.AbstractTimingSchedulerTask;

public class TestSchedulerTask extends AbstractTimingSchedulerTask {
	
	private String taskName ;
	
	public TestSchedulerTask(Integer executeIntervalTicket,String taskName){
		this.setExecuteIntervalTicket(executeIntervalTicket);
		this.taskName = taskName;
	}

	@Override
	public void executeTask() {
		
		System.out.println("taskName="+taskName+"间隔时间为executeIntervalTicket="+getExecuteIntervalTicket());

	}

}

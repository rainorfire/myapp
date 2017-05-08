package timingwheel;

import com.test.timer.timingwheel.TimingWheelTask;

public class ConcreteTimingWheelTask implements TimingWheelTask{
	
	private Integer idx = 0;
	
	public ConcreteTimingWheelTask(Integer idx){
		this.idx = idx;
	}

	@Override
	public void executeTask() {
		System.out.println("执行时间轮任务！idx="+idx);
	}
}

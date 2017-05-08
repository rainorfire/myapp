package timingwheel;

import java.util.concurrent.TimeUnit;

import com.test.timer.timingwheel.AbstractDelegateTimintWheel;
import com.test.timer.timingwheel.WheelCaller;

public class SimpleTimingWheel extends AbstractDelegateTimintWheel{

	
	public SimpleTimingWheel(Integer ticketPerWheel,Integer ticketDuration,TimeUnit timeUnit){
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
//		SimpleTimingWheel simpleTimingWheel = new SimpleTimingWheel(10,1,TimeUnit.SECONDS);
//		simpleTimingWheel.startWheel();
//		
//		simpleTimingWheel.addTask(1, new ConcreteTimingWheelTask(1));
//		
//		try {
//			Thread.sleep(1000 * 8);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		TempTimingWheelTask task7 = new TempTimingWheelTask(7);
//		simpleTimingWheel.addTask(7, task7);
	}

}

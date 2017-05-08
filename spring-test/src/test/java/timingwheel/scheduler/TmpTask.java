package timingwheel.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.test.timer.timingwheel.concrete.timing.AbstractConcreteTempTimingTask;

public class TmpTask extends AbstractConcreteTempTimingTask {

	@Override
	public void executeTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date executeDate = this.getStartExecuteDate();
		System.out.println(sdf.format(new Date())+"正在执行临时任务，executeDate="+sdf.format(executeDate));

	}

}

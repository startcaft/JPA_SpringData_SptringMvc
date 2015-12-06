package quartz_core.quartz_study;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 需要执行的任务
 */
public class MyJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("测试Quartz" + new Date());
	}
}

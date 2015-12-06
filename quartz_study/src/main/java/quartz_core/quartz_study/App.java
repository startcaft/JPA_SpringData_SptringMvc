package quartz_core.quartz_study;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		
		System.out.println("MyJob Start!");
		
		test();
	}
	
	public static void test(){
    	//通过schedulerFactory获取一个调度器 
    	SchedulerFactory schedulerfactory = new StdSchedulerFactory();
    	Scheduler scheduler = null;
    	
    	try {
			//通过schedulerFactory获取一个调度器 
    		scheduler = schedulerfactory.getScheduler();
    		
    		//通过JobBuilder来创建Job实例
    		//设置Job的名称，所在的组，以及指定Job的实现类
    		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
    				.withIdentity("myJob", "myGroup")
    				.build();
    		
    		//使用SimpleTrigger规则，每5秒执行一次，执行10次
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("simpleTrigger","triggerGroup")
    				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5).withRepeatCount(10))
    				.startNow().build();
    		
    		/*
    		//使用cornTrigger规则  每天10点42分  
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")  
            .withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 * * ? *"))  
            .startNow().build();  
            */
    		
    		//把任务和触发器注册到任务调度对象中
    		scheduler.scheduleJob(jobDetail, trigger);
    		
    		//启动任务调度
    		scheduler.start();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

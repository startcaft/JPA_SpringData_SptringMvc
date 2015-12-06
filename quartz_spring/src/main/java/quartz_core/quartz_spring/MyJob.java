package quartz_core.quartz_spring;

import java.util.Date;

/**
 * 需要执行的任务，普通的POJO类
 */
public class MyJob {
	
	public void doJob(){
		service();
	}
	
	public void service(){
		System.out.println("测试Quartz" + new Date());
	}
}

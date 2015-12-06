package quartz_core.quartz_spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
	public static void main(String[] args) {
		
		System.out.println( "Hello World!" );
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
	}
}

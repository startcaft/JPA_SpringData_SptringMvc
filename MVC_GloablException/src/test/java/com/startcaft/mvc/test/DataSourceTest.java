package com.startcaft.mvc.test;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {
	
	private ApplicationContext context;
	
	@Before
	public void init(){
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void test() {
		
		DataSource ds = (DataSource) context.getBean("dataSource");
		System.out.println(ds);
	}

}

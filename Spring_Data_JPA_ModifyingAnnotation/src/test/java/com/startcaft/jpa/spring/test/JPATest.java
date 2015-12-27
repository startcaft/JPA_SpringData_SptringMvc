package com.startcaft.jpa.spring.test;




import java.util.Date;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.jpa.spring.dao.UserRepository;
import com.startcaft.jpa.spring.service.UserService;

public class JPATest {

	private ApplicationContext context;
	private UserRepository respotiry;
	private UserService service;
	
	@Before
	public void init(){
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		respotiry = (UserRepository) context.getBean("userRepository");
		service = (UserService) context.getBean("userService");
	}
	
	@Test
	public void testDataSource() {
		
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}
	
	@Test
	public void testQueryAnnotationUpdate(){
		
		respotiry.updateUserBirthday(new Date(), 1);
	}
	
	@Test
	public void testModiyfingAnnotation(){
		
		service.updateUserBirthday(new Date(), 1);
	}
}

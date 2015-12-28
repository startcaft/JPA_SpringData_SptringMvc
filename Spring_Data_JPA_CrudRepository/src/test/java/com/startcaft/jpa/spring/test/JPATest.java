package com.startcaft.jpa.spring.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.common.SexEum;
import com.startcaft.jpa.entity.User;
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
	public void testCrudRepository(){
		
		List<User> users = new ArrayList<User>();
		
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setAddress("ZZZZZ" + i);
			user.setBirthday(new Date());
			user.setSex(SexEum.MALE);
			user.setUsername("Z" + i + "Name");
			
			users.add(user);
		}
		
		service.saveUserList(users);
	}
}

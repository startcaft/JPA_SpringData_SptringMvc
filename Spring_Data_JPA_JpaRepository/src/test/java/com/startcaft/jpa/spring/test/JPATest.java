package com.startcaft.jpa.spring.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.startcaft.common.SexEum;
import com.startcaft.jpa.entity.User;
import com.startcaft.jpa.spring.dao.UserRepository;
import com.startcaft.jpa.spring.service.UserService;

public class JPATest {

	private ApplicationContext context;
	private UserRepository respotiry;
	private UserService service;

	@Before
	public void init() {

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
	public void testJpaRepository() {
		
		User user = new User();
		user.setAddress("中国湖北武汉");
		user.setUsername("startcaft");
		user.setBirthday(new Date());
		user.setSex(SexEum.MALE);
		
		//相当于jpa的merge()方法，没id则insert，有id则比较属性更新，中间有一个属性复制的过程。
		User user2 = respotiry.saveAndFlush(user);
	}
}

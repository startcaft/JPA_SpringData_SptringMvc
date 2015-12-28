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
	public void testPagingAndSortingRepository() {

		// pageIndex 是从0 开始的
		int pageIndex = 5 - 1;// 第5页
		int pageSize = 5;

		// Sort封装了排序的信息
		Order order = new Order(Direction.ASC, "address");
		Order order2 = new Order(Direction.DESC, "id");
		Sort sort = new Sort(order, order2);

		// Pageable是一个接口，其实现类为PageRequest，其中封装了分页相关，排序相关的参数
		Pageable pageable = new PageRequest(pageIndex, pageSize,sort);

		Page<User> page = respotiry.findAll(pageable);

		System.out.println("总记录数:" + page.getTotalElements());
		System.out.println("总页数:" + page.getTotalPages());
		System.out.println("当前页码：" + (page.getNumber() + 1));
		System.out.println("当前页的List:" + page.getContent());
		System.out.println("当前页面的记录数:" + page.getNumberOfElements());
	}
}

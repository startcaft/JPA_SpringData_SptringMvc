package com.startcaft.jpa.spring.test;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.jpa.entity.Order;
import com.startcaft.jpa.spring.dao.OrderRepository;

public class OrderRepositoryTest {
	
	private ApplicationContext context;
	OrderRepository respotiry;
	
	@Before
	public void init(){
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		respotiry = (OrderRepository) context.getBean("orderRepository");
	}
	
	@Test
	public void testGetByUserIdGreaterThan() {
		
		List<Order> orders = respotiry.getByUserIdGreaterThan(1);
		
		System.out.println(orders);
	}

}

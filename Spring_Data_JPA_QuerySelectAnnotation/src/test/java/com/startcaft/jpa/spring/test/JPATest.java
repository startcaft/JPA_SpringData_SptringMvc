package com.startcaft.jpa.spring.test;




import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.jpa.entity.User;
import com.startcaft.jpa.spring.dao.UserRepository;

public class JPATest {

	private ApplicationContext context;
	UserRepository respotiry;
	
	@Before
	public void init(){
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		respotiry = (UserRepository) context.getBean("userRepository");
	}
	
	@Test
	public void testDataSource() {
		
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}
	
	@Test
	public void testQueryAnnotation(){
		
		User user = respotiry.getMaxIdUser();
		
		System.out.println(user);
	}
	
	@Test
	public void testQueryAnnotationParams1(){
		
		User user = respotiry.queryAnnotationParams1("王五", 1);
		
		System.out.println(user);
	}
	
	@Test
	public void testQueryAnnotationParams2(){
		
		User user = respotiry.queryAnnotationParams2(1,"王五");
		
		System.out.println(user);
	}
	
	@Test
	public void testQueryAnnotationLikeParam(){
		
		List<User> user = respotiry.queryAnnotationLikeParam("王");
		
		System.out.println(user);
	}
	
	@Test
	public void testQueryNativeSql(){
		
		System.out.println(respotiry.getTotalCount());
	}
}

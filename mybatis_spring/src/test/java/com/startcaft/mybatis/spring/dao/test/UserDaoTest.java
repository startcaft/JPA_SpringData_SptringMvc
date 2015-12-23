package com.startcaft.mybatis.spring.dao.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.mybatis.dao.UserDao;
import com.startcaft.mybatis.po.User;

public class UserDaoTest {
	
	private ApplicationContext context;
	
	@Before
	public void init() throws IOException {

		context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@Test
	public void testFindUserById() {
		
		UserDao dao = (UserDao) context.getBean("userDao");
		
		try {
			User user = dao.findUserById(1);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

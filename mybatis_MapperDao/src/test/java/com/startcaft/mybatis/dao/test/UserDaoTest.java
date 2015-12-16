package com.startcaft.mybatis.dao.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.mybatis.mapper.UserMapper;
import com.startcaft.mybatis.po.User;

public class UserDaoTest {
	
	private SqlSessionFactory factory;
	
	//次方法在执行测试方法之前执行
	@Before
	public void init() throws IOException{
		
		//创建SqlSessionFactory
		InputStream inputSream = Resources.getResourceAsStream("mybatis_config.xml");
		// 创建SqlSessionFactory会话工厂，需要传入mybatis的配置信息
		factory = new SqlSessionFactoryBuilder().build(inputSream);
	}

	@Test
	public void testFindUserById() throws Exception {
		
		SqlSession sqlSession = factory.openSession();
		
		//创建UserMapper对象，通过SqlSession的getMapper()方法；
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		System.out.println(userMapper.getClass().getName());
		
		User user = userMapper.findUserById(1);
		System.out.println(user);
		sqlSession.close();
	}

	@Test
	public void testInsertUser() throws Exception {
		
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);	
		
		User user = new User();
		user.setAddress("北京");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setUsername("孙悟空");
		
		userMapper.insertUser(user);
		
		sqlSession.commit();
		System.out.println("id主键:" + user.getId());
		sqlSession.close();
	}

	@Test
	public void testFindUserByname() throws Exception {
		
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);	
		
		List<User> list = userMapper.findUserByname("小明");
		for (User user : list) {
			System.out.println(user.getUsername());
		}
		
		sqlSession.close();
	}

}

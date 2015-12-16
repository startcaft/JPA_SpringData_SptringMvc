package com.startcaft.mybatis.dao.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.mybatis.dao.IUserDao;
import com.startcaft.mybatis.dao.impl.UserDao;
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
		
		IUserDao userDao = new UserDao(factory);
		
		User user = userDao.findUserById(1);
		
		System.out.println(user);
	}

	@Test
	public void testInsertUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUserById() {
		fail("Not yet implemented");
	}

}

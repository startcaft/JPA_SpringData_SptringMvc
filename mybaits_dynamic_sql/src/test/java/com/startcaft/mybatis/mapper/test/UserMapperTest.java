package com.startcaft.mybatis.mapper.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.mybaits.vo.UserQueryVo;
import com.startcaft.mybatis.mapper.UserMapper;
import com.startcaft.mybatis.po.User;

/*
 * 什么是动态sql？
 * mybatis核心：对sql语句进行灵活操作，通过表达式进行判断，对sql进行灵活拼接，组装。
 * 
 * 案例需求：对查询条件进行判断，如果查询条件不为空，才进行查询条件的拼接。
 */
public class UserMapperTest {

	private SqlSessionFactory factory;

	// 次方法在执行测试方法之前执行
	@Before
	public void init() throws IOException {

		// 创建SqlSessionFactory
		InputStream inputSream = Resources.getResourceAsStream("mybatis_config.xml");
		// 创建SqlSessionFactory会话工厂，需要传入mybatis的配置信息
		factory = new SqlSessionFactoryBuilder().build(inputSream);
	}
	
	@Test
	public void findUserListTest(){
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			
			User user = new User();
			user.setSex("2");
			
			UserQueryVo query = new UserQueryVo();
			query.setUser(user);
			
			//List<User> lists = mapper.findUserList(null);//查询所有的
			//int count = mapper.findUserListCount(null);
			List<User> lists = mapper.findUserList(query);//查询sex='2'的
			int count = mapper.findUserListCount(query);
			
			System.out.println(count);
			System.out.println(lists);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

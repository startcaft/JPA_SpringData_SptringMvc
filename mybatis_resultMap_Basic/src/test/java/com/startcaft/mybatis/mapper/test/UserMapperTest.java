package com.startcaft.mybatis.mapper.test;


import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.mybatis.mapper.UserMapper;
import com.startcaft.mybatis.po.User;

/*
 * mybatis中使用resultMap来完成高级映射，这里的实例只是一个很简单的resultMap应用
 * 
 * 如果SQL查询出来的列名和POJO中的属性名不一致，可以通过定义一个resultMap来对列名和属性之间做一个映射；
 * 
 * resultMap通过id被引用，别的mapper引用resultMap时，需要通过namespace+id才能引用；
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
	public void testFindUserByResultMap() {
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			
			User user = mapper.findUserByResultMap(1);
			
			System.out.println(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}

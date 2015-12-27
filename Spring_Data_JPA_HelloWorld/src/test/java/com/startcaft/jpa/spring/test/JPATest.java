package com.startcaft.jpa.spring.test;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.jpa.entity.User;
import com.startcaft.jpa.spring.dao.UserRepository;

/*
 * Spring Data是Spring的一个子项目，目的是为了简化持久化层(DAO)的开发量，
 * Spring Data支持NoSql以及关系型数据库。
 * 
 * Spring Data JPA进行持久层开发需要四个步骤：
 * 1，配置Spring整合JPA
 * 2，在Spring配置文件中配置Spring Data，让Spring为声明的DAO接口创建代理对象。
 * 		配置了<jpa:repositories>后，Spring初始化容器时将会扫描base package 指定的包目录及其子目录，
 * 		为继承自Repository或其子接口的接口创建代理对象，并将代理对象注册为Spring Bean。
 * 		业务层便可以通过Spring自动封装的特性来直接使用该对象。
 * 3，声明持久层的接口，该接口继承Repository，Repository是一个标记型接口，不包含任何方法，
 * 		如有必要，可以实现Repository其他子接口，其中定义了一些常用的增删改善，以及分页相关的方法。
 * 4，在接口中声明需要的方法，Spring Data将根据给定的策略(后面详细讲解)来为其生成实现代码。
 */
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
	public void testSave(){
		
		User user = respotiry.getByUsername("张三");
		
		System.out.println(user);
	}
	
	@Test
	public void testGetByUsernameStartingWithAndIdLessThan(){
		
		List<User> users = respotiry.getByUsernameStartingWithAndIdLessThan("王", 30);
		
		System.out.println(users);
	}
	
	@Test
	public void testGetByIdInAndBrithdayLessThan(){
		
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(10);
		ids.add(16);
		ids.add(28);
		
		List<User> users = respotiry.getByIdInOrBirthdayLessThan(ids, new Date());
		
		System.out.println(users);
	}
}

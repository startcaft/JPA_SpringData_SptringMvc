package com.startcaft.jpa.spring.test;


import java.util.Date;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.common.SexEum;
import com.startcaft.jpa.entity.User;
import com.startcaft.jpa.spring.service.UserService;

/*
 * Spring整合JPA
	三种整合方式：
	1，LocalEntityManagerFactoryBean:
		适用于仅仅使用JPA进行数据访问的项目，该FactoryBean将根据PersistenceProvider自动简检测配置文件进行工作，
		一般从"META-INF/persistence.xml"读取配置信息，这种方式最为简单，
		但不能设置Spring中的DataSource，切不支持Spring管理的全局事务。
	
	2，从JNDI中获取：
		用于从Java EE服务器获取指定的EntityManagerFactory，这种方式在进行Spring事务管理时候一般要使用JTA事务管理。
	
	3，LocalContainerEntityManagerFactoryBean：
		适用于所有环境的FactoryBean，能全面控制EntityManagerFactory配置，如指定Spring定义的DataSource等等。
 */
public class JPATest {

	private ApplicationContext context;
	
	@Before
	public void init(){
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testDataSource() {
		
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}
	
	@Test
	public void testSave(){
		
		UserService service = (UserService) context.getBean("userService");
		
		User u1 = new User();
		u1.setAddress("XXXXXXX");
		u1.setUsername("XXXXXXX");
		u1.setSex(SexEum.MALE);
		u1.setBirthday(new Date());
		
		User u2 = new User();
		u2.setAddress("YYYYYY");
		u2.setUsername("YYYYYY");
		u2.setSex(SexEum.FEMALE);
		u2.setBirthday(new Date());
		
		System.out.println(service.getClass().getName());//代理对象的话，事务就成功了
		service.saveNewUser(u1, u2);
	}

}

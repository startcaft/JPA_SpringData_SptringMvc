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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.mybatis.mapper.OrdersMapper;
import com.startcaft.mybatis.po.Orderdetail;
import com.startcaft.mybatis.po.Orders;

/*
 * Mybatis和Spring的整合思路：
 * 1，需要Spring通过单例方式来管理SqlSessionFactory【Mybatis提供的第三方整合包mybatis-spring-x.x.x.jar(org.mybatis.spring.SqlSessionFactoryBean)】
 * 2，Spring和mybatis整合生成代理对象，使用SqlSessionFactory创建SqlSession【Spring和mybatis整合自动完成】;
 * 3，持久层的mapper都需要由Spring来管理
 * 
 * (1)原始Dao开发整合Spring，可以让Dao的实现类继承org.mybatis.spring.support.SqlSessionDaoSupport抽象类；
 * 
 * (2)Mapper代理开发整合Spring，
 * 		配置org.mybatis.spring.mapper.MapperFactoryBean的bean，它可以根据xxxMapper.java接口来生成代理对象；
 * 		【弊端：需要针对每一个Mapper接口进行配置】
 * 
 * 		配置org.mybatis.spring.mapper.MapperScannerConfigurer,它可以自动为扫描到的Mapper接口创建代理对象，
 * 		并且将它们在Spring容器中注册；
 * 		【注意：使用MapperScannerConfigurer后，Mybatis的配置文件中就不需要再去加载Mapper映射文件了】
 * 		【但是需要遵循一些规范，将xxxMapper.java和xxxMapper.xml文件名必须保持一致，且在一个目录中】
 * 		【坑点：千万不要配置该对象的SqlSessionFactory属性，不然Spring无法加载外部的属性文件，要配置sqlSessionTemplateBeanName属性，来指定SqlSessionFactory配置好的bean的名称】
 */
public class OrdersMapperTest {

private ApplicationContext context;
	
	@Before
	public void init() throws IOException {

		context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	
	@Test
	public void testFindOrdersUserResulrMap() {
		
		//OrdersMapper mapper = (OrdersMapper) context.getBean("orderMapper");
		OrdersMapper mapper = (OrdersMapper) context.getBean("ordersMapper");
		
		try {
			List<Orders> lists = mapper.findOrderUserAndDetails();
			
			for (Orders orders : lists) {
				System.out.println(orders.getId() + "---" + orders.getUser().getUsername());
				for (Orderdetail detail : orders.getOrderDetails()) {
					System.out.println("\t\t" +detail.getItemsNum());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

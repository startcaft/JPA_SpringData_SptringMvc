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

import com.startcaft.mybaits.vo.OrdersCutom;
import com.startcaft.mybatis.mapper.OrdersMapper;
import com.startcaft.mybatis.po.Orders;

/*
 * mybatis 一对一 查询，
 * 1,使用内连接，POJO包装类的形式来完成;
 * 2,使用resultMap来完成，在Orders类中添加一个User对象属性，使用resultMap来完成高级映射；
 * 
 * 实现区别：
 * resultMap可以实现延迟加载，resutType无法实现延迟加载。
 */
public class OrdersMapperTest {

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
	public void testFindOrderUser() {
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			OrdersMapper mapper = session.getMapper(OrdersMapper.class);
			
			List<OrdersCutom> lists = mapper.findOrdersUser();
			
			for (OrdersCutom ordersCutom : lists) {
				System.out.println(ordersCutom.getNumber() + "---" + ordersCutom.getUsername());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testFindOrdersUserResulrMap() {
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			OrdersMapper mapper = session.getMapper(OrdersMapper.class);
			
			List<Orders> orders = mapper.findOrdersUserResulrMap();
			
			for (Orders order : orders) {
				System.out.println(order.getNumber() + "---" + order.getUser().getUsername());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

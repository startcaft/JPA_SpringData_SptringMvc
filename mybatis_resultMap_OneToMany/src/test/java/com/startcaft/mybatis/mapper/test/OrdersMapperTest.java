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

import com.startcaft.mybatis.mapper.OrdersMapper;
import com.startcaft.mybatis.po.Orderdetail;
import com.startcaft.mybatis.po.Orders;

/*
 * mybatis 一对多查询:
 * mybatis使用collection标签对关联查询的多条记录映射到一个List集合属性中。
 * 
 * 需求案例：
 * 查询订单以及订单明细，要求对Orders进行映射，不能出现重复记录:
 * 
 * 在Orders类中，添加一个List<OrderDetails> orderDetails属性，
 * 最终将订单信息映射到Orders对象中，将该订单的订单明细映射到orderDetails对象中。
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
	public void testFindOrdersUserResulrMap() {
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			OrdersMapper mapper = session.getMapper(OrdersMapper.class);
			
			List<Orders> lists = mapper.findOrderUserAndDetails();
			
			for (Orders orders : lists) {
				System.out.println(orders.getId() + "---" + orders.getUser().getUsername());
				for (Orderdetail detail : orders.getOrderDetails()) {
					System.out.println("\t\t" +detail.getItemsNum());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

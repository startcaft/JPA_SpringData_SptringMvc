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
import com.startcaft.mybatis.po.User;

/*
 * mybatis 对多对查询:
 * 
 * 需求分析：查询用户及用户所购买的商品
 * 查询主表：用户表；
 * 关联表：【数据库级别来说，用户与商品没有直接的关联关系，中间通过订单表，订单明细表来关联】
 * 		orders表，orderdetail表，items表
 * 
 * 使用resultMap，无非是collection嵌套，或者association嵌套。
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
			
			List<User> users = mapper.findUserAndItemsResultMap();
			
			for (User user : users) {
				System.out.println("用户:" + user.getUsername() + "的订单:");
				for (Orders order : user.getOrdersList()) {
					System.out.println("\t" + "订单编号:" + order.getId());
					for (Orderdetail detail : order.getOrderDetails()) {
						System.out.println("\t\t" + "明细:" + detail.getId() + "--" + detail.getItem().getName() + "(" + detail.getItemsNum() + ")");
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

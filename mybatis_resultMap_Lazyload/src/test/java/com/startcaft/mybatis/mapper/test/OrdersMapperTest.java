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
 * mybatis的resultMap可以实现高级映射(使用assocation,collection实现一对一，一对多映射)；
 * assocation，collection具有延迟加载的功能；
 * 
 * 需求案例：
 * 查询订单并且关联查询用户信息。【先查询订单信息，当我们需要查询用户信息时再查询用户信息】,
 * 【这种按需查询，称之为延迟加载】
 * 
 * 延迟加载：先从单表查询，需要时再从关联表关联查询，大大提交数据库的性能。
 * 
 * 【需要定义两个statement，分开查询】
 * 在主查询的statement中使用assocaion,collection延迟加载关联查询信息的statement;
 * 
 * 【mybatis默认没有开启延迟加载，需要在settings节点去开启延迟加载，并关闭默认的积极加载】
 * 延迟加载需呀cglib.jar的支持
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
			
			List<Orders> list = mapper.findOrdersUserLazyload();
			
			for (Orders orders : list) {
				System.out.println(orders.getNumber());
				System.out.println(orders.getUser().getUsername());//getUser()开始延迟加载
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

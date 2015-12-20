package com.startcaft.mybatis.paramterType.test;


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
 * 通过paramterType指定输入参数的类型，类型可以是简单类型，HashMmap，自定义POJO的包装类型。
 * 
 * 案例需求1：完成用户信息的综合查询，需要传入查询条件(可能包括用户信息，商品信息，订单信息等)
 * 
 * 针对以上需求，建议使用自定义的包装类型的POJO
 */
public class UserMapperTest {
	
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
	public void findUserListTest(){
		
		User user = new User();
		user.setSex("1");
		user.setUsername("张");
		UserQueryVo query = new UserQueryVo();
		query.setUser(user);
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			List<User> users = mapper.findUserList(query);
			
			for (User u : users) {
				System.out.println(u.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

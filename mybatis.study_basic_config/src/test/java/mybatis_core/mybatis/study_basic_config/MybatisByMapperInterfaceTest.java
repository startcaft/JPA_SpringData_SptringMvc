package mybatis_core.mybatis.study_basic_config;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import mybatis.shop.mapper.UserMapper;
import mybatis.shop.model.User;
import mybatis.shop.util.MybatisUtil;

/*
 * Mybatis基于Mapper接口的编程方式
 */
public class MybatisByMapperInterfaceTest {
	
	//select操作是不需要事务支持的
	@Test
	public void testList(){
		
		SqlSession session = null;
		try {
			session = MybatisUtil.createSession();
			//获取UserMapper接口对象
			UserMapper userMapper = session.getMapper(UserMapper.class);
			
			List<User> users = userMapper.list();
			for (User user : users) {
				System.out.println(user.getUsername());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			MybatisUtil.closeSession(session);
		}
	}

	@Test
	public void testAdd(){
		
		SqlSession session = null;
		try {
			session = MybatisUtil.createSession();
			User user = new User();
			user.setNickname("八戒");
			user.setPassword("123456");
			user.setType(1);
			user.setUsername("猪八戒");
			
			//获取UserMapper接口对象
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.add(user);
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			MybatisUtil.closeSession(session);
		}
	}
}

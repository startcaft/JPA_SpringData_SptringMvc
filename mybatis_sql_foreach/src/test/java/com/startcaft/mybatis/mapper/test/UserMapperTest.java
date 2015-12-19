package com.startcaft.mybatis.mapper.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
 * 映射文件中的<foreach>标签
 * 
 * 如何向sql中传递一个List集合或者数组，mybatis使用foreach解析
 * 
 * 需求案例：在用户查询列表和查询总数的statement中，增加多个id的输入查询，SQL语句如下：
 * 
 * SELECT * from USER where (id=1 or id=2 or id=3);
 * 或者
 * SELECR * from USER where id int (1,2,3);
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
	public void findUserListTest(){
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			
			User user = new User();
			user.setSex("2");
			
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(1);
			ids.add(2);
			ids.add(3);
			
			UserQueryVo query = new UserQueryVo();
			query.setUser(user);
			query.setIds(ids);
			
			//List<User> lists = mapper.findUserList(null);//查询所有的
			//int count = mapper.findUserListCount(null);
			//List<User> lists = mapper.findUserList(query);//查询sex='2'的
			//int count = mapper.findUserListCount(query);
			List<User> lists = mapper.findUserList(query);//查询sex='2'的并且id为1,2,3的
			int count = mapper.findUserListCount(query);
			
			System.out.println(count);
			System.out.println(lists);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

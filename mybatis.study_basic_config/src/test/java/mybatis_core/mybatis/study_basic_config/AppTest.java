package mybatis_core.mybatis.study_basic_config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mybatis.shop.model.User;
import mybatis.shop.util.MybatisUtil;

public class AppTest {
	
	@Test
	public void listTest(){
		
		SqlSession session = null;
		try {
			session = MybatisUtil.createSession();
			List<User> selectList = session.selectList("mybatis.shop.model.User.list", null);
			for (User user : selectList) {
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
	public void loadTest(){
		
		SqlSession session = null;
		try {
			session = MybatisUtil.createSession();
			User user = session.selectOne("mybatis.shop.model.User.load", 1);
			System.out.println(user.getNickname());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			MybatisUtil.closeSession(session);
		}
	}
	
	@Test
	public void addTest() {

		try {
			// 1.创建配置文件的输入流
			InputStream input = Resources.getResourceAsStream("mybatis_config.xml");
			// 2.创建SqlSessionFactory
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(input);
			// 3.创建SqlSession
			SqlSession session = factory.openSession();
			// 4.调用具体的mapper.xml映射文件(需要讲mapper文件加入到mybaits配置文件中)

			User user = new User();
			user.setUsername("孙悟空");
			user.setNickname("悟空");
			user.setPassword("123456");
			user.setType(0);

			session.insert("mybatis.shop.model.User.add", user);

			// 提交事物
			session.commit();
			// 关闭session
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete(){
		
		SqlSession session = null;
		try {
			session = MybatisUtil.createSession();
			session.delete("mybatis.shop.model.User.delete", 5);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();//遇到异常就回滚事物
		}
		finally{
			MybatisUtil.closeSession(session);
		}
	}
	
	@Test
	public void update(){
		
		SqlSession session = null;
		try {
			session = MybatisUtil.createSession();
			User user = new User();
			user.setUsername("孙悟空");
			user.setNickname("悟空");
			user.setPassword("5904395");
			user.setType(0);
			user.setId(3);
			session.update("mybatis.shop.model.User.update", user);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();//遇到异常就回滚事物
		}
		finally{
			MybatisUtil.closeSession(session);
		}
	}
}

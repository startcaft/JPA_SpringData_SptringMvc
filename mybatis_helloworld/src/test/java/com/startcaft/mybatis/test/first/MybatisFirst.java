package com.startcaft.mybatis.test.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.startcaft.mybatis.po.User;

public class MybatisFirst {

	// 根据id，得到一个User对象
	@Test
	public void findUserByIdTest() throws IOException {

		SqlSession session = null;
		try {
			// 加载mybatis基础配置文件
			InputStream inputSream = Resources.getResourceAsStream("mybatis_config.xml");

			// 创建SqlSessionFactory会话工厂，需要传入mybatis的配置信息
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputSream);

			// 通过会话工厂创建SqlSession对象
			session = factory.openSession();

			// 通过SqlSession对象操作数据库
			// selectOne第一个参数是:映射文件中statement的id，等于namespace + "." +
			// statement的id；
			// selectOne第二个参数是:指定和映射文件中statement设置的paramterType类型的参数；
			// selectOne的结果就是与映射文件中statement设置的resultType类型的对象；
			User user = session.selectOne("test.findUserById", 1);

			// 查询操作不需要事物的支持

			System.out.println(user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放SqlSession会话资源
			session.close();
		}
	}

	// 根据username，查询，可能返回多条记录
	@Test
	public void findUserByNameTest() throws IOException {

		SqlSession session = null;
		try {
			InputStream inputSream = Resources.getResourceAsStream("mybatis_config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputSream);
			session = factory.openSession();
			List<User> users = session.selectList("test.findUserByName", "小明");

			for (User user : users) {
				System.out.println(user.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放SqlSession会话资源
			session.close();
		}
	}

	// 新增user表的一条记录，并返回其主键
	@Test
	public void insertUserTest() throws IOException {

		SqlSession session = null;
		try {
			InputStream inputSream = Resources.getResourceAsStream("mybatis_config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputSream);
			session = factory.openSession();

			User user = new User();
			user.setUsername("王小军");
			user.setBirthday(new Date());
			user.setSex("1");
			user.setAddress("河南郑州");

			session.insert("test.insertUser", user);

			// 因为是insert操作，所以需要提交事物
			session.commit();

			// 插入记录之后，需要返回该条记录的主键
			System.out.println(user.getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放SqlSession会话资源
			session.close();
		}
	}

	// 删除一条user记录
	@Test
	public void deleteUserTest() throws IOException {

		SqlSession session = null;
		try {
			InputStream inputSream = Resources.getResourceAsStream("mybatis_config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputSream);
			session = factory.openSession();

			session.delete("test.deleteUser", 30);

			// 因为是delete操作，所以需要提交事物
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放SqlSession会话资源
			session.close();
		}
	}

	// 更新一条user记录
	@Test
	public void updateUserTest() throws IOException {

		SqlSession session = null;
		try {
			InputStream inputSream = Resources.getResourceAsStream("mybatis_config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputSream);
			session = factory.openSession();
			
			User user = new User();
			user.setUsername("王晓军");
			user.setBirthday(new Date());
			user.setAddress("中国北京");
			user.setSex("2");
			user.setId(28);
			
			session.update("test.updateUser", user);

			// 因为是update操作，所以需要提交事物
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放SqlSession会话资源
			session.close();
		}
	}
}

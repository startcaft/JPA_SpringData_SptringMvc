package com.startcaft.mybatis.test.first;

import java.io.IOException;
import java.io.InputStream;
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

	//根据username，查询，可能返回多条记录
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
}

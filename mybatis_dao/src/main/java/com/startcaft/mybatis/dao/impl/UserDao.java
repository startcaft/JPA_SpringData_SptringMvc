package com.startcaft.mybatis.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.startcaft.mybatis.dao.IUserDao;
import com.startcaft.mybatis.po.User;

/**
 * 原始DAO的开发方式充满了重复代码
 * @author Administrator
 *
 */
public class UserDao implements IUserDao {
	
	//需要向dao实现类注入SqlSessionFactory对象，这里通过构造函数注入
	private SqlSessionFactory factory;
	
	public UserDao(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public User findUserById(int id) throws Exception {
		
		SqlSession session = null;
		User user = null;
		try {
			session = factory.openSession();
			user = session.selectOne("test.findUserById", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return user;
	}

	public int insertUser(User user) throws Exception {
		
		SqlSession session = null;
		int id = 0;
		try {
			session = factory.openSession();
			id = session.insert("test.insertUser", user);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		finally{
			session.close();
		}
		return id;
	}

	public void deleteUserById(int id) throws Exception {
		
		SqlSession session = null;
		try {
			session = factory.openSession();
			session.insert("test.deleteUser", id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		finally{
			session.close();
		}
	}

}

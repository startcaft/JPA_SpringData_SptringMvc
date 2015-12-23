package com.startcaft.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.startcaft.mybatis.dao.IUserDao;
import com.startcaft.mybatis.po.User;

public class UserDao extends SqlSessionDaoSupport implements IUserDao {
	
	public User findUserById(int id) throws Exception {
		
		SqlSession session = null;
		User user = null;
		try {
			//session = factory.openSession();
			//继承SqlSessionDaoSupport类的获取SqlSession的方式
			session = this.getSqlSession();
			user = session.selectOne("test.findUserById", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//而且，也不需要手动关闭SqlSession对象了
		//finally{
		//	session.close();
		//}
		return user;
	}
}

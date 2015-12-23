package com.startcaft.mybatis.dao;

import com.startcaft.mybatis.po.User;

/**
 * dao接口，用户管理
 */
public interface IUserDao {
	
	public User findUserById(int id) throws Exception;
}

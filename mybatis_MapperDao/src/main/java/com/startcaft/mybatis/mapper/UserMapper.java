package com.startcaft.mybatis.mapper;

import java.util.List;

import com.startcaft.mybatis.po.User;

/**
 * mapper接口，用户管理，相当于dao接口
 */
public interface UserMapper {
	
	public User findUserById(int id) throws Exception;
	
	public void insertUser(User user) throws Exception;
	
	public void deleteUser(int id) throws Exception;
	
	public List<User> findUserByname(String name) throws Exception;
}

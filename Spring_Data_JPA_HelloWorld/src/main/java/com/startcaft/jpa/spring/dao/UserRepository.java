package com.startcaft.jpa.spring.dao;

import org.springframework.data.repository.Repository;
import com.startcaft.jpa.entity.User;

public interface UserRepository extends Repository<User, Integer> {
	
	//根据username获取对应的User
	public User getByUsername(String username);
}

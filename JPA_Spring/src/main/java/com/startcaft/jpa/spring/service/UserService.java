package com.startcaft.jpa.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.jpa.entity.User;
import com.startcaft.jpa.spring.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void saveNewUser(User user1,User user2){
		
		userDao.save(user1);
		
		int i = 10 / 0;//异常
		
		userDao.save(user2);
	}
}

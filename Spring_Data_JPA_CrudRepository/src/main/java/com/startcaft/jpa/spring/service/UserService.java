package com.startcaft.jpa.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.jpa.entity.User;
import com.startcaft.jpa.spring.dao.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	public void saveUserList(List<User> users){
		
		userRepo.save(users);
	}
}

package com.startcaft.jpa.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startcaft.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}

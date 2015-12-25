package com.startcaft.jpa.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.startcaft.jpa.entity.User;

@Repository
public class UserDao {
	
	//如何获取到和当前事物关联到的EntityManager 对象呢？
	//通过@PersistenceContext注解来标记成员变量
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(User user){
		
		entityManager.persist(user);
	}
}

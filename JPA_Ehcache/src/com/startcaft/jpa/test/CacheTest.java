package com.startcaft.jpa.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.jpa.entity.Customer;

public class CacheTest {

	private EntityManagerFactory entityFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	@Before
	public void init(){
		
		entityFactory = Persistence.createEntityManagerFactory("JPA_Ehcache");
		entityManager = entityFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	@After
	public void destory(){
		
		transaction.commit();
		entityManager.close();
		entityFactory.close();
	}
	
	@Test
	public void testSecondLevelCache(){
		
		Customer customer1 = entityManager.find(Customer.class, 1);
		transaction.commit();
		entityManager.close();
		
		entityManager = entityFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
		
		Customer customer2 = entityManager.find(Customer.class, 1);
	}
}

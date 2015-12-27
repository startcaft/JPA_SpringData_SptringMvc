package com.startcaft.jpa.spring.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.startcaft.jpa.entity.Order;

/**
 * 
 */
public interface OrderRepository extends Repository<Order,Integer> {
	
	//where u.id > ?
	public List<Order> getByUserIdGreaterThan(Integer id);
}

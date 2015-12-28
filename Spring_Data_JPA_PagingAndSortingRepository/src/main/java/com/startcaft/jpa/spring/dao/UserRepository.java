package com.startcaft.jpa.spring.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.startcaft.jpa.entity.User;

/**
 * CrudRepository子接口，提供了一组CRUD的方法，继承该接口就可以直接使用了
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
}

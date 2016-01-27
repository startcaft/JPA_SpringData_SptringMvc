package com.startcaft.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.startcaft.mvc.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>,JpaSpecificationExecutor<Item> {
	
	@Query("select i from Item i where i.id = ?")
	public Item findById(Integer id);
}

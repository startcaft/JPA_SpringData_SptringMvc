package com.startcaft.jpa.spring.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.startcaft.jpa.entity.User;

/**
 * 对于复杂一点的查询来说，使用规范来定义方法显得比较无力，
 * 这时，可以使用@org.springframework.data.jpa.repository.Query注解来定义JPQL查询语句
 */
public interface UserRepository extends Repository<User, Integer> {
	
	/*
	 * 使用@Query 注解可以自定义 JPQL 语句以实现更为灵活的查询
	 */
	@Query(value="select u from User u where u.id = (select max(u2.id) from User u2)")
	public User getMaxIdUser();
	
	
	//为 @Query 注解传递参数的方式1： 使用占位符 ?   方法参数必须按照占位符的顺序
	@Query("select u from User u where u.username = ? and u.id = ?")
	User queryAnnotationParams1(String username,Integer id);
	
	//为 @Query 注解传递参数的方式2：使用参数命名的方式，并使用@Param("paramName") 注解来绑定方法参数
	@Query("select u from User u where u.username = :username and u.id = :id")
	User queryAnnotationParams2(@Param("id")Integer id,@Param("username")String username);
	
	//为 @Query 注解指定 like 查询参数时，占位符后面一定要跟索引【从1开始】，不然会解析JPQL报错
	//@Query("select u from User u where u.username like %?1%")
	@Query("select u from User u where u.username like %:username%")
	List<User> queryAnnotationLikeParam(@Param("username")String name);
	
	//设置 @Query 注解的属性nativeQuery为true，则可以使用本地SQL查询语句
	@Query(value="select count(*) from user",nativeQuery=true)
	long getTotalCount();
}

package com.startcaft.jpa.spring.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.startcaft.jpa.entity.User;

/*
 * 1，Repository 是一个空接口，即是一个标记接口
 * 2，若我们定义的DAO接口继承了Repository，则该接口会被IOC 容器识别为一个Repository Bean，
 * 		纳入到IOC 容器中，进而可以在该接口中定义满足一定规范的方法。
 * 
 * 3，实际上，也可以通过一个注解@RepositoryDefinition 注解来替代继承 Repository 接口。
 * 
 * 4，基础的Repository接口提供了最基本的数据访问功能，其子接口扩展了一些功能：
 * 		4-1，CrudRepository：继承Repository，实现了一组CRUD相关的方法；
 * 		4-2，PagingAndSortingRepository，继承了CrudRepository，实现了一组分页排序相关的方法；
 * 		4-3，JpaRepository：继承PagingAndSortingRepository，实现了一组JPA规范相关的方法；
 * 		
 * 		4-4，自定义的XxxxRepository需要继承JpaRepository，这样的自定义XxxxRepository接口就具有了
 * 			通用的数据访问控制层的能力；
 * 		
 * 		4-5：JpaSpecificationExecutor：不属于Repository，实现了一组JPA Criteria查询相关的方法；
 */
/**
 * 在Repository系列接口中声明方法规范：
 * 1，方法不是随便声明的，需要符合一定的规范：
 * 		1-1：【查询方法以 find | get | read 开头】；
 * 		1-2：涉及到查询条件时，条件的属性【用条件关键字链接】，并且，【条件属性要首字母大写】；
 * 			Spring Data 支持的关键字：
 * 			And			Or			Between			LessThan		GreaterThan
 * 			After		Before		IsNull			IsNotNull		NotNull
 * 			Like		NotLike		StartingWith	EndingWith		Cotaining
 * 			OrderBy		Not			In				NotIn			TRUE
 * 			FALSE
 * 
 * 2，支持属性的级联查询，【如何级联查询时，有两个相同的属性,则会优先使用当前类的属性，而不是级联的属性】
 * 		【如若要使用级联属性查询，则属性之间使用下划线 _ 进行连接】
 */
//@RepositoryDefinition(domainClass=User.class,idClass=Integer.class)
public interface UserRepository extends Repository<User, Integer> {
	
	//根据username获取对应的User
	public User getByUsername(String username);
	
	//where username like '?%' and id < ?【username 以XXX开头，并且id小于多少】
	public List<User> getByUsernameStartingWithAndIdLessThan(String username,Integer id);
	
	//where id in (?,?,?) OR birthday < ?
	public List<User> getByIdInOrBirthdayLessThan(List<Integer> ids,Date birthday);
}

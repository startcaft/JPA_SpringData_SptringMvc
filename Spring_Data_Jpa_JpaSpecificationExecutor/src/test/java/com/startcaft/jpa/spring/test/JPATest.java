package com.startcaft.jpa.spring.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.startcaft.common.SexEum;
import com.startcaft.jpa.entity.User;
import com.startcaft.jpa.spring.dao.UserRepository;
import com.startcaft.jpa.spring.service.UserService;

public class JPATest {

	private ApplicationContext context;
	private UserRepository respotiry;
	private UserService service;

	@Before
	public void init() {

		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		respotiry = (UserRepository) context.getBean("userRepository");
		service = (UserService) context.getBean("userService");
	}

	@Test
	public void testDataSource() {

		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}
	
	/**
	 * 目标：实现带查询条件的分页。
	 * 调用JpaSpecificationExecytor的Page<T> findAll(Specification<T> spec,Pageable pageable)
	 * Specification：封装了JPA Criteria 查询的查询条件
	 * Pageable：封装了请求分页，以及排序的信息
	 */
	@Test
	public void testJpaSpecificationExecutor(){
		
		Pageable pageable = new PageRequest(3-1, 5);//从第三页开始，每页5条记录
		
		//通常使用Specification 的匿名内部类
		Specification<User> spec = new Specification<User>() {
			
			/**
			 * 其中
			 * root：代表查询的实体类
			 * query：可以从中可得到Root 对象，即告知JPA Criteria 查询要查询哪一个实体类，
			 * 		还可以来添加查询条件，还可以结合EntityManager对象 得到最终查询的TypeQuery对象。
			 * cb: CriteriaBuilder对象，用于创建Criteria 相关对象的工厂，当然可以从中或起到 Predicate 对象
			 * 
			 * @return : Predicate类型，代表一个查询条件
			 */
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Path path = root.get("id");
				Predicate predicate = cb.gt(path, 5);
				return predicate;
			}
		};
		
		Page<User> page = respotiry.findAll(spec, pageable);
		
		System.out.println("总记录数:" + page.getTotalElements());
		System.out.println("总页数:" + page.getTotalPages());
		System.out.println("当前页码：" + (page.getNumber() + 1));
		System.out.println("当前页的List:" + page.getContent());
		System.out.println("当前页面的记录数:" + page.getNumberOfElements());
	}
}

package com.startcaft.jpa.spring.dao;



import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.startcaft.jpa.entity.User;

/**
 * Spring_Data_JPA提供两种完成insert，update，delete操作的方式：
 * 1，可以通过Repository系列接口提供的相关方法来完成增加，删除，修改操作；
 * 2，可以通过定制JPQL的方式来完成，update，delete操作【JPQL和HQL都是不支持insert语句】；
 * 		【使用@Query 注解来自定义DML操作，配合@Modifying 注解来告知Spring_Data这是一个update或delete语句】。
 * 		【update或delete，需要事务的支持】，此时就需要定义Service层的方法上添加事务支持；
 * 
 * 注意：【默认情况下，Spring_Data的没个方法上都有一个事务，但是是只读事务，无法完成insert,delete,update操作】
 */
public interface UserRepository extends Repository<User, Integer> {
	
	
	//报错，Not supported for DML operations
	@Query(value="update User u set u.birthday = :birthday where u.id = :id")
	public void updateUserBirthday(@Param("birthday")Date birthday,@Param("id")Integer id);
	
	//DML[insert,update,delete]需要事物支持哦
	@Modifying
	@Query(value="update User u set u.birthday = :birthday where u.id = :id")
	public void updateUserBirthday2(@Param("birthday")Date birthday,@Param("id")Integer id);
}

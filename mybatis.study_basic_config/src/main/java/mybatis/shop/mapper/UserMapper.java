package mybatis.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import mybatis.shop.model.User;

/**
 * Mybatis基于Mapper接口的编程方式
 */
public interface UserMapper {
	
	public void add(User user);
	public void update(User user);
	public void delete(Integer id);
	public void load(Integer id);
	public List<User> list();
	
	/*
	 * 基于Annotation的写法，可以省去xml映射文件，不建议使用这种方式
	@Select("select * from t_user where id=#{value}")
	public void load(Integer id);
	*/
}

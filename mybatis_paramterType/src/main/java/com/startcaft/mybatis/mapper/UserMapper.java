package com.startcaft.mybatis.mapper;

import java.util.List;

import com.startcaft.mybaits.vo.UserQueryVo;
import com.startcaft.mybatis.po.User;

/**
 * mapper接口，用户管理，相当于dao接口
 */
public interface UserMapper {
	
	public List<User> findUserList(UserQueryVo userVo) throws Exception;
}

package com.startcaft.mybatis.mapper;


import java.util.List;

import com.startcaft.mybatis.po.User;

/**
 * 订单Mapper接口
 */
public interface OrdersMapper {
	
	public List<User> findUserAndItemsResultMap() throws Exception;
}

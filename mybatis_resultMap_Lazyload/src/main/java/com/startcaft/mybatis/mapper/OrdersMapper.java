package com.startcaft.mybatis.mapper;



import java.util.List;

import com.startcaft.mybatis.po.Orders;

/**
 * 订单Mapper接口
 */
public interface OrdersMapper {
	
	public List<Orders> findOrdersUserLazyload() throws Exception;
}

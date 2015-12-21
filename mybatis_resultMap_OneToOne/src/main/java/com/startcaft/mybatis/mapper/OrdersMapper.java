package com.startcaft.mybatis.mapper;


import java.util.List;

import com.startcaft.mybaits.vo.OrdersCutom;
import com.startcaft.mybatis.po.Orders;

/**
 * 订单Mapper接口
 */
public interface OrdersMapper {
	
	public List<OrdersCutom> findOrdersUser() throws Exception;
	public List<Orders> findOrdersUserResulrMap() throws Exception;
}

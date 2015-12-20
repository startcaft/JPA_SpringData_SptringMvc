package com.startcaft.mybaits.vo;

import com.startcaft.mybatis.po.User;

public class UserQueryVo {
	
	//在这包装所需要的查询条件
	
	//用户查询条件
	private User user;
	
	//订单查询条件
	//private Order order;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

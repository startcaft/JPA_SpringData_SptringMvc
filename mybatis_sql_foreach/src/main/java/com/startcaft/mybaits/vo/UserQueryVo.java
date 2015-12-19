package com.startcaft.mybaits.vo;

import java.util.List;

import com.startcaft.mybatis.po.User;

public class UserQueryVo {
	
	//在这包装所需要的查询条件
	
	//用户查询条件
	private User user;
	
	//传入的多个id
	private List<Integer> ids;
	
	//订单查询条件
	//private Order order;
	
	public List<Integer> getIds() {
		return ids;
	}
	
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

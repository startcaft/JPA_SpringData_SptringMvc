package com.startcaft.mybaits.vo;

import com.startcaft.mybatis.po.Orders;

/**
 * 订单扩展类,此类映射订单和用户查询的结果。
 * 让该类继承查询字段较多的pojo类。
 */
public class OrdersCutom extends Orders {

	private String username;
	private String address;
	private String sex;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}

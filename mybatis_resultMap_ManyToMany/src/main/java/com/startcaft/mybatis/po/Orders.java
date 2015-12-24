package com.startcaft.mybatis.po;

import java.util.Date;
import java.util.List;

public class Orders {
	
	private Integer id;
	private Integer user_id;
	private String number;
	private Date createtime;
	private String note;
	private User user;//订单对应的用户
	private List<Orderdetail> orderDetails;//订单包含的订单明细
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Orderdetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<Orderdetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}

package com.startcaft.mvc.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.startcaft.mvc.controller.validation.ValidGroup1;

public class ItemsVo {
	
	private Integer id;
	private String name;
	private double price;
	private String pic;
	private String detail;
	private Date createTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	//groups指定该校验所属的分组
	@Size(max=30,min=1,message="{item.name.length.error}",groups={ValidGroup1.class})
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@NotNull(message="{item.creatime.isNULL}")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

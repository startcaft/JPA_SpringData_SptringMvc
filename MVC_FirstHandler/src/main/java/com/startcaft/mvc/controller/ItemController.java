package com.startcaft.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.startcaft.mvc.entity.Item;

public class ItemController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<Item> items = new ArrayList<Item>();
		
		Item item = new Item();
		item.setCreateTime(new Date());
		item.setPrice(6000f);
		item.setName("联想笔记本");
		item.setDetail("Think Pad T430联想笔记本电脑");
		
		Item item2 = new Item();
		item2.setCreateTime(new Date());
		item2.setPrice(5000f);
		item2.setName("苹果手机");
		item2.setDetail("Iphone6苹果手机");
		
		items.add(item);
		items.add(item2);
		
		//返回ModelAndView
		ModelAndView mv = new ModelAndView();
		//相当于request.setAttribute()方法
		mv.addObject("items", items);
		//指定试图
		mv.setViewName("/WEB-INF/views/items/itemList.jsp");
		
		return mv;
	}
}

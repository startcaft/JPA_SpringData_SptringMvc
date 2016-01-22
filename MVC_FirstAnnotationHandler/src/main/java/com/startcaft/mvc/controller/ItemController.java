package com.startcaft.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.startcaft.mvc.entity.Item;

@Controller
public class ItemController {
	
	@RequestMapping("/queryItems.action")
	public ModelAndView queryItems(){
		
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
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("items", items);
		//mv.setViewName("/WEB-INF/views/items/itemList.jsp");
		mv.setViewName("items/itemList");
		return mv;
	}
	
	/*
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
	*/
}

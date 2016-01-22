package com.startcaft.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.startcaft.mvc.entity.Item;

public class ItemController1 implements HttpRequestHandler {

	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
		request.setAttribute("items", items);
		
		request.getRequestDispatcher("/WEB-INF/views/items/itemList.jsp").forward(request, response);
	}

}

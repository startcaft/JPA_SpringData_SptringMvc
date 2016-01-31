package com.startcaft.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.startcaft.mvc.vo.ItemsVo;

@Controller
public class JsonTestController {
	
	/*
	 * @RequestBody注解 将客户端请求的JSON串转换成对应的Java对象。
	 * @ResponseBody注解 将一个java对象转换成JSON串，然后输出到客户端。
	 */
	@RequestMapping("/requestJson.action")
	@ResponseBody
	public ItemsVo requestJson(@RequestBody ItemsVo itemVo){
		
		return itemVo;
	}
	
	@RequestMapping("/responseJson.action")
	@ResponseBody
	public ItemsVo responseJson(ItemsVo itemVo){
		
		return itemVo;
	}
}

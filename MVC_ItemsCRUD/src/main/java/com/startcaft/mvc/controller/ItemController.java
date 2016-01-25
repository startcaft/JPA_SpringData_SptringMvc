package com.startcaft.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.startcaft.mvc.entity.Item;
import com.startcaft.mvc.service.ItemService;
import com.startcaft.mvc.vo.ItemListVo;
import com.startcaft.mvc.vo.ItemsVo;

@Controller
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService service;

	@RequestMapping("/queryItems.action")
	public ModelAndView queryItems(
				@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
				ItemsVo itemVo) throws Exception {
		
		int pageNo = 1;
		//对pageNo的校验
		try {
			pageNo =Integer.parseInt(pageNoStr);
			if(pageNo < 1){
				pageNo = 1;
			}
		} catch (Exception e) {}
		
		Page<Item> page = service.findItemList(itemVo, pageNo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("page", page);
		mv.setViewName("items/itemList");
		return mv;
	}
	
	@RequestMapping("/editItems.action")
	public ModelAndView editItem(Integer id) throws Exception{
		
		Item currentItem = service.findItemById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentItem", currentItem);
		mv.setViewName("items/editItems");
		
		return mv;
	}
	
	@RequestMapping(value="/editItemsSubmit.action",method=RequestMethod.POST)
	public String editItemSubmit(ItemsVo itemVo) throws Exception{
		
		service.UpdateItem(itemVo);
		
		return "redirect:/items/queryItems.action";
	}
	
	/*
	 * 绑定数组 数据很简单
	 */
	@RequestMapping(value="/deleteItems.action",method=RequestMethod.POST)
	public String deleteItems(Integer[] itemsId) throws Exception{
		
		System.out.println(itemsId);
		
		//todo:deleteItems
		
		return "redirect:/items/queryItems.action";
	}
	
	/*
	 * 批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
	 * 这里演示的是绑定List<T>数据
	 */
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(
							@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
							ItemsVo itemVo) throws Exception{
		
		int pageNo = 1;
		//对pageNo的校验
		try {
			pageNo =Integer.parseInt(pageNoStr);
			if(pageNo < 1){
				pageNo = 1;
			}
		} catch (Exception e) {}
		
		Page<Item> page = service.findItemList(itemVo, pageNo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("page", page);
		mv.setViewName("items/editItemsQuery");
		return mv;
	}
	
	@RequestMapping(value="/editItemsAllSubmit.action",method=RequestMethod.POST)
	public String editItemsAllSubmit(ItemListVo itemListVo) throws Exception{
		
		
		//todo:deleteItems
		
		return "redirect:/items/queryItems.action";
	}
}

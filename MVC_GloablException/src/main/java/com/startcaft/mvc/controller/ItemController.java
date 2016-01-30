package com.startcaft.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.startcaft.mvc.controller.validation.ValidGroup1;
import com.startcaft.mvc.entity.Item;
import com.startcaft.mvc.exception.CustomException;
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
	
	
	/*
	 * @RequestParam注解 指定request的输入参数名称和方法形参进行绑定
	 * 		通过required属性指定该参数是否是必须的
	 * 		通过defaultValue属性指定参数的默认值，如果该参数没有传入的话，则使用默认值代替。
	 */
	@RequestMapping("/editItems.action")
	public ModelAndView editItem(
				@RequestParam(value="id",required=true)Integer id) throws Exception{
		
		Item currentItem = service.findItemById(id);
		
		//如果currentItem为null，则删除手动抛出一个异常
		if (currentItem == null) {
			throw new CustomException("指定的商品信息不存在");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentItem", currentItem);
		mv.setViewName("items/editItems");
		
		return mv;
	}
	
	/*
	 * 在需要校验的pojo前面添加@Validated注解，在需要校验的pojo的后面添加一个类型为BindingResult的参数。
	 * 注意，@Validated，BindingResult他们两个是配对出现的【一前一后】。
	 * 
	 * SpringMVC默认会对pojo数据进行回显，会自动将pojo数据放到request作用域中，key就等于pojo的类名(首字母小写)。
	 * 
	 * @ModelAttribute注解可以指定pojo回显到页面在request作用域中的key。
	 * 
	 * @ModelAttribute还可以将方法的返回值传到页面。
	 */
	@RequestMapping(value="/editItemsSubmit.action",method=RequestMethod.POST)
	public String editItemSubmit(
					@ModelAttribute("currentItem") @Validated(value={ValidGroup1.class}) ItemsVo itemVo,
					BindingResult bindingResult,
					Model model) throws Exception{
		
		//获取校验信息
		if(bindingResult.hasErrors()){
			//输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}
			
			//将错误信息传递到页面
			model.addAttribute("allErrors", allErrors);
			//校验出错，则返回到修改页面，并显示错误信息
			return "items/editItems";
		}
		
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

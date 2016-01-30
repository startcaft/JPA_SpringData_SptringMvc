package com.startcaft.mvc.service;


import org.springframework.data.domain.Page;

import com.startcaft.mvc.entity.Item;
import com.startcaft.mvc.vo.ItemsVo;

public interface ItemService {
	
	public Page<Item> findItemList(ItemsVo itemVo,int pageNo) throws Exception;
	
	public Item findItemById(Integer id) throws Exception;
	
	public void UpdateItem(ItemsVo itemVo);
}

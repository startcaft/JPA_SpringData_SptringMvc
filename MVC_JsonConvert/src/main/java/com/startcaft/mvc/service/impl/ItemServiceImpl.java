package com.startcaft.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.startcaft.mvc.dao.ItemRepository;
import com.startcaft.mvc.entity.Item;
import com.startcaft.mvc.service.ItemService;
import com.startcaft.mvc.vo.ItemsVo;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Transactional(readOnly=true)
	public Page<Item> findItemList(final ItemsVo itemVo,int pageNo) throws Exception {
		
		Specification<Item> spec = new Specification<Item>() {
			
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> pres = new ArrayList<Predicate>();
				Path<String> propertyPath = null;
				
				if(itemVo != null){
					if(!"".equals(itemVo.getName()) && itemVo.getName() != null){
						propertyPath = root.get("name");
						pres.add(cb.like(propertyPath, "%"+itemVo.getName()+"%"));
					}
				}
				
				Predicate[] array = new Predicate[pres.size()];
				
				return cb.and(pres.toArray(array));
			}
		};
		
		Pageable pageable = new PageRequest(pageNo - 1, 5);
		
		return itemRepo.findAll(spec, pageable);
	}
	
	@Transactional(readOnly=true)
	public Item findItemById(Integer id) throws Exception {
		
		return itemRepo.findById(id);
	}
	
	@Transactional
	public void UpdateItem(ItemsVo itemVo) {
		
		Item item = new Item();
		BeanUtils.copyProperties(itemVo, item);
		
		itemRepo.saveAndFlush(item);
	}

}

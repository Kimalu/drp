package com.Kimalu.drp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Kimalu.drp.domain.Item;
@Repository
public class ItemDao extends PageBaseDao<Item> {
	
	public Page<Item> getItemList(Page<Item> page){
		String hql="from Item";
		return this.getByQuery(page, hql, null);
	}
	public void delItems(String[] itemIds){
		String hql="delete from Item i where i.id in (:itemIds)";
		List<Integer> idList=new ArrayList<Integer>();
		for(String id:itemIds){
			idList.add(Integer.parseInt(id));
		}
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("itemIds", idList);
		
		int	count=createQuery(hql,paraMap).executeUpdate();
	}
	public Page<Item> queryItemListByName(Page<Item> page,String queryItemName){
		return this.getByCriteria(page, Restrictions.like("itemName", "%"+queryItemName+"%"));
	}
	
	public Item queryExitedItem(String itemNo){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("itemNo", itemNo);
		String hql="from Item item where item.itemNo=:itemNo";
		return (Item)this.createQuery(hql, paramMap).uniqueResult();
	}
}

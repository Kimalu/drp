package com.Kimalu.drp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Kimalu.drp.daoImpl.ItemDaoImpl;
import com.Kimalu.drp.domain.AbstractDataDict;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.util.DBUtil;

public class ItemService {
	private static ItemService itemService=new ItemService();
	private ItemService(){
		
	}
	
	public static ItemService getInstance(){
		return itemService;
	}
	private ItemDaoImpl itemDaoImpl=ItemDaoImpl.getInstance();
	
	public Page<Item> getItemListByQueryItemName(Page<Item> page,String queryItemName){
		if(queryItemName!=null&&!"".equalsIgnoreCase(queryItemName)){
			page.setList(itemDaoImpl.queryByItemName(page.getCurrentPageNo(), page.getPageSize(),queryItemName));
		}else{
			this.getItemList(page);
		}
		return page;
	}
	
	public void addItem(Item t) throws SQLException{
		Connection conn=DBUtil.getConnection();
		conn.setAutoCommit(false);
		itemDaoImpl.add(conn, t);
		conn.commit();
	}
	public List<AbstractDataDict> getDataDictList(String category){
		List<AbstractDataDict> ddlist=new ArrayList<AbstractDataDict>();
		if("itemUnit".equalsIgnoreCase(category)){
			ddlist=itemDaoImpl.queryDataDict("D");
		}
		if("itemCategory".equalsIgnoreCase(category)){
			ddlist=itemDaoImpl.queryDataDict("C");
		}
		return ddlist;
	}
	public Page<Item> getItemList(Page<Item> page){
		page.setList(itemDaoImpl.query(page.getCurrentPageNo(), page.getPageSize()));
		return page;
	}
	
	
	public int getTotalNum(String queryString){
		return itemDaoImpl.getTotalNum("t_items",queryString);
	}
	
	public Item getItem(String id){
		return itemDaoImpl.queryById(id);
	}

	public void modifyItem(Item t){
		Connection conn=DBUtil.getConnection();
		itemDaoImpl.updateById(conn, t);
		DBUtil.close(conn);
	}
	public void del(String itemNo){
		Connection conn=DBUtil.getConnection();
		itemDaoImpl.del(conn, new String[]{itemNo});
		DBUtil.close(conn);
	}
}

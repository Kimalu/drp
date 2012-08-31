package com.Kimalu.drp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.ItemDao;
import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.domain.Item;

@Service
public class ItemService {
	@Autowired
	private ItemDao itemDao;

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void addItem(Item item){
		itemDao.save(item);
	}
	
	@Transactional
	public Page<Item> getItemList(Page<Item> page){
		return itemDao.getItemList(page);
	}
	
	@Transactional
	public void modifyItem(Item item){
		itemDao.update(item);
	}
	
	@Transactional
	public void delItems(String[] itemIds){
		itemDao.delItems(itemIds);
	}
	
	@Transactional
	public Item getItem(String id){
		return itemDao.getEntityById(id);
	}
	
	@Transactional
	public Page<Item> queryItemListByName(Page<Item> page,String itemName){
		return itemDao.queryItemListByName(page, itemName);
	}
	@Transactional
	public Item queryItemByItemNo(String itemNo){
		return itemDao.queryExitedItem(itemNo);
	}
	
	// public Page<Item> getItemListByQueryItemName(Page<Item> page,String
	// queryItemName){
	// if(queryItemName!=null&&!"".equalsIgnoreCase(queryItemName)){
	// page.setList(itemDaoImpl.queryByItemName(page.getCurrentPageNo(),
	// page.getPageSize(),queryItemName));
	// }else{
	// this.getItemList(page);
	// }
	// return page;
	// }
	//
	// public void addItem(Item t) throws SQLException{
	// Connection conn=DBUtil.getConnection();
	// conn.setAutoCommit(false);
	// itemDaoImpl.add(conn, t);
	// conn.commit();
	// }
	// public List<AbstractDataDict> getDataDictList(String category){
	// List<AbstractDataDict> ddlist=new ArrayList<AbstractDataDict>();
	// if("itemUnit".equalsIgnoreCase(category)){
	// ddlist=itemDaoImpl.queryDataDict("D");
	// }
	// if("itemCategory".equalsIgnoreCase(category)){
	// ddlist=itemDaoImpl.queryDataDict("C");
	// }
	// return ddlist;
	// }
	// public Page<Item> getItemList(Page<Item> page){
	// page.setList(itemDaoImpl.query(page.getCurrentPageNo(),
	// page.getPageSize()));
	// return page;
	// }
	//
	//
	// public int getTotalNum(String queryString){
	// return itemDaoImpl.getTotalNum("t_items",queryString);
	// }
	//
	// public Item getItem(String id){
	// return itemDaoImpl.queryById(id);
	// }
	//
	// public void modifyItem(Item t){
	// Connection conn=DBUtil.getConnection();
	// itemDaoImpl.updateById(conn, t);
	// DBUtil.close(conn);
	// }
	// public void del(String itemNo){
	// Connection conn=DBUtil.getConnection();
	// itemDaoImpl.del(conn, new String[]{itemNo});
	// DBUtil.close(conn);
	// }

}

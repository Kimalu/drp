package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

public class ItemDao4MySqlImpl implements ItemDao {

	public void addItem(Connection conn, Item item) {
		System.out.println("ItemDao4MySqlImpl.addItem");
	}

	public Item findItemById(Connection conn, String itemNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public PageModel findAllItem(Connection conn, String queryString,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyItem(Connection conn, Item item) {
		
	}

	public void modifyUploadFileNameField(Connection conn, String itemNo,
			String uploadFileName) {
		// TODO Auto-generated method stub
		
	}

}

package com.Kimalu.drp.dao;

import java.sql.Connection;
import java.util.List;

import com.Kimalu.drp.domain.Page;


public interface BaseDao2<T> {
	
	public String add(Connection conn,T t);
	
	public void del(Connection conn,T t);
	
	public List<T> query(Integer pageNO ,Integer pageSize);
	
	public T queryById(String id);
	public T queryById(int id);
	public void updateById(Connection conn,T t);
	public int getTotalNum(String tableName,String queryString);
	public void del(Connection conn,String[] Ids);
}

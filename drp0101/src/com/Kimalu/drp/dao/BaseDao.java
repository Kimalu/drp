package com.Kimalu.drp.dao;

import java.util.List;

import com.Kimalu.drp.domain.Page;


public interface BaseDao<T> {
	
	public String add(T t);
	
	public void del(T t);
	
	public List<T> query(Integer pageNO ,Integer pageSize);
	
	public T queryById(String id);
	public T queryById(int id);
	public void updateById(T t);
	public int getTotalNum(String tableName);
	public void del(String[] Ids);
}

package com.Kimalu.drp.queryparam;

import com.Kimalu.drp.dao.Page;

public class QueryParam<T> {

	private Page<T> page=new Page<T>();
	
	public Page<T> getPage(){
		return page;
	}
	public int getPageNo(){
		return page.getPageNo();
	}
	public void setPageNo(int pageNo){
		page.setPageNo(pageNo);
	}
	
}

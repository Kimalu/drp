package com.Kimalu.drp.dao;

import java.util.List;

public class Page<T> {
	
	public static final int MIN_PAGESIZE = 1;
    //public static final int DEFAULT_PAGESIZE = 15;
	public static final int MAX_PAGESIZE = 200;
	
	private int PageNo=1;
	private long totalNum=-1l;
	private int pageSize=4;
	private List<T> list;
	private long totalPageNum;

	public long getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(long totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getPageNo() {
		return PageNo;
	}
	
	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		if(totalNum>0){
			this.totalNum = totalNum;
			this.totalPageNum=(this.totalNum+this.pageSize-1)/this.pageSize;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	public int getFirst(){
		return (PageNo-1)*pageSize;
	}
}

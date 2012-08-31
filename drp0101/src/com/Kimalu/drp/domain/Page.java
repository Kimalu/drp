package com.Kimalu.drp.domain;

import java.util.List;

public class Page<T> {
private int currentPageNo;
private int totalNum;
private int pageSize;
private List<T> list;
private int totalPageNum;
public int getTotalPageNum() {
	return totalPageNum;
}
public void setTotalPageNum(int totalPageNum) {
	this.totalPageNum = totalPageNum;
}
public int getCurrentPageNo() {
	return currentPageNo;
}
public void setCurrentPageNo(int currentPageNo) {
	this.currentPageNo = currentPageNo;
}
public int getTotalNum() {
	return totalNum;
}
public void setTotalNum(int totalNum) {
	this.totalNum = totalNum;
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

}

package com.Kimalu.drp.service;

import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.dao.FiscalYearPeriodDao;
import com.Kimalu.drp.daoImpl.FiscalYearPeriodDaoImpl;
import com.Kimalu.drp.domain.FiscalYearPeriod;
import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.domain.User;

public class FiscalYearPeriodService {
	
	private static FiscalYearPeriodService fyps=new FiscalYearPeriodService();
	private FiscalYearPeriodService(){
		
	}
	public static FiscalYearPeriodService getInstance(){
		return fyps;
	}
	private AbstractBaseDao<FiscalYearPeriod> fypDao=FiscalYearPeriodDaoImpl.getInstance();
	public String addFiscalYearPeriod(FiscalYearPeriod fyp){
		return fypDao.add(fyp);
	}
	public List<FiscalYearPeriod> queryFiscalYearPeriod(){
		return fypDao.query(null,null);
	}
	public List<FiscalYearPeriod> queryFiscalYearPeriod(Integer pageNo,Integer pageSize){
		return fypDao.query(pageNo,pageSize);
	}
	public Page<FiscalYearPeriod> queryUserListByPage(Page<FiscalYearPeriod> page){
		page.setList(this.queryFiscalYearPeriod(page.getCurrentPageNo(),page.getPageSize()));
		return page;
	}
	public FiscalYearPeriod queryById(int fypId){
		FiscalYearPeriod fyp=fypDao.queryById(fypId);
		return fyp;
	} 
	public int getTotalNum(){
		return fypDao.getTotalNum("t_fiscal_year_period");
	}

}

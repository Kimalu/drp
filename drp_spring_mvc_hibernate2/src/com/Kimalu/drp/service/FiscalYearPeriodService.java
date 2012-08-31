package com.Kimalu.drp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.FiscalYearPeriodDao;
import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.domain.FiscalYearPeriod;
@Service
public class FiscalYearPeriodService {
	@Autowired
	private FiscalYearPeriodDao fypDao;
	
	public FiscalYearPeriodDao getFypDao() {
		return fypDao;
	}
	public void setFypDao(FiscalYearPeriodDao fypDao) {
		this.fypDao = fypDao;
	}
	@Transactional
	public void addFiscalYearPeriod(FiscalYearPeriod fyp){
		fypDao.save(fyp);
	}
	@Transactional
	public Page<FiscalYearPeriod> getFYPList(Page<FiscalYearPeriod> page){
		return fypDao.getFYPList(page);
	}
	@Transactional
	public void modifyFYP(FiscalYearPeriod fyp){
		fypDao.update(fyp);
	}
	
	public FiscalYearPeriod get(int id){
		return fypDao.getEntityById(id);
	}

}

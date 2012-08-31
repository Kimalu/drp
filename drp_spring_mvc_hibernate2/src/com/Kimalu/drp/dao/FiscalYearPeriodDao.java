package com.Kimalu.drp.dao;

import org.springframework.stereotype.Repository;

import com.Kimalu.drp.domain.FiscalYearPeriod;
@Repository
public class FiscalYearPeriodDao extends PageBaseDao<FiscalYearPeriod> {
	public Page<FiscalYearPeriod> getFYPList(Page<FiscalYearPeriod> page ){
		String hql="from FiscalYearPeriod";
		return this.getByQuery(page,hql,null);
	}
}

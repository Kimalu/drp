package com.Kimalu.drp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.Kimalu.drp.domain.FiscalYearPeriod;
@Repository
public class FiscalYearPeriodDao extends PageBaseDao<FiscalYearPeriod> {
	public Page<FiscalYearPeriod> getFYPList(Page<FiscalYearPeriod> page ){
		String hql="from FiscalYearPeriod";
		return this.getByQuery(page,hql,null);
	}

	public FiscalYearPeriod getFYPByCurrentDate(Date date) {
		String hql="from FiscalYearPeriod fyp where fyp.beginDate<:currentDate1 and fyp.endDate>:currentDate2";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("currentDate1", date);
		paramMap.put("currentDate2", date);
		return (FiscalYearPeriod)this.createQuery(hql,paramMap).uniqueResult();
	}
}

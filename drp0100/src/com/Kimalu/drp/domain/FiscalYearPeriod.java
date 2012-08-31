package com.Kimalu.drp.domain;

import java.util.Date;

public class FiscalYearPeriod {
	private int id;
	private int fiscalYear;
	private int fiscalmonth;
	private Date beginDate;
	private Date endDate;
	private String periodStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public int getFiscalmonth() {
		return fiscalmonth;
	}
	public void setFiscalmonth(int fiscalmonth) {
		this.fiscalmonth = fiscalmonth;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPeriodStatus() {
		return periodStatus;
	}
	public void setPeriodStatus(String periodStatus) {
		this.periodStatus = periodStatus;
	}

}

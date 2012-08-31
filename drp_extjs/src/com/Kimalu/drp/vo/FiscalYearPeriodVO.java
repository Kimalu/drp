package com.Kimalu.drp.vo;
public class FiscalYearPeriodVO {
	private int id;
	private int fiscalYear;
	private int fiscalMonth;
	private String beginDate;
	private String endDate;
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
	public int getFiscalMonth() {
		return fiscalMonth;
	}
	public void setFiscalMonth(int fiscalMonth) {
		this.fiscalMonth = fiscalMonth;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPeriodStatus() {
		return periodStatus;
	}
	public void setPeriodStatus(String periodStatus) {
		this.periodStatus = periodStatus;
	}
	
}

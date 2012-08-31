package com.Kimalu.drp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "t_fiscal_year_period")
public class FiscalYearPeriod {
	@Id
	@TableGenerator(name = "idGen", table = "t_table_id", pkColumnName = "table_name", valueColumnName = "value", pkColumnValue = "t_fiscal_year_period", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGen")
	private int id;
	@Column(name = "fiscal_year")
	private int fiscalYear;
	@Column(name = "fiscal_period")
	private int fiscalMonth;
	@Column(name = "begin_date")
	@DateTimeFormat(iso=ISO.DATE)
	private Date beginDate;
	@Column(name = "end_date")
	@DateTimeFormat(iso=ISO.DATE)
	private Date endDate;
	@Column(name = "period_sts")
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

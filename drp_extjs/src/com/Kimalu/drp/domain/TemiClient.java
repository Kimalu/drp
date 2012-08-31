package com.Kimalu.drp.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue("TemiClient")
public class TemiClient extends Region {
	private String contactPerson;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="temiClientTypeId")
	private TemiClientType temiClientType;		//终端客户类型
	
	public TemiClient(){
		this.nodeType=this.TEMICLIENT;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public TemiClientType getTemiClientType() {
		return temiClientType;
	}
	public void setTemiClientType(TemiClientType temiClientType) {
		this.temiClientType = temiClientType;
	}
	
	
	
	
}

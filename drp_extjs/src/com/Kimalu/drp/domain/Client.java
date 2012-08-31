package com.Kimalu.drp.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue("Client")
public class Client extends Region {
	private String bankAcctNo;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="levelId")
	private ClientLevel clientLevel;
	
	public Client(){
		this.nodeType=this.CLIENT;
	}
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	public ClientLevel getClientLevel() {
		return clientLevel;
	}
	public void setClientLevel(ClientLevel clientLevel) {
		this.clientLevel = clientLevel;
	}
	

	
}

package com.Kimalu.drp.domain;

public class Client {

	private int id;
	private int pid;
	private String name;
	private String clientId;
	private String bankAcctNo;
	private String contactTel;
	private String address;
	private String zipCode;
	private String isLead;
	private String isClient;
	private ClientLevel clientLevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getIsLead() {
		return isLead;
	}
	public void setIsLead(String isLead) {
		this.isLead = isLead;
	}
	public String getIsClient() {
		return isClient;
	}
	public void setIsClient(String isClient) {
		this.isClient = isClient;
	}
	public ClientLevel getClientLevel() {
		return clientLevel;
	}
	public void setClientLevel(ClientLevel clientLevel) {
		this.clientLevel = clientLevel;
	}
}

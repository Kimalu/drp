package com.Kimalu.drp.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="t_client")
public class Client {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name="pId")
	private Client parentClient;
	private String name;
	//分销商代码
	private String clientId;
	private String bankAcctNo;
	private String contactTel;
	private String address;
	private String zipCode;
	private String isLead;
	private String isClient;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="levelId")
	private ClientLevel clientLevel;
//	@OneToMany(mappedBy="parentClient",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@OneToMany(mappedBy="parentClient",cascade=CascadeType.ALL)
	private Set<Client> chlidClients;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Client getParentClient() {
		return parentClient;
	}
	public void setParentClient(Client parentClient) {
		this.parentClient = parentClient;
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
	public Set<Client> getChlidClients() {
		return chlidClients;
	}
	public void setChlidClients(Set<Client> chlidClients) {
		this.chlidClients = chlidClients;
	}
}

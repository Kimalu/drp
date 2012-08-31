package com.Kimalu.drp.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name="t_client")
@Component
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,name="category")
@DiscriminatorValue("Region")
public class Region {
	@Transient
	public static final int REGION=1;
	@Transient
	public static final int CLIENT=2;
	@Transient
	public static final int TEMICLIENT=3;
	
	@Id
	@GeneratedValue(generator="myuuid")
	@GenericGenerator(name="myuuid",strategy="uuid")
	private String id;
	@ManyToOne
	@JoinColumn(name="pId")
	private Region parentClient;
	private String name;
	//分销商代码
	private String clientNo;
	private String contactTel;
	private String address;
	private String zipCode;
	private String isClient;
	@OneToMany(mappedBy="parentClient",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Region> chlidClients;
	
	protected int nodeType=REGION;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Region getParentClient() {
		return parentClient;
	}
	public void setParentClient(Region parentClient) {
		this.parentClient = parentClient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClientNo() {
		return clientNo;
	}
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
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
	public String getIsClient() {
		return isClient;
	}
	public void setIsClient(String isClient) {
		this.isClient = isClient;
	}
	public List<Region> getChlidClients() {
		return chlidClients;
	}
	public void setChlidClients(List<Region> chlidClients) {
		this.chlidClients = chlidClients;
	}
	public int getNodeType() {
		return nodeType;
	}
	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
}

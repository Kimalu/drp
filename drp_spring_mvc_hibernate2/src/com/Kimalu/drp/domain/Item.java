package com.Kimalu.drp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="t_items",uniqueConstraints=@UniqueConstraint(columnNames = { "itemNo" }))
public class Item {
	@Id
	@GeneratedValue
	private int id;
	private String itemNo;
	private String itemName;
	private String spec;
	private String pattern;
	private String uploadFileName;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="categoryId")
	private ItemCategory itemCategory=new ItemCategory();
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="unitId")
	private ItemUnit itemUnit=new ItemUnit();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public ItemCategory getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}
	public ItemUnit getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(ItemUnit itemUnit) {
		this.itemUnit = itemUnit;
	}
	
}

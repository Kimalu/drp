package com.Kimalu.drp.domain.sysconfig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_sys_menu")
public class SysMenu {
	@Id
	@GeneratedValue(generator="myuuid")
	@GenericGenerator(name="myuuid",strategy="uuid")
	private String id;
	private String nodeName;
	private String url;
	@ManyToOne
	@JoinColumn(name="pid")
	private SysMenu parentNode;
	private boolean isMainMenu;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public SysMenu getParentNode() {
		return parentNode;
	}
	public void setParentNode(SysMenu parentNode) {
		this.parentNode = parentNode;
	}
	public boolean isMainMenu() {
		return isMainMenu;
	}
	public void setMainMenu(boolean isMainMenu) {
		this.isMainMenu = isMainMenu;
	}

}

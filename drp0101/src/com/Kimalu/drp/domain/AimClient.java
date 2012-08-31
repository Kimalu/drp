package com.Kimalu.drp.domain;


/**
 * 需方客户
 * @author Administrator
 *
 */
public class AimClient {
	
	//主键
	private int id;
	
	//终端或分销商的代码
	private String temiOrClientId;
	
	//名称
	private String name;
	
	//级别ID 
	private String levelId;
	
	//级别名称
	private String levelName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemiOrClientId() {
		return temiOrClientId;
	}

	public void setTemiOrClientId(String temiOrClientId) {
		this.temiOrClientId = temiOrClientId;
	}
	
}

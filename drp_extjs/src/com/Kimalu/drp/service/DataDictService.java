package com.Kimalu.drp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.DataDictDao;
import com.Kimalu.drp.domain.AbstractDataDict;
import com.Kimalu.drp.domain.ClientLevel;
import com.Kimalu.drp.domain.ItemCategory;
import com.Kimalu.drp.domain.ItemUnit;
import com.Kimalu.drp.domain.TemiClientType;

@Service
public class DataDictService {
	@Autowired
	private DataDictDao ddd;

	public DataDictDao getDdd() {
		return ddd;
	}

	public void setDdd(DataDictDao ddd) {
		this.ddd = ddd;
	}
	@Transactional
	public List<AbstractDataDict> getDataDictList(String type) {
		String className = "";
		if ("unit".equalsIgnoreCase(type)) {
			className = ItemUnit.class.getSimpleName();
		} else if ("category".equalsIgnoreCase(type)) {
			className=ItemCategory.class.getSimpleName();
		}else if("clientLevel".equalsIgnoreCase(type)){
			className=ClientLevel.class.getSimpleName();
		}else if("temiClientType".equalsIgnoreCase(type)){
			className=TemiClientType.class.getSimpleName();
		}
		return ddd.getDataDictList(className);
	}
	@Transactional
	public List<AbstractDataDict> getDataDictList(Class c) {
		String className = c.getSimpleName();
		return ddd.getDataDictList(className);
	}
	@Transactional
	public void saveDataDict(AbstractDataDict add){
		ddd.save(add);
	}
	
	public AbstractDataDict getDataDict(String id){
		return ddd.getEntityById(id);
	}
}

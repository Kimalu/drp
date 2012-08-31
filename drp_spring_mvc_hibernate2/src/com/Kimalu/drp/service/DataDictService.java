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
		}
		return ddd.getDataDictList(className);
	}
	
	public AbstractDataDict getDataDict(int id){
		return ddd.getEntityById(id);
	}
}

package com.Kimalu.drp.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.Kimalu.drp.domain.AbstractDataDict;

@Repository
public class DataDictDao extends BaseDao<AbstractDataDict> {
	
	public List<AbstractDataDict> getDataDictList(String className){
		StringBuffer queryString=new StringBuffer("from ");
		queryString.append(className);
		Query query=this.createQuery(queryString.toString(), null);
		return query.list();
	}

}

package com.Kimalu.drp.dao.basedata;

import org.springframework.stereotype.Repository;

import com.Kimalu.drp.dao.BaseDao;
import com.Kimalu.drp.domain.Region;
@Repository
public class RegionDao extends BaseDao<Region> {
	
	public Region getClientTreeRoot(){
		String hql="from Region r where r.parentClient.id is null";
		return (Region)this.createQuery(hql, null).uniqueResult();
	}
}

package com.Kimalu.drp.dao.flowcard;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.PageBaseDao;
import com.Kimalu.drp.domain.Region;
@Repository
public class AimClientDao extends PageBaseDao<Region> {
	
	public Page<Region> getAimClientList(Page<Region> page){
		String hql="from Region r where r.isClient=:isClient";
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("isClient", "Y");
		return this.getByQuery(page, hql, paraMap);
	}
	

	public Page<Region> queryAimClientListByName(Page<Region> page,String queryAimClientName){
		return this.getByCriteria(page, Restrictions.like("name", "%"+queryAimClientName+"%"),Restrictions.like("isClient", "Y"));
	}
	
}

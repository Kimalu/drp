package com.Kimalu.drp.dao.basedata;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.PageBaseDao;
import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.TemiClient;

@Repository
public class TemiClientDao extends PageBaseDao<TemiClient> {
	public Region getTemiClientTreeRoot(){
		String hql="from Region a where a.parentClient.id is null";
		return (Region)this.createQuery(hql, null).uniqueResult();
	}
	public Page<TemiClient> getTemiClientList(Page<TemiClient> page){
		String hql="from TemiClient c where c.isClient=:isClient";
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("isClient", "Y");
		return this.getByQuery(page, hql, paraMap);
	}
	public Page<TemiClient> getTemiClietListByName(Page<TemiClient> page,String queryByClientName){
		return this.getByCriteria(page, Restrictions.like("name", "%"+queryByClientName+"%"),Restrictions.like("isClient", "Y"));
	}	
}

package com.Kimalu.drp.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.Client;

@Repository
public class ClientDao extends PageBaseDao<Client> {
	
	public Region getClientTreeRoot(){
		String hql="from AbstractClient a where a.parentClient.id is null";
		return (Region)this.createQuery(hql, null).uniqueResult();
	}
	public Page<Client> getClientList(Page<Client> page){
		String hql="from Client c where c.isClient=:isClient";
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("isClient", "Y");
		return this.getByQuery(page, hql, paraMap);
	}
	public Page<Client> getClietListByName(Page<Client> page,String queryByClientName){
		return this.getByCriteria(page, Restrictions.like("name", "%"+queryByClientName+"%"),Restrictions.like("isClient", "Y"));
	}	

}

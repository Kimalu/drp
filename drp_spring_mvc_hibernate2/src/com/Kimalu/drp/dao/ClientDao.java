package com.Kimalu.drp.dao;

import org.springframework.stereotype.Repository;

import com.Kimalu.drp.domain.Client;

@Repository
public class ClientDao extends BaseDao<Client> {
	
	public Client getClientTreeRoot(){
		String hql="from Client c where c.parentClient.id is null";
		return (Client)this.createQuery(hql, null).uniqueResult();
	}
		

}

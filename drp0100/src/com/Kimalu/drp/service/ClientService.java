package com.Kimalu.drp.service;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.daoImpl.ClientDaoImpl;
import com.Kimalu.drp.daoImpl.ClientTreeReader;
import com.Kimalu.drp.domain.Client;

public class ClientService {
	private static ClientService clientService=new ClientService();
	private ClientService(){
		
	}
	public static ClientService getInstance(){
		return clientService;
	}

	private AbstractBaseDao<Client> clientDao=ClientDaoImpl.getInstance();
	private ClientDaoImpl cdi=ClientDaoImpl.getInstance();
	public String getClientTree(){
		ClientTreeReader ctr=new ClientTreeReader();
		return ctr.getHtmlTree();
	}
	public Client queryById(int id){
		return cdi.queryById(id);
	}
	public void updateClient(Client c){
		cdi.updateById(c);
	}
}

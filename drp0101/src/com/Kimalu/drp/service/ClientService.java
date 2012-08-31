package com.Kimalu.drp.service;

import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.daoImpl.ClientDaoImpl;
import com.Kimalu.drp.daoImpl.ClientTreeReader;
import com.Kimalu.drp.domain.Client;
public class ClientService{
	private static ClientService clientService=new ClientService();
	private ClientService(){
		
	}
	public static ClientService getInstance(){
		return clientService;
	}

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
	public void addClient(Client c){
		cdi.add(c);
	}
	public void del(Client c){
		cdi.del(c);
	}
	public int getTotalNum(String queryString){
		return cdi.getTotalNum("t_client",queryString);
	}
	public List<Client> getClientList(){
		return cdi.getClientList();
	}
}

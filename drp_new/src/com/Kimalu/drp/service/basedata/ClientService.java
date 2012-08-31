package com.Kimalu.drp.service.basedata;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.ClientDao;
import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.basedata.RegionDao;
import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.Client;

@Service
public class ClientService {
	@Autowired
	private ClientDao clientDao;
	
	public Element buildClientDocument(Element pNode,Region c){
		Element node=pNode.addElement("tree");
		node.addAttribute("text", c.getName());
		if(c.getIsClient().equalsIgnoreCase("Y")){
			node.addAttribute("action","ClientController.do?command=showClientCRUD&id="+c.getId());
		}else if(c.getIsClient().equalsIgnoreCase("N")){
			node.addAttribute("action","ClientController.do?command=showClientNodeCRUD&id="+c.getId());
		}else{
			node.addAttribute("action","javascript:alert('该节点数据有误')");
		}
		node.addAttribute("target", "clientDispAreaFrame");
		if(c.getChlidClients().size()!=0){
			for(Region tempc:c.getChlidClients()){
				buildClientDocument(node,tempc);
			}
		}
		return node;
	}
	@Transactional
	public void addClient(Client client){
		clientDao.save(client);
	}
	@Transactional
	public void delClientAndChildrenById(Client c){
		clientDao.del(c);
	}
	@Transactional
	public void modifyClient(Client c){
		clientDao.update(c);
	}
	@Transactional
	public Page<Client> getClietList(Page<Client> page){
		return clientDao.getClientList(page);
	}
	@Transactional
	public Page<Client> getClietListByName(Page<Client> page,String queryByClientName){
		return clientDao.getClietListByName(page, queryByClientName);
	}
	@Transactional
	public Client getClientById(String id) {
		return clientDao.getEntityById(id);
	}
	
	
}

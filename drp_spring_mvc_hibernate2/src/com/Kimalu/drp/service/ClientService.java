package com.Kimalu.drp.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.ClientDao;
import com.Kimalu.drp.domain.Client;

@Service
public class ClientService {
	@Autowired
	private ClientDao clientDao;
	
	@Transactional
	public Client getClientTreeRoot(){
		Client clientRoot= clientDao.getClientTreeRoot();
		return clientRoot;
	}
	@Transactional
	public String getClientTreeXML(){
		Client clientRoot= clientDao.getClientTreeRoot();
		Document dom=DocumentHelper.createDocument();
		Element root=dom.addElement("tree");
		buildClientDocument(root,clientRoot);
		return dom.asXML();
	}
	
	public Element buildClientDocument(Element pNode,Client c){
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
			for(Client tempc:c.getChlidClients()){
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
	public Client getClientById(int id){
		return clientDao.getEntityById(id);
	}
	@Transactional
	public void delClientAndChildrenById(Client c){
		clientDao.del(c);
	}
	@Transactional
	public void modifyClient(Client c){
		clientDao.update(c);
	}
	
}

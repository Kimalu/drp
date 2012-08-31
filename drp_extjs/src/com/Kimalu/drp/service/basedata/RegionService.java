package com.Kimalu.drp.service.basedata;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.basedata.RegionDao;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.Region;
@Service
public class RegionService {
	@Autowired
	private RegionDao acDao;
	@Transactional
	public void addRegion(Region ac){
		acDao.save(ac);
	}
	
	@Transactional
	public Region getRegionClientById(String id) {
		return acDao.getEntityById(id);
	}
	@Transactional
	public Region getClientTreeRoot(){
		Region clientRoot= acDao.getClientTreeRoot();
		return clientRoot;
	}
	@Transactional
	public String getClientTreeXML(){
		Region clientRoot= acDao.getClientTreeRoot();
		Document dom=DocumentHelper.createDocument();
		Element root=dom.addElement("tree");
		buildClientDocument(root,clientRoot);
		return dom.asXML();
	}
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
	public void delRegionAndChildrenById(Region r){
		acDao.del(r);
	}
	
	@Transactional
	public void modifyRegion(Region r){
		acDao.update(r);
	}
	
}

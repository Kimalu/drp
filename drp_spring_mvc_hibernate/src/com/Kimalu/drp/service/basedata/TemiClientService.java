package com.Kimalu.drp.service.basedata;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.ClientDao;
import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.basedata.TemiClientDao;
import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.TemiClient;

@Service
public class TemiClientService {
	@Autowired
	private TemiClientDao temiClientDao;
	
	@Transactional
	public Region getTemiClientTreeRoot(){
		Region clientRoot= temiClientDao.getTemiClientTreeRoot();
		return clientRoot;
	}
	@Transactional
	public String getTemiClientTreeXML(){
		Region temiClientRoot= temiClientDao.getTemiClientTreeRoot();
		Document dom=DocumentHelper.createDocument();
		Element root=dom.addElement("tree");
		buildTemiClientDocument(root,temiClientRoot);
		return dom.asXML();
	}
	
	public void buildTemiClientDocument(Element pNode,Region c){
		if(c.getNodeType()==Region.CLIENT){
			return ;
		}
		Element node=pNode.addElement("tree");
		node.addAttribute("text", c.getName());
		if(c.getIsClient().equalsIgnoreCase("Y")){
			node.addAttribute("action","TemiClientController.do?command=showTemiClientCRUD&id="+c.getId());
		}else if(c.getIsClient().equalsIgnoreCase("N")){
			node.addAttribute("action","TemiClientController.do?command=showTemiClientNodeCRUD&id="+c.getId());
		}else{
			node.addAttribute("action","javascript:alert('该节点数据有误')");
		}
		node.addAttribute("target", "temiClientDispAreaFrame");
		if(c.getChlidClients().size()!=0){
			for(Region tempc:c.getChlidClients()){
				buildTemiClientDocument(node,tempc);
			}
		}
	}
	@Transactional
	public void addTemiClient(TemiClient c){
		temiClientDao.save(c);
	}
	@Transactional
	public void delTemiClientAndChildrenById(TemiClient c){
		temiClientDao.del(c);
	}
	@Transactional
	public void modifyTemiClient(TemiClient c){
		temiClientDao.update(c);
	}
	@Transactional
	public Page<TemiClient> getTemiClietList(Page<TemiClient> page){
		return temiClientDao.getTemiClientList(page);
	}
	@Transactional
	public Page<TemiClient> getTemiClietListByName(Page<TemiClient> page,String queryByClientName){
		return temiClientDao.getTemiClietListByName(page, queryByClientName);
	}
	@Transactional
	public TemiClient getTemiClientById(String id) {
		return temiClientDao.getEntityById(id);
	}
}

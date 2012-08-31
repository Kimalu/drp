package com.Kimalu.drp.dao.flowcard;

import org.springframework.stereotype.Repository;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.PageBaseDao;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.flowcard.FlowCard;

@Repository
public class FlowCardDao extends PageBaseDao<FlowCard> {
	
	public Page<FlowCard> getFlowCardList(Page<FlowCard> page){
		String hql="from FlowCard";
		return this.getByQuery(page, hql, null);
	}
	
	public void sessionClear(){
		this.getSession().clear();
	}
}

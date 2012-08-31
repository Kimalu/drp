package com.Kimalu.drp.dao;

import com.Kimalu.drp.domain.FlowCard;

public interface FlowCardDao {
	
	public void addFlowCardMaster(FlowCard flowCard);
	
	public void addFlowCardDetail(FlowCard flowCard);
	
	public String genVouNo();
}

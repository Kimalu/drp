package com.Kimalu.drp.dao.flowcard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.Kimalu.drp.dao.PageBaseDao;
import com.Kimalu.drp.domain.flowcard.FlowCardDetail;
@Repository
public class FlowCardDetailDao extends PageBaseDao<FlowCardDetail> {

	public List<FlowCardDetail> getFlowCardDetailList(String flowCardId) {
		String hql="from FlowCardDetail fcd where fcd.flowCard.fcId=:fcid";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("fcid", flowCardId);
		return (List<FlowCardDetail>)this.createQuery(hql, paramMap).list();
	}

	public void delFlowCardDetailByFlowCardId(String flowCardId) {
		String hql="delete FlowCardDetail fcd where fcd.flowCard.fcId=:fcid";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("fcid", flowCardId);
		this.createQuery(hql, paramMap).executeUpdate();
	}
}

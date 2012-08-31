package com.bjpowernode.drp.flowcard.service;

import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.PageModel;

public interface FlowCardService {
	
	/**
	 * 添加流向单
	 * @param flowCard
	 * @throws AppException
	 */
	public void addFlowCard(FlowCard flowCard) 
	throws AppException;
	
	/**
	 * 根据条件查询
	 * @param pageNo
	 * @param pageSize
	 * @param clientId
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws AppException
	 */
	public PageModel findFlowCardList(int pageNo, int pageSize,
			String clientId, String beginDate, String endDate)
	throws AppException;
	
	/**
	 * 删除流向单
	 * @param flowCardNo  
	 * @throws AppException
	 */
	public void delFlowCard(String[] flowCardNo)
	throws AppException;
	
	/**
	 * 送审流向单
	 * @param flowCardNo
	 * @throws AppException
	 */
	public void auditFlowCard(String[] flowCardNo)
	throws AppException;
	
	/**
	 * 根据流向单号查询
	 * 
	 * @param flowCardNo
	 * @return
	 * @throws AppException
	 */
	public FlowCard findFlowCardById(String flowCardNo)
	throws AppException;
	
	/**
	 * 修改流向单
	 * 
	 * @param flowCard
	 * @throws AppException
	 */
	public void modifyFlowCard(FlowCard flowCard)
	throws AppException;
}

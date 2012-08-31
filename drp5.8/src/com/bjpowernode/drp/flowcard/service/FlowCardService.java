package com.bjpowernode.drp.flowcard.service;

import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.PageModel;

public interface FlowCardService {
	
	/**
	 * �������
	 * @param flowCard
	 * @throws AppException
	 */
	public void addFlowCard(FlowCard flowCard) 
	throws AppException;
	
	/**
	 * ����������ѯ
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
	 * ɾ������
	 * @param flowCardNo  
	 * @throws AppException
	 */
	public void delFlowCard(String[] flowCardNo)
	throws AppException;
	
	/**
	 * ��������
	 * @param flowCardNo
	 * @throws AppException
	 */
	public void auditFlowCard(String[] flowCardNo)
	throws AppException;
	
	/**
	 * �������򵥺Ų�ѯ
	 * 
	 * @param flowCardNo
	 * @return
	 * @throws AppException
	 */
	public FlowCard findFlowCardById(String flowCardNo)
	throws AppException;
	
	/**
	 * �޸�����
	 * 
	 * @param flowCard
	 * @throws AppException
	 */
	public void modifyFlowCard(FlowCard flowCard)
	throws AppException;
}

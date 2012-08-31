package com.bjpowernode.drp.flowcard.dao;

import java.util.List;

import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.domain.FlowCardDetail;
import com.bjpowernode.drp.util.DaoException;

/**
 * �������ݷ��ʶ���ӿ�
 * @author Administrator
 *
 */
public interface FlowCardDao {
	
	/**
	 * �����������Ϣ
	 * @param flowCard
	 * @throws DaoException
	 */
	public void addFlowCardMaster(String flowCardNo, FlowCard flowCard)
	throws DaoException;
	
	/**
	 * ���������ϸ��Ϣ
	 * @param flowCardNo
	 * @param flowCardDetailList
	 * @throws DaoException
	 */
	public void addFlowCardDetail(String flowCardNo, List<FlowCardDetail> flowCardDetailList)
	throws DaoException;
	
	/**
	 * ���ɵ���
	 * @return yyyymmdd0000
	 * @throws DaoException
	 */
	public String genVouNo()
	throws DaoException;
	
	/**
	 * ��������ȡ�������б�
	 * @param pageNo
	 * @param pageSize
	 * @param clientId
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public List<FlowCard> findFlowCardList(int pageNo, int pageSize, String clientId, String beginDate, String endDate)
	throws DaoException;
	
	/**
	 * ��������ȡ�ü�¼��
	 * @param clientId
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public int getRecordCount(String clientId, String beginDate, String endDate)
	throws DaoException;
	
	/**
	 * �������򵥺�ɾ������
	 * @param flowCardNo
	 * @throws DaoException
	 */
	public void delFlowCardMaster(String[] flowCardNo)
	throws DaoException;
	
	/**
	 * �������򵥺�ɾ����ϸ��
	 * @param flowCardNo
	 * @throws DaoException
	 */
	public void delFlowCardDetail(String[] flowCardNo)
	throws DaoException;
	
	/**
	 * ��������
	 * @param flowCardNo
	 * @throws DaoException
	 */
	public void auditFlowCard(String[] flowCardNo)
	throws DaoException;
	
	/**
	 * �������򵥺Ų�ѯ����
	 * 
	 * @param flowCardNo
	 * @return
	 * @throws DaoException
	 */
	public FlowCard findFlowCardMasterById(String flowCardNo)
	throws DaoException;
	
	/**
	 * �������򵥺Ų�ѯ��ϸ��
	 * 
	 * @param flowCardNo
	 * @return
	 * @throws DaoException
	 */
	public List<FlowCardDetail> findFlowCardDetailByFlowCardNo(String flowCardNo)
	throws DaoException;
	
	/**
	 * �������򵥺�ɾ��������ϸ
	 * 
	 * @param flowCard
	 * @throws DaoException
	 */
	public void modifyFlowCardMaster(FlowCard flowCard)
	throws DaoException;
	
	/**
	 * �޸�������ϸ
	 * 
	 * @param flowCardDetailList
	 * @throws DaoException
	 */
	public void modifyFlowCardDetail(List<FlowCardDetail> flowCardDetailList)
	throws DaoException;;
}

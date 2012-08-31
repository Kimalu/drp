package com.bjpowernode.drp.flowcard.dao;

import java.util.List;

import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.domain.FlowCardDetail;
import com.bjpowernode.drp.util.DaoException;

/**
 * 流向单数据访问对象接口
 * @author Administrator
 *
 */
public interface FlowCardDao {
	
	/**
	 * 添加流向单主信息
	 * @param flowCard
	 * @throws DaoException
	 */
	public void addFlowCardMaster(String flowCardNo, FlowCard flowCard)
	throws DaoException;
	
	/**
	 * 添加流向单明细信息
	 * @param flowCardNo
	 * @param flowCardDetailList
	 * @throws DaoException
	 */
	public void addFlowCardDetail(String flowCardNo, List<FlowCardDetail> flowCardDetailList)
	throws DaoException;
	
	/**
	 * 生成单号
	 * @return yyyymmdd0000
	 * @throws DaoException
	 */
	public String genVouNo()
	throws DaoException;
	
	/**
	 * 根据条件取得流向单列表
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
	 * 根据条件取得记录数
	 * @param clientId
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public int getRecordCount(String clientId, String beginDate, String endDate)
	throws DaoException;
	
	/**
	 * 根据流向单号删除主表
	 * @param flowCardNo
	 * @throws DaoException
	 */
	public void delFlowCardMaster(String[] flowCardNo)
	throws DaoException;
	
	/**
	 * 根据流向单号删除明细表
	 * @param flowCardNo
	 * @throws DaoException
	 */
	public void delFlowCardDetail(String[] flowCardNo)
	throws DaoException;
	
	/**
	 * 送审流向单
	 * @param flowCardNo
	 * @throws DaoException
	 */
	public void auditFlowCard(String[] flowCardNo)
	throws DaoException;
	
	/**
	 * 根据流向单号查询主表
	 * 
	 * @param flowCardNo
	 * @return
	 * @throws DaoException
	 */
	public FlowCard findFlowCardMasterById(String flowCardNo)
	throws DaoException;
	
	/**
	 * 根据流向单号查询明细表
	 * 
	 * @param flowCardNo
	 * @return
	 * @throws DaoException
	 */
	public List<FlowCardDetail> findFlowCardDetailByFlowCardNo(String flowCardNo)
	throws DaoException;
	
	/**
	 * 根据流向单号删除流向单明细
	 * 
	 * @param flowCard
	 * @throws DaoException
	 */
	public void modifyFlowCardMaster(FlowCard flowCard)
	throws DaoException;
	
	/**
	 * 修改流向单明细
	 * 
	 * @param flowCardDetailList
	 * @throws DaoException
	 */
	public void modifyFlowCardDetail(List<FlowCardDetail> flowCardDetailList)
	throws DaoException;;
}

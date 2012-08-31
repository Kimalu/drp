package com.bjpowernode.drp.flowcard.service.impl;

import java.sql.Connection;
import java.util.List;

import com.bjpowernode.drp.flowcard.dao.FlowCardDao;
import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.service.FlowCardService;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.ConnectionManager;
import com.bjpowernode.drp.util.DaoException;
import com.bjpowernode.drp.util.PageModel;

public class FlowCardServiceImpl implements FlowCardService {
	
//	public void addFlowCard(FlowCard flowCard) 
//	throws AppException {
//		Connection conn = null;
//		try {
//			//得到与当前线程绑定的Connection
//			conn = ConnectionManager.getConnection();
//			//开启事务
//			ConnectionManager.setAutoCommit(conn, false);
//			
//			//取得FlowCardDao
//			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
//			
//			//得到单号
//			String flowCardNo = flowCardDao.genVouNo();
//
//			//添加流向单主信息
//			flowCardDao.addFlowCardMaster(flowCardNo, flowCard);
//			
//			//添加明细信息
//			flowCardDao.addFlowCardDetail(flowCardNo, flowCard.getFlowCardDetailList());
//			
//			//提交事务
//			ConnectionManager.commit(conn);
//		}catch(DaoException e) {
//			//回滚事务
//			ConnectionManager.rollback(conn);
//			throw new AppException("添加流向单失败！");
//		}finally {
//			//关闭Connection
//			ConnectionManager.closeConnection();	
//		}
//	}

//	public PageModel findFlowCardList(int pageNo, int pageSize,
//			String clientId, String beginDate, String endDate)
//			throws AppException {
//		Connection conn = null;
//		try {
//			conn = ConnectionManager.getConnection();
//			//取得FlowCardDao
//			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
//			
//			//取得流向单列表
//			List flowCardList = flowCardDao.findFlowCardList(pageNo, pageSize, clientId, beginDate, endDate);
//			
//			//取得记录数
//			int recordCount = flowCardDao.getRecordCount(clientId, beginDate, endDate);
//			
//			//拼装PageModel
//			PageModel pageModel = new PageModel();
//			pageModel.setList(flowCardList);
//			pageModel.setTotalRecords(recordCount);
//			pageModel.setPageNo(pageNo);
//			pageModel.setPageSize(pageSize);
//			return pageModel;
//		}catch(DaoException e) {
//			throw new AppException("查询流向单失败！");
//		}finally {
//			ConnectionManager.closeConnection();	
//		}
//	}
	
	public void addFlowCard(FlowCard flowCard) 
	throws AppException {
		try {
			//取得FlowCardDao
			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
			
			//得到单号
			String flowCardNo = flowCardDao.genVouNo();

			//添加流向单主信息
			flowCardDao.addFlowCardMaster(flowCardNo, flowCard);
			
			//添加明细信息
			flowCardDao.addFlowCardDetail(flowCardNo, flowCard.getFlowCardDetailList());
		}catch(DaoException e) {
			throw new AppException("添加流向单失败！");
		}
	}
	
	public PageModel findFlowCardList(int pageNo, int pageSize,
			String clientId, String beginDate, String endDate)
			throws AppException {
		try {
			//取得FlowCardDao
			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
			
			//取得流向单列表
			List flowCardList = flowCardDao.findFlowCardList(pageNo, pageSize, clientId, beginDate, endDate);
			
			//取得记录数
			int recordCount = flowCardDao.getRecordCount(clientId, beginDate, endDate);
			
			//拼装PageModel
			PageModel pageModel = new PageModel();
			pageModel.setList(flowCardList);
			pageModel.setTotalRecords(recordCount);
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			return pageModel;
		}catch(DaoException e) {
			throw new AppException("查询流向单失败！");
		}
	}

	public void delFlowCard(String[] flowCardNo) throws AppException {
		// TODO Auto-generated method stub
		
	}

	public void auditFlowCard(String[] flowCardNo) throws AppException {
		// TODO Auto-generated method stub
		
	}

	public FlowCard findFlowCardById(String flowCardNo) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyFlowCard(FlowCard flowCard) throws AppException {
		// TODO Auto-generated method stub
		
	}

}

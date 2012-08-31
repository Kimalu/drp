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
//			//�õ��뵱ǰ�̰߳󶨵�Connection
//			conn = ConnectionManager.getConnection();
//			//��������
//			ConnectionManager.setAutoCommit(conn, false);
//			
//			//ȡ��FlowCardDao
//			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
//			
//			//�õ�����
//			String flowCardNo = flowCardDao.genVouNo();
//
//			//�����������Ϣ
//			flowCardDao.addFlowCardMaster(flowCardNo, flowCard);
//			
//			//�����ϸ��Ϣ
//			flowCardDao.addFlowCardDetail(flowCardNo, flowCard.getFlowCardDetailList());
//			
//			//�ύ����
//			ConnectionManager.commit(conn);
//		}catch(DaoException e) {
//			//�ع�����
//			ConnectionManager.rollback(conn);
//			throw new AppException("�������ʧ�ܣ�");
//		}finally {
//			//�ر�Connection
//			ConnectionManager.closeConnection();	
//		}
//	}

//	public PageModel findFlowCardList(int pageNo, int pageSize,
//			String clientId, String beginDate, String endDate)
//			throws AppException {
//		Connection conn = null;
//		try {
//			conn = ConnectionManager.getConnection();
//			//ȡ��FlowCardDao
//			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
//			
//			//ȡ�������б�
//			List flowCardList = flowCardDao.findFlowCardList(pageNo, pageSize, clientId, beginDate, endDate);
//			
//			//ȡ�ü�¼��
//			int recordCount = flowCardDao.getRecordCount(clientId, beginDate, endDate);
//			
//			//ƴװPageModel
//			PageModel pageModel = new PageModel();
//			pageModel.setList(flowCardList);
//			pageModel.setTotalRecords(recordCount);
//			pageModel.setPageNo(pageNo);
//			pageModel.setPageSize(pageSize);
//			return pageModel;
//		}catch(DaoException e) {
//			throw new AppException("��ѯ����ʧ�ܣ�");
//		}finally {
//			ConnectionManager.closeConnection();	
//		}
//	}
	
	public void addFlowCard(FlowCard flowCard) 
	throws AppException {
		try {
			//ȡ��FlowCardDao
			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
			
			//�õ�����
			String flowCardNo = flowCardDao.genVouNo();

			//�����������Ϣ
			flowCardDao.addFlowCardMaster(flowCardNo, flowCard);
			
			//�����ϸ��Ϣ
			flowCardDao.addFlowCardDetail(flowCardNo, flowCard.getFlowCardDetailList());
		}catch(DaoException e) {
			throw new AppException("�������ʧ�ܣ�");
		}
	}
	
	public PageModel findFlowCardList(int pageNo, int pageSize,
			String clientId, String beginDate, String endDate)
			throws AppException {
		try {
			//ȡ��FlowCardDao
			FlowCardDao flowCardDao = (FlowCardDao)BeanFactory.getInstance().getBean(FlowCardDao.class);
			
			//ȡ�������б�
			List flowCardList = flowCardDao.findFlowCardList(pageNo, pageSize, clientId, beginDate, endDate);
			
			//ȡ�ü�¼��
			int recordCount = flowCardDao.getRecordCount(clientId, beginDate, endDate);
			
			//ƴװPageModel
			PageModel pageModel = new PageModel();
			pageModel.setList(flowCardList);
			pageModel.setTotalRecords(recordCount);
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			return pageModel;
		}catch(DaoException e) {
			throw new AppException("��ѯ����ʧ�ܣ�");
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

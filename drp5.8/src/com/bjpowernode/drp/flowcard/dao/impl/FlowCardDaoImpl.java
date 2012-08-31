package com.bjpowernode.drp.flowcard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bjpowernode.drp.basedata.domain.Client;
import com.bjpowernode.drp.flowcard.dao.FlowCardDao;
import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.domain.FlowCardDetail;
import com.bjpowernode.drp.sysmgr.domain.User;
import com.bjpowernode.drp.util.ConnectionManager;
import com.bjpowernode.drp.util.DaoException;
import com.bjpowernode.drp.util.generator.IDGenerator;

public class FlowCardDaoImpl implements FlowCardDao {

	public void addFlowCardMaster(String flowCardNo, FlowCard flowCard)
			throws DaoException {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("insert into t_flow_card_master ( ")
				   .append("flow_card_no, fiscal_year_period, opt_type, ") 
				   .append("client_id, recorder_id, opt_date, vou_sts)") 
				   .append("values (?, ?, ?, ?, ?, ?, ?)");
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setString(1, flowCardNo);
			pstmt.setInt(2, flowCard.getFiscalYearPeriod().getId());
			pstmt.setString(3, flowCard.getOptType());
			pstmt.setInt(4, flowCard.getClient().getId());
			pstmt.setString(5, flowCard.getRecorder().getUserId());
			pstmt.setTimestamp(6, new Timestamp(flowCard.getOptDate().getTime()));
			pstmt.setString(7, flowCard.getVouSts());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl.addFlowCardMaster()Ê§°Ü£¡");
			throw new DaoException(e);
		}finally {
			ConnectionManager.close(pstmt);
		}
	}
	
	public void addFlowCardDetail(String flowCardNo,
			List<FlowCardDetail> flowCardDetailList) throws DaoException {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("insert into t_flow_card_detail ( ")
		   .append("flow_card_detail_id, aim_client_id, item_no, ") 
		   .append("flow_card_no, opt_qty, adjust_flag) ") 
 		   .append("values (?, ?, ?, ?, ?, ?)");
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			for (Iterator<FlowCardDetail>  iter = flowCardDetailList.iterator(); iter.hasNext();) {
				FlowCardDetail flowCardDetail = (FlowCardDetail)iter.next();
				pstmt.setInt(1, (int)IDGenerator.getInstance().newID("t_flow_card_detail"));
				pstmt.setInt(2, flowCardDetail.getAimClient().getId());
				pstmt.setString(3, flowCardDetail.getItem().getItemNo());
				pstmt.setString(4, flowCardNo);
				pstmt.setDouble(5, flowCardDetail.getOptQty());
				pstmt.setString(6, flowCardDetail.getAdjustFlag());
				pstmt.addBatch();
			}
			//ÅúÁ¿Ö´ÐÐ
			pstmt.executeBatch();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl.addFlowCardDetail()Ê§°Ü£¡");
			throw new DaoException(e);
		}finally {
			ConnectionManager.close(pstmt);
		}
	}
	
	/**
	 * Éú³Éµ¥ºÅ
	 * @return yyyymmdd0000
	 * @throws DaoException
	 * 
	 * 200911190001
	 * 200911190002
	 */
	public String genVouNo() throws DaoException {
		String sql = "select max(flow_card_no) from t_flow_card_master where substr(flow_card_no, 1, 8)=?";
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String vouNo = currentDate + "0001";
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, currentDate);
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getLong(1) != 0) {
				vouNo = String.valueOf(rs.getLong(1) + 1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl.genVouNo()Ê§°Ü£¡");
			throw new DaoException(e);
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return vouNo;
	}

	public List<FlowCard> findFlowCardList(int pageNo, int pageSize,
			String clientId, String beginDate, String endDate)
			throws DaoException {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select * from ")
		.append("(")
			.append("select rownum rn, t.* from ")
			.append("(")
		    .append("select a.flow_card_no, b.client_id, b.name as client_name, c.user_name, a.opt_date " +
				"from t_flow_card_master a, t_client b, t_user c " +
				"where a.client_id=b.id and a.recorder_id=c.user_id and a.vou_sts='N' ");
				if (clientId != null && !"".equals(clientId)) {
					sbSql.append("and b.client_id='" + clientId + "' ");
				}
				sbSql.append("and to_char(a.opt_date, 'YYYY-MM-DD') between '")
					.append(beginDate)
					.append("' and '")
					.append(endDate)
					.append("'")
					.append(" order by a.flow_card_no ")
			.append(") t where rownum <=? ")
		.append(") ")
		.append("where rn > ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FlowCard> flowCardList = new ArrayList<FlowCard>();
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, pageNo * pageSize);
			pstmt.setInt(2, (pageNo - 1) * pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FlowCard flowCard = new FlowCard();
				flowCard.setFlowCardNo(rs.getString("flow_card_no"));
				
				Client client = new Client();
				client.setClientId(rs.getString("client_id"));
				client.setName(rs.getString("client_name"));
				flowCard.setClient(client);
				
				User recorder = new User();
				recorder.setUserName(rs.getString("user_name"));
				flowCard.setRecorder(recorder);
				
				flowCard.setOptDate(rs.getTimestamp("opt_date"));
				flowCardList.add(flowCard);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl.findFlowCardList()Ê§°Ü£¡");
			throw new DaoException(e);
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		return flowCardList;	
	}

	public int getRecordCount(String clientId, String beginDate, String endDate)
			throws DaoException {
		String sql = "select count(*) from t_flow_card_master a, t_client b where a.client_id=b.id and ";
		if (clientId != null && !"".equals(clientId)) {
			sql+="b.client_id='" + clientId + "' and ";
		}		
		sql+="to_char(a.opt_date, 'YYYY-MM-DD') between '" + beginDate + "' and '" + endDate + "' and a.vou_sts='N'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl.getRecordCount()Ê§°Ü£¡");
			throw new DaoException(e);
		}finally {
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
	}

	public void delFlowCardDetail(String[] flowCardNo) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	public void delFlowCardMaster(String[] flowCardNo) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	public void auditFlowCard(String[] flowCardNo) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	public List<FlowCardDetail> findFlowCardDetailByFlowCardNo(String flowCardNo)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public FlowCard findFlowCardMasterById(String flowCardNo)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyFlowCardDetail(List<FlowCardDetail> flowCardDetailList)
			throws DaoException {
		// TODO Auto-generated method stub
		
	}

	public void modifyFlowCardMaster(FlowCard flowCard) throws DaoException {
		// TODO Auto-generated method stub
		
	}
	
}

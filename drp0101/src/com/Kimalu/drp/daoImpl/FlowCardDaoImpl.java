package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.Kimalu.drp.dao.FlowCardDao;
import com.Kimalu.drp.domain.FlowCard;
import com.Kimalu.drp.domain.FlowCardDetail;
import com.Kimalu.drp.util.ConnectionManager;

public class FlowCardDaoImpl implements FlowCardDao {

	@Override
	public void addFlowCardMaster(FlowCard flowCard) {
		String sql = "insert into t_flow_card_master( flow_card_no,fiscal_year_period,opt_type,client_id,recorder_id,opt_date,vou_sts)values(?,?,?,?,?,?,?)";
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, flowCard.getFlowCardNo());
			ps.setInt(2, flowCard.getFiscalYearPeriod().getId());
			ps.setString(3, flowCard.getOptType());
			ps.setInt(4, flowCard.getClient().getId());
			ps.setString(5, flowCard.getRecorder().getUserId());
			ps.setTimestamp(6, new Timestamp(flowCard.getOptDate().getTime()));
			ps.setString(7, flowCard.getVouSts());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(ps);
		}
	}

	@Override
	public void addFlowCardDetail(FlowCard flowCard) {
		String sql = "insert into t_flow_care_detail(flow_card_detail_id,aim_client_id,item_no,flow_card_no,opt_qty,adjust_flag)values(?,?,?,?,?,?)";
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (Iterator<FlowCardDetail> i = flowCard.getFlowCardDetailList()
					.iterator(); i.hasNext();) {
				FlowCardDetail fcd = i.next();
				ps.setInt(1, fcd.getFlowCardDetailId());
				ps.setInt(2, fcd.getAimClient().getId());
				ps.setString(3, fcd.getItem().getItemNo());
				ps.setString(4, fcd.getFlowCard().getFlowCardNo());
				ps.setDouble(5, fcd.getOptQty());
				ps.setString(6, fcd.getAdjustFlag());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(ps);
		}
	}

	@Override
	public String genVouNo() {
		String flowCardNo = "0001";
		String sql = "select max(flow_card_no) from T_FLOW_CARD_MASTER where substr(FLOW_CARD_NO,1,8)=?";
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			rs.next();
			if (rs.getLong(1) != 0) {
				flowCardNo=String.valueOf(rs.getLong(1)+1);
			}else{
				flowCardNo=date+flowCardNo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.close(rs);
			ConnectionManager.close(ps);
		}
		return flowCardNo;
	}
}

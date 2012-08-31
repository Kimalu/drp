package com.Kimalu.drp.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.Kimalu.drp.daoImpl.FlowCardDaoImpl;
import com.Kimalu.drp.domain.FlowCard;
import com.Kimalu.drp.util.ConnectionManager;

public class FlowCardService {
	
	FlowCardDaoImpl fcdi=new FlowCardDaoImpl();
	
	public void addFlowCard(FlowCard fc){
		
		Connection conn=ConnectionManager.getConnection();
		ConnectionManager.setAutoCommit(conn, false);
		fc.setFlowCardNo(fcdi.genVouNo());
		fcdi.addFlowCardMaster(fc);
		fcdi.addFlowCardDetail(fc);
		try {
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			ConnectionManager.close(conn);
		}
		
		
	}

}

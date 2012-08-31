package com.Kimalu.drp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IDCreator {

	private static IDCreator idc=new IDCreator();
	private IDCreator(){
		
	}
	public static IDCreator getInstance(){
		return idc;
	}
	public int getID(String tableName){
		int id=0;
		String sql="select value from t_table_id where table_name=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		 try {
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			ps.setString(1, tableName);
			rs=ps.executeQuery();
			if(rs.next()){
				id=rs.getInt("value")+1;
			}else{
				initTabId(conn,tableName);
				id=1;
			}
			updateTabId(conn,tableName,id);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
		}
		return id;
	}
	public void updateTabId(Connection conn,String tableName, long id) {
		String sql="update t_table_id set value=? where table_name=?";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.setString(2, tableName);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void initTabId(Connection conn,String tableName){
		String sql="insert into t_table_idf values(?,?)";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, tableName);
			ps.setInt(2, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}
}

package com.bjpowernode.drp.util.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjpowernode.drp.util.DBUtil;

/**
 * id生成器,采用单例模式
 * @author Administrator
 *
 */
public class IDGenerator {
	
	private static IDGenerator instance = new IDGenerator();
	
	private IDGenerator() {}
	
	public static IDGenerator getInstance() {
		return instance;
	}
	
	/**
	 * 生成新id
	 * @param tableName
	 * @return
	 */
	//public synchronized long newID(String tableName) {
		//synchronized(this) {
	public long newID(String tableName) {
		String sql = "select value from t_table_id where table_name=? for update";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long value = 0L;
		try {
			conn = DBUtil.getConnection();
			//开启事务
			DBUtil.setAutoCommit(conn, false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				value = rs.getLong("value");
			}else {
				throw new RuntimeException("生成Id出错！");
			}
			value = value + 1;
			
			//更新value
			modifyValueField(conn, tableName, value);
			//提交事务
			DBUtil.commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			//回滚事务
			DBUtil.rollback(conn);
			throw new RuntimeException("生成Id出错！");
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return value;
	}
	
	/**
	 * 更新value
	 * @param conn
	 * @param tableName
	 * @param value
	 * @throws SQLException
	 */
	private void modifyValueField(Connection conn, String tableName, long value) 
	throws SQLException {
		String sql = "update t_table_id set value=? where table_name=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, value);
			pstmt.setString(2, tableName);
			pstmt.executeUpdate();
		}finally {
			DBUtil.close(pstmt);
		}
	} 
	
	public static void main(String[] args) {
		long newId = IDGenerator.getInstance().newID("t_client");
		System.out.println(newId);
	}
}

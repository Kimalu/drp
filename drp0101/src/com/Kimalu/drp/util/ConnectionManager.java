package com.Kimalu.drp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	private static ThreadLocal<Connection> connectionHolder=new ThreadLocal<Connection>();
	
	public static Connection getConnection(){
		if(connectionHolder.get()==null){
			Connection conn=DBUtil.getConnection();
			connectionHolder.set(conn);
		}
		return connectionHolder.get();
	}
	public static void close(Connection conn){
		DBUtil.close(conn);
		connectionHolder.remove();
	}
	
	public static void close(Statement pstmt) {
		DBUtil.close(pstmt);
	}
	
	public static void close(ResultSet rs) {
		DBUtil.close(rs);
	}
	public static void commit(Connection conn) {
		DBUtil.commit(conn);
	}
	
	public static void rollback(Connection conn) {
		DBUtil.rollback(conn);
	}
	
	public static void setAutoCommit(Connection conn, boolean autoCommit) {
		DBUtil.setAutoCommit(conn, autoCommit);
	}
}

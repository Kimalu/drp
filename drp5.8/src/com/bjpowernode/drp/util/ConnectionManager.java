package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ����ThreadLocal��װConnection
 * ThreadLocal������ͬһ���߳��й������
 * @author Administrator
 *
 */
public class ConnectionManager {

	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	
	/**
	 * ȡ��connection
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = connectionHolder.get();
		//�����ThreadLocal�в������뵱ǰ�̰߳󶨵�Connection
		//��ô���Ǿʹ���һ��Connection��������ŵ�ThreadLocal��
		if (conn == null) {
			try {
				//ȡ��jdbc������Ϣ
				JdbcInfo jdbcInfo = ConfigReader.getInstance().getJdbcInfo();
				Class.forName(jdbcInfo.getDriverName());
				conn = DriverManager.getConnection(jdbcInfo.getUrl(), jdbcInfo.getUsername(), jdbcInfo.getPassword());
				
				//�ŵ�ThreadLocal��
				connectionHolder.set(conn);
			} catch (ClassNotFoundException e) {
				//��¼��־���Խ��಻���Ҽ�¼��ȥ���������Ը�׼ȷ�Ķ�λ����
				//���Ǹ��û���Ӧ���׳��಻���ҵ���Ӧ���׳��û��ܹ����Ĵ���
				e.printStackTrace();
				throw new AppException("ϵͳ���ֹ��ϣ�����ϵϵͳ����Ա��");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException("ϵͳ���ֹ��ϣ�����ϵϵͳ����Ա��");
			}
		}
		return conn;
	}
	
	/**
	 * ����connection
	 */
	public static void closeConnection() {
		Connection conn = connectionHolder.get();
		if (conn != null) {
			try {
				conn.close();
				//��ThreadLocal���Ƴ�
				connectionHolder.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void commit(Connection conn) {
		if (conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setAutoCommit(Connection conn, boolean autoCommit) {
		if (conn != null) {
			try {
				conn.setAutoCommit(autoCommit);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
}

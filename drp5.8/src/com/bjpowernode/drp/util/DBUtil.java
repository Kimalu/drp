package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DBUtil {

	/**
	 * 取得数据库连接
	 * @return
	 */	
	public static Connection getConnection() {
//		Connection conn = null;
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:BJPOWERN";
//			String username = "drp";
//			String password = "drp";
//			conn = DriverManager.getConnection(dbUrl, username, password);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
		
		Connection conn = null;
		try {
			//取得jdbc配置信息
			JdbcInfo jdbcInfo = ConfigReader.getInstance().getJdbcInfo();
			Class.forName(jdbcInfo.getDriverName());
			conn = DriverManager.getConnection(jdbcInfo.getUrl(), jdbcInfo.getUsername(), jdbcInfo.getPassword());
		} catch (ClassNotFoundException e) {
			//记录日志可以将类不能找记录进去，这样可以更准确的定位问题
			//但是给用户不应该抛出类不能找到，应该抛出用户能够理解的错误
			e.printStackTrace();
			throw new AppException("系统出现故障，请联系系统管理员！");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("系统出现故障，请联系系统管理员！");
		}
		return conn;
		
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
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
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
	
	
	public static void main(String[] args) {
		DBUtil.getConnection();
		System.out.println("---------ok---------");
	}
}

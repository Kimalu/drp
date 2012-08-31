package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 采用ThreadLocal封装Connection
 * ThreadLocal可以在同一个线程中共享变量
 * @author Administrator
 *
 */
public class ConnectionManager {

	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	
	/**
	 * 取得connection
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = connectionHolder.get();
		//如果在ThreadLocal中不存在与当前线程绑定的Connection
		//那么我们就创建一个Connection，并将其放到ThreadLocal中
		if (conn == null) {
			try {
				//取得jdbc配置信息
				JdbcInfo jdbcInfo = ConfigReader.getInstance().getJdbcInfo();
				Class.forName(jdbcInfo.getDriverName());
				conn = DriverManager.getConnection(jdbcInfo.getUrl(), jdbcInfo.getUsername(), jdbcInfo.getPassword());
				
				//放到ThreadLocal中
				connectionHolder.set(conn);
			} catch (ClassNotFoundException e) {
				//记录日志可以将类不能找记录进去，这样可以更准确的定位问题
				//但是给用户不应该抛出类不能找到，应该抛出用户能够理解的错误
				e.printStackTrace();
				throw new AppException("系统出现故障，请联系系统管理员！");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException("系统出现故障，请联系系统管理员！");
			}
		}
		return conn;
	}
	
	/**
	 * 关于connection
	 */
	public static void closeConnection() {
		Connection conn = connectionHolder.get();
		if (conn != null) {
			try {
				conn.close();
				//从ThreadLocal中移除
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

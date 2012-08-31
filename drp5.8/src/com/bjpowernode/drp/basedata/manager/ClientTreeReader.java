package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjpowernode.drp.util.Constants;
import com.bjpowernode.drp.util.DBUtil;

/**
 * 负责读取分销商树
 * @author wangy  2009.11.06 初稿
 * @author zhangsan 2009.11.10 加入了xx方法
 * ............. 
 */
class ClientTreeReader {
	
	//html字符串
	private StringBuffer sbTree = new StringBuffer();
	
	/**
	 * 读取分销商数据(主控方法)
	 * @return html字符串
	 */
	public String read() {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			read(conn, 0, 0);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return sbTree.toString();
	}
	
//	/**
//	 * 递归读取分销商树
//	 * 
//	 * 第一步：不考虑过多的逻辑，就是先读取出来
//	 * @param conn
//	 * @param id 
//	 * @param level 控制层次感
//	 */
//	private void read(Connection conn, int id, int level) 
//	throws SQLException {
//		String sql = "select * from t_client where pid=?";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				sbTree.append(rs.getString("name"))
//					.append("<br>\n");
//				if ("N".equals(rs.getString("is_leaf"))) {
//					read(conn, rs.getInt("id"), level+1);
//				}
//			}
//		}finally {
//			DBUtil.close(rs);
//			DBUtil.close(pstmt);
//		}
//	}
	
//	/**
//	 * 递归读取分销商树
//	 * 
//	 * 第二步：加入缩进
//	 * @param conn
//	 * @param id 
//	 * @param level 控制层次感
//	 */
//	private void read(Connection conn, int id, int level) 
//	throws SQLException {
//		String sql = "select * from t_client where pid=?";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				for (int i=0; i<level; i++) {
//					sbTree.append("&nbsp;&nbsp;&nbsp;");
//				}
//				sbTree.append(rs.getString("name"))
//					.append("<br>\n");
//				if ("N".equals(rs.getString("is_leaf"))) {
//					read(conn, rs.getInt("id"), level+1);
//				}
//			}
//		}finally {
//			DBUtil.close(rs);
//			DBUtil.close(pstmt);
//		}
//	}

//	/**
//	 * 递归读取分销商树
//	 * 
//	 * 第三步：叶子节点加入"-"号，非叶子节点加入“+”号
//	 * @param conn
//	 * @param id 
//	 * @param level 控制层次感
//	 */
//	private void read(Connection conn, int id, int level) 
//	throws SQLException {
//		String sql = "select * from t_client where pid=?";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				for (int i=0; i<level; i++) {
//					sbTree.append("&nbsp;&nbsp;&nbsp;");
//				}
//				if ("N".equals(rs.getString("is_leaf"))) {
//					sbTree.append("+").append(rs.getString("name"))
//					.append("<br>\n");
//					read(conn, rs.getInt("id"), level+1);
//				}else {
//					sbTree.append("-").append(rs.getString("name"))
//					.append("<br>\n");
//				}
//			}
//		}finally {
//			DBUtil.close(rs);
//			DBUtil.close(pstmt);
//		}
//	}
	
	/**
	 * 递归读取分销商树
	 * 
	 * 第四步：采用div完成树形结构
	 * @param conn
	 * @param id 
	 * @param level 控制层次感
	 */
	private void read(Connection conn, int id, int level) 
	throws SQLException {
		String sql = "select * from t_client where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sbTree.append("<div>");
				sbTree.append("\n");
				for (int i=0; i<level; i++) {
					sbTree.append("<img src=\"../images/white.gif\">");
					sbTree.append("\n");
				}
				if ("N".equals(rs.getString("is_leaf"))) {
					sbTree.append("<img alt=\"展开\" style=\"cursor:hand;\" onClick=\"display('" + rs.getInt("id") +"');\" id=\"img" + rs.getInt("id") + "\" src=\"../images/plus.gif\">");
					sbTree.append("\n");
					sbTree.append("<img id=\"im" + rs.getInt("id") + "\" src=\"../images/closedfold.gif\">");
					sbTree.append("\n");
					sbTree.append("<a href=\"client_node_crud.jsp?id=" + rs.getInt("id") + "\" target=\"clientDispAreaFrame\">" + rs.getString("name") + "</a>");
					sbTree.append("\n");
					sbTree.append("<div style=\"display:none;\" id=\"div" + rs.getInt("id") + "\">");
					sbTree.append("\n");
					read(conn, rs.getInt("id"), level+1); //递归读取
					sbTree.append("</div>");
					sbTree.append("\n");
				}else {
					sbTree.append("<img src=\"../images/minus.gif\">");
					sbTree.append("\n");
					sbTree.append("<img src=\"../images/openfold.gif\">");
					sbTree.append("\n");
					if (Constants.NO.equals(rs.getString("is_client"))) { 
						sbTree.append("<a href=\"client_node_crud.jsp?id=" + rs.getInt("id") + "\" target=\"clientDispAreaFrame\">" + rs.getString("name") + "</a>");
					}else {
						sbTree.append("<a href=\"client_crud.jsp?id=" + rs.getInt("id") + "\" target=\"clientDispAreaFrame\">" + rs.getString("name") + "</a>");
					}
					sbTree.append("\n");
				}
				sbTree.append("</div>");
			}
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
	}	
}

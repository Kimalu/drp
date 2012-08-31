package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjpowernode.drp.util.Constants;
import com.bjpowernode.drp.util.DBUtil;

/**
 * �����ȡ��������
 * @author wangy  2009.11.06 ����
 * @author zhangsan 2009.11.10 ������xx����
 * ............. 
 */
class ClientTreeReader {
	
	//html�ַ���
	private StringBuffer sbTree = new StringBuffer();
	
	/**
	 * ��ȡ����������(���ط���)
	 * @return html�ַ���
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
//	 * �ݹ��ȡ��������
//	 * 
//	 * ��һ���������ǹ�����߼��������ȶ�ȡ����
//	 * @param conn
//	 * @param id 
//	 * @param level ���Ʋ�θ�
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
//	 * �ݹ��ȡ��������
//	 * 
//	 * �ڶ�������������
//	 * @param conn
//	 * @param id 
//	 * @param level ���Ʋ�θ�
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
//	 * �ݹ��ȡ��������
//	 * 
//	 * ��������Ҷ�ӽڵ����"-"�ţ���Ҷ�ӽڵ���롰+����
//	 * @param conn
//	 * @param id 
//	 * @param level ���Ʋ�θ�
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
	 * �ݹ��ȡ��������
	 * 
	 * ���Ĳ�������div������νṹ
	 * @param conn
	 * @param id 
	 * @param level ���Ʋ�θ�
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
					sbTree.append("<img alt=\"չ��\" style=\"cursor:hand;\" onClick=\"display('" + rs.getInt("id") +"');\" id=\"img" + rs.getInt("id") + "\" src=\"../images/plus.gif\">");
					sbTree.append("\n");
					sbTree.append("<img id=\"im" + rs.getInt("id") + "\" src=\"../images/closedfold.gif\">");
					sbTree.append("\n");
					sbTree.append("<a href=\"client_node_crud.jsp?id=" + rs.getInt("id") + "\" target=\"clientDispAreaFrame\">" + rs.getString("name") + "</a>");
					sbTree.append("\n");
					sbTree.append("<div style=\"display:none;\" id=\"div" + rs.getInt("id") + "\">");
					sbTree.append("\n");
					read(conn, rs.getInt("id"), level+1); //�ݹ��ȡ
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

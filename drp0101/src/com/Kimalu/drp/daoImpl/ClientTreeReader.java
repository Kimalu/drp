package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Kimalu.drp.util.DBUtil;

public class ClientTreeReader {
	private StringBuffer sb=new StringBuffer();
	private String sql="select * from t_client where pid=?";
	public String getHtmlTree(){
		Connection conn=DBUtil.getConnection();
		buildHtmlTree(conn,0,0);
		DBUtil.close(conn);
		return sb.toString();
		
	}
	private void buildHtmlTree(Connection conn,int id,int level ){
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				sb.append("<div>\n");
				for(int i=0;i<level;i++){
					sb.append("<img src='../images/white.gif'>\n");
				}
				if("n".equalsIgnoreCase(rs.getString("is_leaf"))){
					sb.append("<img alt='Õ¹¿ª' style='cursor:hand;' onClick=\"display('"+rs.getInt("id")+"');\" id='img"+rs.getInt("id")+"' src='../images/plus.gif'>\n");
					sb.append("<img id='im"+rs.getInt("id")+"' src='../images/closedfold.gif'>\n");
					if("y".equalsIgnoreCase(rs.getString("is_client"))){
						sb.append("<a href='client_crud.jsp?id="+rs.getInt("id")+"&name="+rs.getString("name")+"' target='clientDispAreaFrame'>"+rs.getString("name")+"</a>\n");
					}else{
						sb.append("<a href='client_node_crud.jsp?id="+rs.getInt("id")+"&name="+rs.getString("name")+"' target='clientDispAreaFrame'>"+rs.getString("name")+"</a>\n");
					}
					sb.append("<div style='display:none;' id='div"+rs.getInt("id")+"'>\n");
					buildHtmlTree(conn,rs.getInt("id"),level+1);
					sb.append("</div>\n");
				}else{
					sb.append("<img src='../images/minus.gif'>\n");
					sb.append("<img id='im"+rs.getInt("id")+"' src='../images/closedfold.gif'>\n");
					if("y".equalsIgnoreCase(rs.getString("is_client"))){
						sb.append("<a href='client_crud.jsp?id="+rs.getInt("id")+"&name="+rs.getString("name")+"' target='clientDispAreaFrame'>"+rs.getString("name")+"</a>\n");
					}else{
						sb.append("<a href='client_node_crud.jsp?id="+rs.getInt("id")+"&name="+rs.getString("name")+"' target='clientDispAreaFrame'>"+rs.getString("name")+"</a>\n");
					}
				}
				sb.append("</div>\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
		}
	}
}
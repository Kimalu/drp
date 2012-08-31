package com.Kimalu.drp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.bjpowernode.drp.util.DBUtil;

public abstract class  AbstractBaseDao<T> implements BaseDao<T> {


	@Override
	public int getTotalNum(String tableName) {
		String sql="select count(*) c from "+tableName;
		 Connection conn=DBUtil.getConnection();
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 int count=0;
		 try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt("c");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return count;
	}

}

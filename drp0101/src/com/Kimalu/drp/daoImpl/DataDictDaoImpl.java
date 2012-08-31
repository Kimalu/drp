package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Kimalu.drp.domain.AbstractDataDict;
import com.Kimalu.drp.util.DBUtil;

public class DataDictDaoImpl {

	private static DataDictDaoImpl ddd=new DataDictDaoImpl();
	private DataDictDaoImpl(){
	}
	public static DataDictDaoImpl getInstance(){
		return ddd;
	}
	
	public List<AbstractDataDict> getListByType(String type){
		List<AbstractDataDict> dds=new ArrayList<AbstractDataDict>();
		String sql="select * from t_data_dict where category=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, type);
			rs=ps.executeQuery();
			while(rs.next()){
				AbstractDataDict add=new AbstractDataDict();
				add.setId(rs.getString("id"));
				add.setName(rs.getString("name"));
				dds.add(add);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return dds;
	}
}

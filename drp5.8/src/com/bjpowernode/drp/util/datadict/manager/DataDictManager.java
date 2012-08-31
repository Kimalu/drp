package com.bjpowernode.drp.util.datadict.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.DBUtil;
import com.bjpowernode.drp.util.datadict.domain.ClientLevel;
import com.bjpowernode.drp.util.datadict.domain.ItemCategory;
import com.bjpowernode.drp.util.datadict.domain.ItemUnit;


/**
 * 采用单例，管理数据字典
 * @author Administrator
 *
 */
public class DataDictManager {
	
	private static DataDictManager instance = new DataDictManager();
	
	private DataDictManager() {}
	
	public static DataDictManager getInstance() {
		return instance;
	}
	
	/**
	 * 取得分销商级别列表
	 * @return
	 */
	public List<ClientLevel> getClientLevelList() {
		String sql = "select id, name from t_data_dict where category='A'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClientLevel> clietLevelList = new ArrayList<ClientLevel>(); 
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ClientLevel cl = new ClientLevel();
				cl.setId(rs.getString("id"));
				cl.setName(rs.getString("name"));
				clietLevelList.add(cl);
			} 
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AppException("取得分销商级别错误！");
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return clietLevelList;
	}
	
	/**
	 * 取得物料类别代码列表
	 * @return
	 */
	public List<ItemCategory> getItemCategoryList() {
		String sql = "select id, name from t_data_dict where category='C'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>(); 
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemCategory ic = new ItemCategory();
				ic.setId(rs.getString("id"));
				ic.setName(rs.getString("name"));
				itemCategoryList.add(ic);
			} 
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AppException("取得物料类别错误！");
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return itemCategoryList;		
	}
	
	/**
	 * 取得物单位列表
	 * @return
	 */
	public List<ItemUnit> getItemUnitList() {
		String sql = "select id, name from t_data_dict where category='D'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemUnit> itemUnitList = new ArrayList<ItemUnit>(); 
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemUnit iu = new ItemUnit();
				iu.setId(rs.getString("id"));
				iu.setName(rs.getString("name"));
				itemUnitList.add(iu);
			} 
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AppException("取得计量单位错误！");
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return itemUnitList;		
	}
	
}

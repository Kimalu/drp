package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.DBUtil;
import com.bjpowernode.drp.util.PageModel;
import com.bjpowernode.drp.util.datadict.domain.ItemCategory;
import com.bjpowernode.drp.util.datadict.domain.ItemUnit;

public class ItemDao4OracleImpl implements ItemDao {

	public void addItem(Connection conn, Item item) {
		String sql = "insert into t_items(item_no, item_name, spec, pattern, category, unit) " +
				"values(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			//Dao�����ͨ���ǵ����ģ���Ӧ�÷���̫����߼�
			//��ΪDaoΪ���ǳ������ײ㣬����Ӧ��Խͨ��Խ��
//			if (findItemById(conn, item.getItemNo()) != null) {
//				throw new AppException("���ϴ����ظ�������=[" + item.getItemNo() + "]��");
//			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getItemNo());
			pstmt.setString(2, item.getItemName());
			pstmt.setString(3, item.getSpec());
			pstmt.setString(4, item.getPattern());
			pstmt.setString(5, item.getItemCategory().getId());
			pstmt.setString(6, item.getItemUnit().getId());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			//log4j��¼��־
			e.printStackTrace();

			//System.out.println("e============" + e.getMessage());
			//if ("ORA-00001: Υ��ΨһԼ������ (DRP.PK_T_ITEMS)".equals(e.getMessage())) {
			//	throw new AppException("���ϴ����ظ�������=��" + item.getItemNo() + "����");
			//}
			
			//ȡ�������ظ��Ĵ�����
			if (e.getErrorCode() == 1) {
				throw new AppException("���ϴ����ظ�������=��" + item.getItemNo() + "����");
			}
			throw new AppException("�������ʧ�ܣ�");
		}finally {
			DBUtil.close(pstmt);
		}
	}

	public Item findItemById(Connection conn, String itemNo) {
		StringBuffer sbSql = new StringBuffer();
		//��һ�з���
		sbSql.append("select a.item_no, a.item_name, a.spec, a.pattern, a.category as category_id, ")
		     .append("b.name as category_name, a.unit as unit_id, c.name as unit_name, a.upload_file_name ")
		     .append("from t_items a, t_data_dict b, t_data_dict c ")
		     .append("where a.category=b.id and a.unit=c.id and a.item_no=?");

//        //�ڶ��з��� 		
//		sbSql.append("select a.item_no, a.item_name, a.spec, a.pattern, a.category as category_id, ")
//			 .append("(select b.name from t_data_dict b where a.category=b.id) as category_name, ")
//			 .append("a.unit as unit_id, ")
//			 .append("(select c.name from t_data_dict c where a.unit=c.id) as unit_name ")
//			 .append("from t_items a where a.item_no=?");
		
		//ͨ��������־�����¼����log4j, ����info,debug,error...
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Item item = null;
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setString(1, itemNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				item = new Item();
				item.setItemNo(rs.getString("item_no"));
				item.setItemName(rs.getString("item_name"));
				item.setSpec(rs.getString("spec"));
				item.setPattern(rs.getString("pattern"));
				//����ItemCategory
				ItemCategory ic = new ItemCategory();
				ic.setId(rs.getString("category_id"));
				ic.setName(rs.getString("category_name"));
				item.setItemCategory(ic);
				
				//����ItemUnit
				ItemUnit iu = new ItemUnit();
				iu.setId(rs.getString("unit_id"));
				iu.setName(rs.getString("unit_name"));
				item.setItemUnit(iu);
				
				//�ϴ��ļ�����
				item.setUploadFileName(rs.getString("upload_file_name"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			//��¼����־�ļ� error
			throw new AppException("�������ϴ����ѯ�������ϴ���[" + itemNo + "]");
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return item;
	}

	@SuppressWarnings("unchecked")
	public PageModel findAllItem(Connection conn, String queryString,
			int pageNo, int pageSize) {
		StringBuffer sbSql = new StringBuffer();
		//��һ�з���
		
//		sbSql.append("select a.item_no, a.item_name, a.spec, a.pattern, a.category as category_id, ")
//		     .append("b.name as category_name, a.unit as unit_id, c.name as unit_name ")
//		     .append("from t_items a, t_data_dict b, t_data_dict c ")
//		     .append("where a.category=b.id and a.unit=c.id ");

//        //�ڶ��з��� 		
//		sbSql.append("select a.item_no, a.item_name, a.spec, a.pattern, a.category as category_id, ")
//			 .append("(select b.name from t_data_dict b where a.category=b.id) as category_name, ")
//			 .append("a.unit as unit_id, ")
//			 .append("(select c.name from t_data_dict c where a.unit=c.id) as unit_name ")
//			 .append("from t_items a ");
		
		sbSql.append("select * ")
			.append("from (")
				.append("select i.*, rownum rn from (")
				.append("select a.item_no, a.item_name, a.spec, a.pattern, a.category as category_id, ")
				.append("b.name as category_name, a.unit as unit_id, c.name as unit_name ")
				.append("from t_items a, t_data_dict b, t_data_dict c ")
				.append("where a.category=b.id and a.unit=c.id  ");
				if (queryString != null && !"".equals(queryString)) {
					sbSql.append(" and (a.item_no like '" + queryString + "%' or a.item_name like '" + queryString + "%') ");
				}
				sbSql.append(" order by a.item_no")
				.append(") i where rownum<=? ")
				.append(") ")
				.append("where rn >? ");
		System.out.println("sql=" + sbSql.toString());
				
		//ͨ��������־�����¼����log4j, ����info,debug,error...
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel pageModel = null;
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, pageNo * pageSize);
			pstmt.setInt(2, (pageNo - 1) * pageSize);
			rs = pstmt.executeQuery();
			List itemList = new ArrayList();
			while (rs.next()) {
				Item item = new Item();
				item.setItemNo(rs.getString("item_no"));
				item.setItemName(rs.getString("item_name"));
				item.setSpec(rs.getString("spec"));
				item.setPattern(rs.getString("pattern"));
				//����ItemCategory
				ItemCategory ic = new ItemCategory();
				ic.setId(rs.getString("category_id"));
				ic.setName(rs.getString("category_name"));
				item.setItemCategory(ic);
				
				//����ItemUnit
				ItemUnit iu = new ItemUnit();
				iu.setId(rs.getString("unit_id"));
				iu.setName(rs.getString("unit_name"));
				item.setItemUnit(iu);
				
				itemList.add(item);
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(itemList);
			//��������ȡ�ü�¼��
			int totalRecords = getTotalRecords(conn, queryString);
			pageModel.setTotalRecords(totalRecords);
		}catch(SQLException e) {
			e.printStackTrace();
			//��¼����־�ļ� error
			throw new AppException("��ҳ��ѯʧ��");
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return pageModel;
	}

	/**
	 * ��������ȡ�ü�¼��
	 * @param conn
	 * @param queryStr
	 * @return
	 */
	private int getTotalRecords(Connection conn, String queryStr) 
	throws SQLException {
		String sql = "select count(*) from t_items ";
		if (queryStr != null && !"".equals(queryStr)) {
			sql+="where item_no like '" + queryStr + "%' or item_name like '" + queryStr + "%' ";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int temp = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			temp = rs.getInt(1);
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return temp;
	}

	public void modifyItem(Connection conn, Item item) {
		String sql = "update t_items set item_name=?, spec=?, pattern=?, category=?, unit=? " +
		"where item_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item.getItemName());
			pstmt.setString(2, item.getSpec());
			pstmt.setString(3, item.getPattern());
			pstmt.setString(4, item.getItemCategory().getId());
			pstmt.setString(5, item.getItemUnit().getId());
			pstmt.setString(6, item.getItemNo());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			//log4j��¼��־
			e.printStackTrace();
			throw new AppException("�޸�����ʧ�ܣ�");
		}finally {
			DBUtil.close(pstmt);
		}	
	}	
	
	public void modifyUploadFileNameField(Connection conn, String itemNo, String uploadFileName) {
		String sql = "update t_items set upload_file_name=? where item_no=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uploadFileName);
			pstmt.setString(2, itemNo);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			//log4j��¼��־
			e.printStackTrace();
			throw new AppException("�޸��ϴ��ļ�ʧ�ܣ�");
		}finally {
			DBUtil.close(pstmt);
		}	
	}
}

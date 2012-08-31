package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao2;
import com.Kimalu.drp.domain.AbstractDataDict;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.ItemCategory;
import com.Kimalu.drp.domain.ItemUnit;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.util.DBUtil;

public class ItemDaoImpl extends AbstractBaseDao2<Item> {

	private static ItemDaoImpl itemDaoImpl = new ItemDaoImpl();

	private ItemDaoImpl() {
	}

	public static ItemDaoImpl getInstance() {
		return itemDaoImpl;
	}

	
	public List<Item> queryByItemName(Integer pageNO, Integer pageSize,String queryItemName) {
		List<Item> itemList = new ArrayList<Item>();
		String sql = "";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "select t.*,dd.name as unitname from (select rownum rn, i.*,dd.name as categname from t_items i left join t_data_dict dd on i.category=dd.id where item_name like '%"+queryItemName+"%') t left join t_data_dict dd on  t.unit=dd.id where rn>=? and rn<=?";
		try {
			ps = conn.prepareStatement(sql);
//			ps.setString(1, queryItemName);
			ps.setInt(1, (pageNO - 1) * pageSize + 1);
			ps.setInt(2, pageNO * pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item=new Item();
				item.setItemNo(rs.getString("item_no"));
				item.setItemName(rs.getString("item_name"));
				item.setSpec(rs.getString("spec"));
				item.setPattern(rs.getString("pattern"));
				item.setUploadFileName(rs.getString("upload_file_name"));
				
				ItemCategory itemCategory=new ItemCategory();
				itemCategory.setId(rs.getString("category"));
				itemCategory.setName(rs.getString("categname"));
				
				ItemUnit itemUnit=new ItemUnit();
				itemUnit.setId(rs.getString("unit"));
				itemUnit.setName(rs.getString("unitname"));
				
				item.setItemCategory(itemCategory);
				item.setItemUnit(itemUnit);
				
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return itemList;
	}
	
	@Override
	public String add(Connection conn, Item t) {
		String sql = "insert into t_items values(?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, t.getItemNo());
			ps.setString(2, t.getItemCategory().getId());
			ps.setString(3, t.getItemUnit().getId());
			ps.setString(4, t.getItemName());
			ps.setString(5, t.getSpec());
			ps.setString(6, t.getPattern());
			ps.setString(7, t.getUploadFileName());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
		}

		return null;
	}

	@Override
	public void del(Connection conn, Item t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Item> query(Integer pageNO, Integer pageSize) {
		List<Item> itemList = new ArrayList<Item>();
		String sql = "";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "select t.*,dd.name as unitname from (select rownum rn, i.*,dd.name as categname from t_items i left join t_data_dict dd on i.category=dd.id) t left join t_data_dict dd on  t.unit=dd.id where rn>=? and rn<=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNO - 1) * pageSize + 1);
			ps.setInt(2, pageNO * pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item=new Item();
				item.setItemNo(rs.getString("item_no"));
				item.setItemName(rs.getString("item_name"));
				item.setSpec(rs.getString("spec"));
				item.setPattern(rs.getString("pattern"));
				item.setUploadFileName(rs.getString("upload_file_name"));
				
				ItemCategory itemCategory=new ItemCategory();
				itemCategory.setId(rs.getString("category"));
				itemCategory.setName(rs.getString("categname"));
				
				ItemUnit itemUnit=new ItemUnit();
				itemUnit.setId(rs.getString("unit"));
				itemUnit.setName(rs.getString("unitname"));
				
				item.setItemCategory(itemCategory);
				item.setItemUnit(itemUnit);
				
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return itemList;
	}

	@Override
	public Item queryById(String id) {
		String sql="select i.*,dd.id as unitId, dd.name as unitname from (select i.*,dd.id as categId, dd.name as categname from t_items i join t_data_dict dd on i.category=dd.id and dd.category='C') i join t_data_dict dd on i.unit=dd.id and dd.category='D' where i.item_no=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Item item=new Item();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				item.setItemNo(rs.getString("item_no"));
				item.setItemName(rs.getString("item_name"));
				item.setSpec(rs.getString("spec"));
				item.setPattern(rs.getString("pattern"));
				item.setUploadFileName(rs.getString("upload_file_name"));
				
				ItemCategory itemCategory=new ItemCategory();
				itemCategory.setId(rs.getString("categId"));
				itemCategory.setName(rs.getString("categName"));
				
				ItemUnit itemUnit=new ItemUnit();
				itemUnit.setId(rs.getString("unitid"));
				itemUnit.setName(rs.getString("unitName"));
				
				item.setItemCategory(itemCategory);
				item.setItemUnit(itemUnit);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		
		return item;
	}

	@Override
	public Item queryById(int id) {
		return null;
	}

	@Override
	public void updateById(Connection conn, Item t) {
		String sql="update t_items set category=?,unit=?,item_name=?,spec=?,pattern=?,upload_file_name=? where item_no=?";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, t.getItemCategory().getId());
			ps.setString(2, t.getItemUnit().getId());
			ps.setString(3, t.getItemName());
			ps.setString(4, t.getSpec());
			ps.setString(5, t.getPattern());
			ps.setString(6, t.getUploadFileName());
			ps.setString(7,t.getItemNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
		}
		
	}

	@Override
	public void del(Connection conn, String[] Ids) {
		String sql="delete from t_items where item_no=?";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, Ids[0]);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
		}
		

	}

	public List<AbstractDataDict> queryDataDict(String category) {
		String sql = "select * from t_data_dict where category=?";
		List<AbstractDataDict> ddlist = new ArrayList<AbstractDataDict>();

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, category);
			rs = ps.executeQuery();
			while (rs.next()) {
				AbstractDataDict itemdd = null;
				if ("c".equalsIgnoreCase(category)) {
					itemdd = new ItemCategory();
				} else {
					itemdd = new ItemUnit();
				}
				itemdd.setId(rs.getString("id"));
				itemdd.setName(rs.getString("name"));
				ddlist.add(itemdd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
		}
		return ddlist;
	}

}

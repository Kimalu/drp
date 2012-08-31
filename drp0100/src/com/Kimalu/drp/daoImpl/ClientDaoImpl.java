package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.ClientLevel;
import com.bjpowernode.drp.util.DBUtil;

public class ClientDaoImpl extends AbstractBaseDao<Client> {
	
	private static ClientDaoImpl clientDao=new ClientDaoImpl();
	private ClientDaoImpl(){
		
	}
	public static ClientDaoImpl getInstance(){
		return clientDao;
	}

	@Override
	public String add(Client t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void del(Client t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> query(Integer pageNO, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client queryById(String id) {
		return null;
	}

	@Override
	public Client queryById(int id) {
		Connection conn=DBUtil.getConnection();
		String sql="select a.*,b.name level_name from t_client a left join t_data_dict b on a.client_level=b.id where a.id=?";
		PreparedStatement ps =null;
		ResultSet rs=null;
		Client c=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				c=new Client();
				c.setId(rs.getInt("id"));
				c.setPid(rs.getInt("pid"));
				c.setClientId(rs.getString("client_id"));
				ClientLevel cl=new ClientLevel();
				cl.setId(rs.getString("client_level"));
				cl.setName(rs.getString("level_name"));
				c.setClientLevel(cl);
				c.setContactTel(rs.getString("contact_tel"));
				c.setAddress(rs.getString("address"));
				c.setBankAcctNo(rs.getString("bank_acct_no"));
				c.setName(rs.getString("name"));
				c.setZipCode(rs.getString("zip_code"));
				c.setClientId(rs.getString("client_id"));
				c.setIsLead(rs.getString("is_leaf"));
				c.setIsClient(rs.getString("is_client"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return c;
	}

	@Override
	public void updateById(Client t) {
		Connection conn=DBUtil.getConnection();
		String sql="update T_CLIENT set client_level=?, name=?,client_id=?,bank_acct_no=?,contact_tel=?,address=?,zip_code=? where id=?";
		PreparedStatement ps =null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, t.getClientLevel().getId());
			ps.setString(2, t.getName());
			ps.setString(3, t.getClientId());
			ps.setString(4, t.getBankAcctNo());
			ps.setString(5, t.getContactTel());
			ps.setString(6, t.getAddress());
			ps.setString(7, t.getZipCode());
			ps.setInt(8, t.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}

	@Override
	public void del(String[] Ids) {
		
	}
}

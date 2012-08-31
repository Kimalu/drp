package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.dao.AbstractBaseDao2;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.ClientLevel;
import com.Kimalu.drp.util.ConnectionManager;
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
		Connection conn=DBUtil.getConnection();
		String sql="insert into t_client values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps =null;
		try {
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			ps.setLong(1, t.getId());
			ps.setLong(2,t.getPid());
			ps.setString(3, t.getClientLevel().getId());
			ps.setString(4, t.getName());
			ps.setString(5, t.getClientId());
			ps.setString(6, t.getBankAcctNo());
			ps.setString(7, t.getContactTel());
			ps.setString(8, t.getAddress());
			ps.setString(9, t.getZipCode());
			ps.setString(10, t.getIsLead());
			ps.setString(11, t.getIsClient());
			ps.executeUpdate();
			Client c=this.queryById(t.getPid());
			if(c.getIsLead().equalsIgnoreCase("y")){
				this.updateLeaf(c.getId(),"n");
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return null;
	}
	public void updateLeaf(int id,String leaf){
		Connection conn=DBUtil.getConnection();
		String sql="update t_client set is_leaf=? where id=?";
		PreparedStatement ps =null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, leaf);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
		}
	}
//	@Override
//	public void del(Client c) {
//		Connection conn=DBUtil.getConnection();
//		try {
//			conn.setAutoCommit(false);
//			delCycle(conn,c);
//			conn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			DBUtil.close(conn);
//		}
//		
//	}
//	public void delCycle(Connection conn,Client c){
//		
//			if(c.getIsLead().equalsIgnoreCase("n")){
//				List<Client> clients=this.queryChildren(c.getId());
//				for(Iterator<Client> i=clients.iterator();i.hasNext();){
//					delCycle(conn,i.next());
//				}
//			}
//			removeNode(conn,c);
//			if(this.queryChildren(c.getPid()).size()==0){
//				this.updateLeaf(conn,c.getPid(), "y");
//			}
//	}
//	
//	public void removeNode(Connection conn,Client c){
//		String sql="delete from t_client where id=?";
//		PreparedStatement ps=null;
//		try {
//			ps=conn.prepareStatement(sql);
//			ps.setInt(1, c.getId());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			DBUtil.close(ps);
//		}
//	}
	@Override
	public void del(Client c){
		if(c.getIsLead().equalsIgnoreCase("n")){
			List<Client> clients=this.queryChildren(c.getId());
			for(Iterator<Client> i=clients.iterator();i.hasNext();){
				del(i.next());
			}
		}
		removeNode(c);
	}

	public void removeNode(Client c){
		Connection conn=DBUtil.getConnection();
		String sql="delete from t_client where id=?";
		PreparedStatement ps=null;
		try {
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.executeUpdate();
			List<Client> clients=this.queryChildren(c.getPid());
			if(clients.size()==0){
				this.updateLeaf(c.getPid(), "y");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
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

	public List<Client> queryChildren(int pid){
		List<Client> clients=new ArrayList<Client>();
		Connection conn=DBUtil.getConnection();
		String sql="select * from t_client where pid=?";
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pid);
			rs=ps.executeQuery();
			while(rs.next()){
				Client c=new Client();
				c.setId(rs.getInt("id"));
				c.setPid(rs.getInt("pid"));
				ClientLevel cl=new ClientLevel();
				cl.setId(rs.getString("client_level"));
				c.setClientLevel(cl);
				c.setName(rs.getString("name"));
				c.setClientId(rs.getString("client_id"));
				c.setBankAcctNo(rs.getString("bank_acct_no"));
				c.setContactTel(rs.getString("contact_tel"));
				c.setAddress(rs.getString("address"));
				c.setZipCode(rs.getString("zip_code"));
				c.setIsLead(rs.getString("is_leaf"));
				c.setIsClient(rs.getString("is_client"));
				clients.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
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
	
	public List<Client> getClientList(){
		List<Client> clientList=new ArrayList<Client>();
		String sql="select a.*,b.name cln from t_client a join t_data_dict b on a.client_level=b.id where is_client='Y'";
		Connection conn=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Client client =new Client();
				client.setId(rs.getInt("id"));
				client.setPid(rs.getInt("pid"));
				ClientLevel clientLevel=new ClientLevel();
				clientLevel.setId(rs.getString("client_level"));
				clientLevel.setName(rs.getString("cln"));
				client.setClientLevel(clientLevel);
				client.setName(rs.getString("name"));
				client.setClientId(rs.getString("client_id"));
				client.setBankAcctNo(rs.getString("bank_acct_no"));
				client.setContactTel(rs.getString("contact_tel"));
				client.setAddress(rs.getString("address"));
				client.setZipCode(rs.getString("zip_code"));
				client.setIsClient(rs.getString("is_client"));
				client.setIsLead(rs.getString("is_leaf"));
				clientList.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.close(rs);
			ConnectionManager.close(ps);
		}
		return clientList;
	}
	
}

package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjpowernode.drp.basedata.domain.AimClient;
import com.bjpowernode.drp.basedata.domain.Client;
import com.bjpowernode.drp.util.Constants;
import com.bjpowernode.drp.util.DBUtil;
import com.bjpowernode.drp.util.PageModel;
import com.bjpowernode.drp.util.datadict.domain.ClientLevel;
import com.bjpowernode.drp.util.generator.IDGenerator;

/**
 * ���õ������������
 * @author Administrator
 *
 */
public class ClientManager {

	private static ClientManager instance = new ClientManager();
	
	private ClientManager() {}
	
	public static ClientManager getInstance() {
		return instance;
	}
	
	/**
	 * ���ط�����html�ַ���
	 * @return html�ַ���
	 */
	public String getClientTreeString() {
		return new ClientTreeReader().read();
	}
	
	/**
	 * ����id��ѯ����������
	 * @param id
	 * @return ������ڷ���Client���󣬷��򷵻�null
	 */
	public Client findRegionOrClientById(int id) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select a.id, a.pid, a.name, a.client_id, a.client_level as client_level_id, ")
			.append("b.name as client_level_name, a.bank_acct_no, a.contact_tel, a.address, a.zip_code, ")
			.append("a.is_client, a.is_leaf ")
			.append("from t_client a left join t_data_dict b on a.client_level=b.id where a.id=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Client client = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setPid(rs.getInt("pid"));
				client.setName(rs.getString("name"));
				client.setClientId(rs.getString("client_id"));
				
				//�����̼���
				ClientLevel clientLevel = new ClientLevel();
				clientLevel.setId(rs.getString("client_level_id"));
				clientLevel.setName(rs.getString("client_level_name"));
				client.setClientLevel(clientLevel);
				
				client.setBankAcctNo(rs.getString("bank_acct_no"));
				client.setContactTel(rs.getString("contact_tel"));
				client.setAddress(rs.getString("address"));
				client.setZipCode(rs.getString("zip_code"));
				client.setIsClient(rs.getString("is_client"));
				client.setIsLeaf(rs.getString("is_leaf"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return client;
	} 
	
	/**
	 * �޸�����������
	 * @param client
	 */
	public void modifyRegionOrClient(Client client) {
		String sql = "update t_client set name=?, client_level=?, bank_acct_no=?, contact_tel=?, " +
				"address=?, zip_code=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, client.getName());
			pstmt.setString(2, client.getClientLevel().getId());
			pstmt.setString(3, client.getBankAcctNo());
			pstmt.setString(4, client.getContactTel());
			pstmt.setString(5, client.getAddress());
			pstmt.setString(6, client.getZipCode());
			pstmt.setInt(7, client.getId());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	/**
	 * �������������
	 * @param client
	 */
	public void addRegionOrClient(Client client) {
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("insert into t_client ( ")
				   .append("id, pid, client_level, ") 
				   .append("name, client_id, bank_acct_no, ") 
				   .append("contact_tel, address, zip_code, ")
				   .append("is_leaf, is_client) ") 
				.append("values (? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?) ");	
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			//��������
			DBUtil.setAutoCommit(conn, false);
			pstmt = conn.prepareStatement(sbsql.toString());
			pstmt.setInt(1, (int)IDGenerator.getInstance().newID("t_client"));
			pstmt.setInt(2, client.getPid());
			pstmt.setString(3, client.getClientLevel().getId());
			pstmt.setString(4, client.getName());
			pstmt.setString(5, client.getClientId());
			pstmt.setString(6, client.getBankAcctNo());
			pstmt.setString(7, client.getContactTel());
			pstmt.setString(8, client.getAddress());
			pstmt.setString(9, client.getZipCode());
			pstmt.setString(10, client.getIsLeaf());
			pstmt.setString(11, client.getIsClient());
			pstmt.executeUpdate();
			
			//���ظ��ڵ����
			Client parentClient = findRegionOrClientById(client.getPid());
			if (Constants.YES.equals(parentClient.getIsLeaf())) {
				//�޸�Ϊ��Ҷ��
				modifyIsLeafField(conn, client.getPid(), Constants.NO);
			}
			//�ύ����
			DBUtil.commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			//�ع�����
			DBUtil.rollback(conn);
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}		
	}
	
	/**
	 * �޸�is_leaf�ֶ�
	 * @param conn
	 * @param id
	 * @param leaf Y:Ҷ��,N:��Ҷ��
	 * @throws SQLException 
	 * 
	 */
	private void modifyIsLeafField(Connection conn, int id, String leaf) 
	throws SQLException {
		String sql = "update t_client set is_leaf=? where id=?";
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, leaf);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		}finally {
			DBUtil.close(pstmt);
		}		
	}
	
	/**
	 * 
	 * @param clientId
	 * @return true:���ڣ�false��������
	 */
	public boolean findClientByClientId(String clientId) {
		String sql = "select count(*) from t_client where client_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}		
		return flag;
	}
	
	/**
	 * ����idɾ��
	 * @param id
	 */
	public void delRegionOrClient(int id) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			//begin transaction
			DBUtil.setAutoCommit(conn, false);
			
			//����id��ѯ�ڵ㣬�õ����ĸ�id
			Client client = findRegionOrClientById(id);
			
			//�ݹ�ɾ��
			recursionDeleteTreeNodeById(conn, id);
			
			//ȡ��ָ���ڵ���ӽڵ���
			int count = getChildrenCount(conn, client.getPid());
			if (count == 0) {
				//�޸�ΪҶ�ӽڵ�
				modifyIsLeafField(conn, client.getPid(), Constants.YES);
			}
			
			//commit transaction
			DBUtil.commit(conn);
		}catch(SQLException e) {
			e.printStackTrace();
			//rollback transaction
			DBUtil.rollback(conn);
		}finally {
			DBUtil.close(conn);
		}		
	}
	
	/**
	 * �ݹ�ɾ��
	 * 
	 * @param conn
	 * @param id
	 * @throws SQLException
	 */
	private void recursionDeleteTreeNodeById(Connection conn, int id) 
	throws SQLException {
		String sql = "select * from t_client where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (Constants.NO.equals(rs.getString("is_leaf"))) {
					recursionDeleteTreeNodeById(conn, rs.getInt("id"));
				}
				//����idɾ��
				deleteTreeNodeById(conn, rs.getInt("id"));
			}
			//ɾ������
			deleteTreeNodeById(conn, id);
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
	}
	
	/**
	 * ����idɾ������
	 * 
	 * @param conn
	 * @param id
	 */
	private void deleteTreeNodeById(Connection conn, int id) 
	throws SQLException {
		String sql = "delete from t_client where id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}finally {
			DBUtil.close(pstmt);
		}
	}
	
	/**
	 * ����id�õ���Ӧ���ӽڵ���
	 * @param conn
	 * @param id
	 * @return
	 */
	private int getChildrenCount(Connection conn, int id) 
	throws SQLException {
		String sql = "select count(*) from t_client where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
			//throw new SQLException(e.toString());
			throw e;
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return count;
	}
	
	/**
	 * ��ѯ���еĹ���������
	 * 
	 * ����t_client��
	 * 
	 * @param pageNo
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ������
	 * @param queryStr
	 *            ��ѯ����
	 * @return pageMode����
	 */
	@SuppressWarnings("unchecked")
	public PageModel findAllClient(int pageNo, int pageSize, String queryStr) {
		StringBuffer sbSql = new StringBuffer();
//		sbSql.append("select a.id, a.pid, a.name, a.client_id, b.id as client_level_id, b.name as client_level_name, ")
//				.append("a.bank_acct_no, a.contact_tel, a.address, a.zip_code, a.is_leaf, a.is_client ")
//				.append("from t_client a, t_data_dict b where a.client_level=b.id and a.is_client='Y' ")
//				.append("and (a.client_id like '" + queryStr + "%' or ")
//				.append("a.name like '" + queryStr + "%')  order by a.id ")
//				.append("limit ").append((pageNo - 1) * pageSize).append(", ")
//				.append(pageSize);
		sbSql.append("select * from ")
		.append("(")
			.append("select t.*, rownum rn from ")
			.append("(")
				.append("select a.id, a.pid, a.name, a.client_id, b.id as client_level_id, b.name as client_level_name, ")
				.append("a.bank_acct_no, a.contact_tel, a.address, a.zip_code, a.is_leaf, a.is_client ")
				.append("from t_client a, t_data_dict b where a.client_level=b.id and a.is_client='Y' ")
				.append("and (a.client_id like '" + queryStr + "%' or ")
				.append("a.name like '" + queryStr + "%') order by a.id ")
			.append(") t where rownum<=?")
		.append(") ")
		.append("where rn > ?");
		System.out.println("sql=" + sbSql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel pageModel = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, pageNo * pageSize);
			pstmt.setInt(2, (pageNo - 1) * pageSize);
			rs = pstmt.executeQuery();
			List clientList = new ArrayList();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setPid(rs.getInt(2));
				client.setName(rs.getString(3));
				client.setClientId(rs.getString(4));
				ClientLevel cl = new ClientLevel();
				cl.setId(rs.getString(5));
				cl.setName(rs.getString(6));
				client.setClientLevel(cl);
				client.setBankAcctNo(rs.getString(7));
				client.setContactTel(rs.getString(8));
				client.setAddress(rs.getString(9));
				client.setZipCode(rs.getString(10));
				client.setIsLeaf(rs.getString(11));
				client.setIsClient(rs.getString(12));
				clientList.add(client);
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(clientList);
			pageModel.setTotalRecords(getTotalClientRecords(conn, queryStr));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return pageModel;
	}

	/**
	 * ��������ȡ�ù��������̵ļ�¼��
	 * @param conn
	 * @param queryStr ����
	 * @return ��¼��
	 */
	private int getTotalClientRecords(Connection conn, String queryStr) {
		String sql = "select count(*) from t_client where is_client='Y' and "
				+ "(client_id like '" + queryStr + "%' or name like '"
				+ queryStr + "%')";
		int totalRecords = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecords = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return totalRecords;
	}

	/**
	 * ��ѯ���е��跽�ͻ�
	 * 
	 * ����v_aim_client��ͼ
	 * 
	 * @param pageNo
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ������
	 * @param queryStr
	 *            ��ѯ����
	 * @return pageMode����
	 */
	public PageModel findAllAimClient(int pageNo, int pageSize, String queryStr) {
//		String sql = "select id, name, level_id, level_name from v_aim_client "
//				+ "where (id like '" + queryStr + "%' or name like '"
//				+ queryStr + "%') " + " order by id " + "limit " + (pageNo - 1)
//				* pageSize + ", " + pageSize;
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select * from ")
		.append("(")
			.append("select t.*,rownum rn from ") 
			.append("(")
			.append("select id, client_temi_id, name, client_temi_category_id, client_temi_category_name from v_aim_client ")
			.append("where (client_temi_id like '" + queryStr + "%' or name like '" + queryStr + "%') ")
			.append(" order by id")
			.append(") t where rownum <=?")
		.append(") ")
		.append("where rn >= ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel pageModel = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, pageNo * pageSize);
			pstmt.setInt(2, (pageNo - 1) * pageSize);
			rs = pstmt.executeQuery();
			List aimClientList = new ArrayList();
			while (rs.next()) {
				AimClient aimClient = new AimClient();
				aimClient.setId(rs.getInt("id"));
				aimClient.setTemiOrClientId(rs.getString("client_temi_id"));
				aimClient.setName(rs.getString("name"));
				aimClient.setLevelId(rs.getString("client_temi_category_id"));
				aimClient.setLevelName(rs.getString("client_temi_category_name"));
				aimClientList.add(aimClient);
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(aimClientList);
			pageModel.setTotalRecords(getTotalAimRecords(conn, queryStr));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return pageModel;

	}
	
	/**
	 * ��������ȡ���跽�ͻ��ļ�¼��
	 * @param conn
	 * @param queryStr ����
	 * @return ��¼��
	 */
	 private int getTotalAimRecords(Connection conn, String queryStr) {
			String sql = "select count(*) from v_aim_client " +
			"where client_temi_id like '" + queryStr + "%' or name like '" + queryStr + "%' ";
		int totalRecords = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecords = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return totalRecords;	 
	}
}

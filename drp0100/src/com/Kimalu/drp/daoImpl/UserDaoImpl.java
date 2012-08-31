package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.dao.UserDao;
import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.domain.User;
import com.bjpowernode.drp.util.DBUtil;

public class UserDaoImpl extends  AbstractBaseDao<User> {

	private static AbstractBaseDao<User> userDao=new UserDaoImpl();
	private UserDaoImpl(){
		
	}
	public static AbstractBaseDao<User> getInstance(){
		return userDao;
	}
	
	@Override
	public String add(User user) {
		String flag="error";
		String sql="insert into t_user(user_id,user_name,password,contact_tel,email,create_date)values(?,?,?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps= null;
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getContactTel());
			ps.setString(5, user.getEmail());
			ps.setTimestamp(6, new Timestamp(user.getCreateDate().getTime()));
			int f= ps.executeUpdate();
			if(f==1){
				flag = "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return flag;
	}

	@Override
	public void del(User user) {

	}
	
	@Override
	public List<User> query(Integer pageNO ,Integer pageSize) {
		List<User> userList=new ArrayList<User>();
		String sql="";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		if(pageNO==null || pageSize==null){
			sql="select * from t_user";
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					User user=new User();
					user.setUserId(rs.getString("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setContactTel(rs.getString("contact_tel"));
					user.setCreateDate(rs.getTimestamp("create_date"));
					userList.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(ps);
				DBUtil.close(conn);
			}
		}else{
			sql="select t.* from (select rownum rn, u.* from t_user u) t where rn>=? and rn<=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, (pageNO-1)*pageSize+1);
				ps.setInt(2, pageNO*pageSize);
				rs=ps.executeQuery();
				while(rs.next()){
					User user=new User();
					user.setUserId(rs.getString("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setContactTel(rs.getString("contact_tel"));
					user.setCreateDate(rs.getTimestamp("create_date"));
					userList.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(ps);
				DBUtil.close(conn);
			}
		}
		return userList;
	}

	@Override
	public User queryById(String userId) {
		String sql="select * from t_user where user_id=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=new User();
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			if(rs.next()){
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setContactTel(rs.getString("contact_tel"));
				user.setCreateDate(rs.getTimestamp("create_date"));
			}else{
				user=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return user;
	}

	@Override
	public void updateById(User user) {
		String sql="update t_user set user_name=? ,password=?,contact_tel=?,email=? where user_id=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getContactTel());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		
	}
	@Override
	public User queryById(int id) {
		return null;
	}
	@Override
	public void del(String[] Ids) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		StringBuffer sql=new StringBuffer("delete from t_user where user_id in(");
		for(int i=0;i<Ids.length-1;i++){
			sql.append("?,");
		}
		sql.append("?)");
		try {
			ps=conn.prepareStatement(sql.toString());
			for(int i=0;i<Ids.length;i++){
				ps.setString(i+1, Ids[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}
}

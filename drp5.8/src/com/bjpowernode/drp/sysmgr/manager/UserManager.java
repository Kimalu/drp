package com.bjpowernode.drp.sysmgr.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bjpowernode.drp.sysmgr.domain.User;
import com.bjpowernode.drp.util.DBUtil;
import com.bjpowernode.drp.util.PageModel;
  
/**
 * 用户管理类
 * @author Administrator
 *
 */
public class UserManager {
	
	//饿汉式
//	private static UserManager instance = new UserManager();
//	
//	private UserManager() {}
//	
//	public static UserManager getInstance() {
//		return instance;
//	}
	
	//懒汉式（lazy）
	private static UserManager instance = null;
	
	private UserManager() {}
	
	public static synchronized UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	/**  
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user) {
		System.out.println("UserManager.addUser() -->>" + user);
		String sql = "insert into t_user(user_id, user_name, password, " +
				"contact_tel, email, create_date) values(?, ?, ?, ?, ?, ?)";
		System.out.println("UserManager.addUser() -->>sql=" + sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getContactTel());
			pstmt.setString(5, user.getEmail());
			pstmt.setTimestamp(6, new Timestamp(user.getCreateDate().getTime())); //注意Timestamp
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			//log4j............
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 根据用户id查询
	 * @param userId
	 * @return 如果存在返回User对象，否则返回null
	 */
	public User findUserById(String userId) {
		System.out.println("UserManager.findUserById() -->> userId=" + userId);
		String sql = "select * from t_user where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			//存在用户信息
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setContactTel(rs.getString("contact_tel"));
				user.setEmail(rs.getString("email"));
				user.setCreateDate(rs.getTimestamp("create_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return user;
	}
	
	/**
	 * 分页查询
	 * @param pageNo 第几页
	 * @param pageSize 每页多少条数据
	 * @return PageModel对象
	 */
	public PageModel<User> findAllUser(int pageNo, int pageSize) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select * from ") 
		.append("( ")
		  .append("select rownum rn, t.* from ") 
		   .append("( ")
		     .append("select * from t_user  where user_id <> 'root' order by user_id ")
		   .append(") t where rownum <=? ")
		.append(") where rn>? ");    
		System.out.println("UserManager.findAllUser() --> sql=" + sbSql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<User> pageModel = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, pageNo * pageSize);
			pstmt.setInt(2, (pageNo-1)*pageSize);
			rs = pstmt.executeQuery();
			List<User> userList = new ArrayList<User>();  
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setContactTel(rs.getString("contact_tel"));
				user.setEmail(rs.getString("email"));
				user.setCreateDate(rs.getTimestamp("create_date"));
				userList.add(user);
			}
			pageModel = new PageModel<User>();
			pageModel.setList(userList);
			pageModel.setTotalRecords(getTotalRecords(conn));
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return pageModel;
	}
	
	/**
	 * 取得记录数
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private int getTotalRecords(Connection conn) 
	throws SQLException{
		String sql = "select count(*) from t_user where user_id <> 'root'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return count;
	}
	
	/**
	 * 修改用户
	 * @param user
	 */
	public void modifyUser(User user) {
		String sql = "update t_user set user_name=?, password=?, contact_tel=?, email=? " +
				"where user_id=? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getContactTel());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getUserId());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 根据用户id删除
	 * @param userId
	 */
	public void delUser(String userId) {
		String sql = "delete from t_user where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}		
	}
	
//	/**
//	 * 根据用户id的集合删除
//	 * 
//	 * 要求采用一条语句删除，一次调用，使用占位符的方式(暂时不使用占位符)
//	 * 
//	 * delete from t_user where user_id in('a001','a002','a003');
//	 * 
//	 * @param userIds
//	 */
//	public void delUser(String[] userIds) {
//		StringBuffer sbStr = new StringBuffer();
//		for (int i=0; i<userIds.length; i++) {
//			sbStr.append("'")
//			.append(userIds[i])
//			.append("',");
//		}
//		String sql = "delete from t_user where user_id in(" + sbStr.substring(0, sbStr.length()-1) + ")";
//		Connection conn = null;
//		Statement stmt = null; //采用Statement
//		try {
//			conn = DBUtil.getConnection();
//			stmt = conn.createStatement();
//			stmt.executeUpdate(sql);
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(stmt);
//			DBUtil.close(conn);
//		}
//	}
	
	/**
	 * 根据用户id的集合删除
	 * 
	 * 要求采用一条语句删除，一次调用，使用占位符的方式
	 * 
	 * delete from t_user where user_id in(?,?,?);
	 * 
	 * @param userIds
	 */
	public void delUser(String[] userIds) {
		StringBuffer sbStr = new StringBuffer();
		for (int i=0; i<userIds.length; i++) {
			sbStr.append("?,");
		}
		String sql = "delete from t_user where user_id in(" + sbStr.substring(0, sbStr.length()-1) + ")";
		Connection conn = null;
		PreparedStatement pstmt = null; //采用Statement
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i=0; i<userIds.length; i++) {
				//jdbc的序号从1开始
				pstmt.setString(i+1, userIds[i]);
			}
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}	
	
//	/**
//	 * 登录(采用受控异常)
//	 * @param userId
//	 * @param password
//	 */
//	public User login(String userId, String password) 
//	throws UserNotFoundException, PasswordNotCorrectException{ //必须抛出
//		User user = findUserById(userId);
//		if (user == null) {
//			throw new UserNotFoundException("用户代码不能找到，代码=【" + userId +"】");
//		}
//		if (!user.getPassword().equals(password)) {
//			throw new PasswordNotCorrectException("密码不正确");
//		}
//		return user;
//	}
	
	/**
	 * 登录(采用非受控异常)
	 * @param userId
	 * @param password
	 */
	public User login(String userId, String password) { 
	//throws UserNotFoundException, PasswordNotCorrectException{ //必须抛出
		User user = findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户代码不能找到，代码=【" + userId +"】");
		}
		if (!user.getPassword().equals(password)) {
			throw new PasswordNotCorrectException("密码不正确");
		}
		return user;
	}

	/**
	 * 登录,演示SQL注入
	 *  用户：aaaaa
		密码：' or '1'='1
	 *  形成的sql语句如下：
	 *  select * from t_user where user_id='aaaaa' and password='' or '1'='1'
	 *  
	 * @param userId
	 * @param password
	 */
	public boolean login1(String userId, String password) {
		String sql = "select * from t_user where user_id='" + userId + "' and password='" + password + "'";
		System.out.println("UserManager.login1() sql ="  +sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false; 
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				success = true;
				System.out.println("用户名称和密码正确！！！");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return success;
	}
	
	/**
	 * 修改密码
	 * @param userId 用户代码
	 * @param password 密码
	 */
	public void modifyPassword(String userId, String password) {
		String sql = "update t_user set password=? where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}					
}
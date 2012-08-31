package com.Kimalu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args) {
		new Test().test1();
	}
	public void saveUser(){
		String url ="jdbc:mysql://localhost:3306/test" ;
		String username="root";
		String password="123";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn= DriverManager.getConnection(url,username,password);
			conn.setAutoCommit(false);
			ps=conn.prepareStatement("insert into user values(?,?,?)");
			ps.setInt(1, 123);
			ps.setString(2, "ewqewq");
			saveMsg(conn);
			ps.setInt(3, 20);
			ps.executeUpdate();
			conn.commit();
			System.out.println("connect success");
		} catch (SQLException e) {
			System.out.println("connect failure");
			e.printStackTrace();
		} 
	}
	public void saveMsg(Connection conn){
		String sql="insert into msg2 values(?,?)";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 123);
			ps.setString(2, "ewqewq");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void test1(){
		String url ="jdbc:mysql://localhost:3306/test" ;
		String username="root";
		String password="123";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn= DriverManager.getConnection(url,username,password);
			conn.setAutoCommit(false);
			test2(conn);
			ps=conn.prepareStatement("select count(*) id from msg2");
			rs=ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getInt("id"));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	public void test2(Connection conn){
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement("delete from msg2");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ps=null;
			}
		}
	}
}

package com.Kimalu.drp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.domain.FiscalYearPeriod;
import com.bjpowernode.drp.util.DBUtil;

public class FiscalYearPeriodDaoImpl extends AbstractBaseDao<FiscalYearPeriod> {
	private static AbstractBaseDao<FiscalYearPeriod> fypDao=new FiscalYearPeriodDaoImpl();
	private FiscalYearPeriodDaoImpl(){
		
	}
	public static AbstractBaseDao<FiscalYearPeriod> getInstance(){
		return fypDao;
	}

	@Override
	public String add(FiscalYearPeriod fyp) {
		String flag = "error";
		String sql = "insert into t_fiscal_year_period values(?,?,?,?,?,?)";
		int id = getSequenceId("t_fiscal_year_period");
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, fyp.getFiscalYear());
			ps.setInt(3, fyp.getFiscalmonth());
			ps.setTimestamp(4,new Timestamp( fyp.getBeginDate().getTime()));
			ps.setTimestamp(5, new Timestamp(fyp.getEndDate().getTime()));
			ps.setString(6, fyp.getPeriodStatus());
			if(ps.executeUpdate()==1){
				if(setSequenceId(id ,"t_fiscal_year_period")==1){
					flag = "success";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return flag;
	}

	private int getSequenceId(String tableName) {
		String sql = "select value from t_table_id where table_name=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tableName);
			rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("value");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return id+1;
	}
	private int setSequenceId(int id ,String tableName){
		int flag=0;
		String sql="update t_table_id set value=? where table_name=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, tableName);
			flag=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return flag;
	}

	@Override
	public void del(FiscalYearPeriod t) {

	}

	@Override
	public List<FiscalYearPeriod> query(Integer pageNO, Integer pageSize) {
		List<FiscalYearPeriod> fypList=new ArrayList<FiscalYearPeriod>();
		String sql="";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		if(pageNO==null || pageSize==null){
			sql="select * from t_fiscal_year_period";
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					FiscalYearPeriod fyp=new FiscalYearPeriod();
					fyp.setId(rs.getInt("id"));
					fyp.setFiscalYear(rs.getInt("fiscal_year"));
					fyp.setFiscalmonth(rs.getInt("fiscal_period"));
					fyp.setBeginDate(rs.getTimestamp("begin_date"));
					fyp.setEndDate(rs.getTimestamp("end_date"));
					fyp.setPeriodStatus(rs.getString("period_sts"));
					fypList.add(fyp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(ps);
				DBUtil.close(conn);
			}
		}else{
			sql="select t.* from (select rownum rn, u.* from t_fiscal_year_period u) t where rn>=? and rn<=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, (pageNO-1)*pageSize+1);
				ps.setInt(2, pageNO*pageSize);
				rs=ps.executeQuery();
				while(rs.next()){
					FiscalYearPeriod fyp=new FiscalYearPeriod();
					fyp.setId(rs.getInt("id"));
					fyp.setFiscalYear(rs.getInt("fiscal_year"));
					fyp.setFiscalmonth(rs.getInt("fiscal_period"));
					fyp.setBeginDate(rs.getTimestamp("begin_date"));
					fyp.setEndDate(rs.getTimestamp("end_date"));
					fyp.setPeriodStatus(rs.getString("period_sts"));
					fypList.add(fyp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(ps);
				DBUtil.close(conn);
			}
		}
		return fypList;
	}

	@Override
	public FiscalYearPeriod queryById(String id) {
		return null;
	}

	@Override
	public void updateById(FiscalYearPeriod t) {

	}
	@Override
	public FiscalYearPeriod queryById(int id) {
		String sql="select * from t_fiscal_year_period where id=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		FiscalYearPeriod fyp=new FiscalYearPeriod();
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
				fyp.setId(rs.getInt("id"));
				fyp.setFiscalYear(rs.getInt("fiscal_year"));
				fyp.setFiscalmonth(rs.getInt("fiscal_month"));
				fyp.setBeginDate(rs.getTimestamp("begin_date"));
				fyp.setEndDate(rs.getTimestamp("end_date"));
				fyp.setPeriodStatus(rs.getString("period_sts"));
		} catch (SQLException e) {
			e.printStackTrace();
			fyp=null;
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return fyp;
	}
	@Override
	public void del(String[] Ids) {
		// TODO Auto-generated method stub
		
	}
}

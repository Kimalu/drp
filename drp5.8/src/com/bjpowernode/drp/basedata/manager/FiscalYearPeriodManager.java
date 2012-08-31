package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bjpowernode.drp.basedata.domain.FiscalYearPeriod;
import com.bjpowernode.drp.util.DBUtil;
import com.bjpowernode.drp.util.PageModel;
import com.bjpowernode.drp.util.generator.IDGenerator;

/**
 * 核算期间管理类，采用单例模式实现
 *
 */
public class FiscalYearPeriodManager {

	private static FiscalYearPeriodManager instance = new FiscalYearPeriodManager();

	private FiscalYearPeriodManager() {
	}

	public static FiscalYearPeriodManager getInstance() {
		return instance;
	}

	/**
	 * 增加会计核算期间
	 * 
	 * @param fiscalYearPeriod  FiscalYearPeriod
	 */
	public void addFiscalYearPeriod(FiscalYearPeriod fiscalYearPeriod) {
		String sql = "insert into t_fiscal_year_period(id, fiscal_year, fiscal_period, begin_date, end_date, period_sts) "
				+ "values(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)IDGenerator.getInstance().newID("t_fiscal_year_period"));
			pstmt.setInt(2, fiscalYearPeriod.getFiscalYear());
			pstmt.setInt(3, fiscalYearPeriod.getFiscalPeriod());
			pstmt.setDate(4, new java.sql.Date(fiscalYearPeriod.getBeginDate()
					.getTime()));
			pstmt.setDate(5, new java.sql.Date(fiscalYearPeriod.getEndDate()
					.getTime()));
			pstmt.setString(6, fiscalYearPeriod.getPeriodSts());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	/**
	 * 修改会计核算期间
	 * 
	 * @param fiscalYearPeriod  FiscalYearPeriod
	 */
	public void modifyFiscalYearPeriod(FiscalYearPeriod fiscalYearPeriod) {
		String sql = "update t_fiscal_year_period set begin_date=?, end_date=?, period_sts=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(fiscalYearPeriod.getBeginDate().getTime()));
			pstmt.setDate(2, new java.sql.Date(fiscalYearPeriod.getEndDate().getTime()));
			pstmt.setString(3, fiscalYearPeriod.getPeriodSts());
			pstmt.setInt(4, fiscalYearPeriod.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 根据核算年和核算月查询会计核算期间
	 * @param fiscalYear 核算年
	 * @param fiscalPeriod 核算月
	 * @return FiscalYearPeriod
	 */
	public FiscalYearPeriod findFiscalYearPeriod(int fiscalYear, int fiscalPeriod) {
		String sql = "select id, fiscal_year, fiscal_period, begin_date, end_date, period_sts from t_fiscal_year_period " +
		"where fiscal_year=? and fiscal_period=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FiscalYearPeriod fiscalYearPeriod = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fiscalYear);
			pstmt.setInt(2, fiscalPeriod);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fiscalYearPeriod = new FiscalYearPeriod();
				fiscalYearPeriod.setId(rs.getInt("id"));
				fiscalYearPeriod.setFiscalYear(rs.getInt("fiscal_year"));
				fiscalYearPeriod.setFiscalPeriod(rs.getInt("fiscal_period"));
				fiscalYearPeriod.setBeginDate(rs.getDate("begin_date"));
				fiscalYearPeriod.setEndDate(rs.getDate("end_date"));
				fiscalYearPeriod.setPeriodSts(rs.getString("period_sts"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return fiscalYearPeriod;
	}
	
	/**
	 * 根据id查询会计期间
	 * @param id id
	 * @return FiscalYearPeriod
	 */
	public FiscalYearPeriod findFiscalYearPeriodById(int id) {
		String sql = "select id, fiscal_year, fiscal_period, begin_date, end_date, period_sts from t_fiscal_year_period " +
		"where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FiscalYearPeriod fiscalYearPeriod = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fiscalYearPeriod = new FiscalYearPeriod();
				fiscalYearPeriod.setId(rs.getInt("id"));
				fiscalYearPeriod.setFiscalYear(rs.getInt("fiscal_year"));
				fiscalYearPeriod.setFiscalPeriod(rs.getInt("fiscal_period"));
				fiscalYearPeriod.setBeginDate(rs.getDate("begin_date"));
				fiscalYearPeriod.setEndDate(rs.getDate("end_date"));
				fiscalYearPeriod.setPeriodSts(rs.getString("period_sts"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return fiscalYearPeriod;		
	}
	
	/**
	 * 查询全部会计核算期间
	 * 
	 * @param pageNo 第几页
	 * @param pageSize  每页多条数据
	 * @return PageModel
	 */
	public PageModel findAllFiscalYearPeriod(int pageNo, int pageSize) {
//		String sql = "select id, fiscal_year, fiscal_period, begin_date, end_date, period_sts from t_fiscal_year_period " +
//		"order by id limit " + (pageNo -1)  * pageSize + ", " + pageSize ;
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select * from ")
			.append("(")
				.append("select rownum rn, t.* from ")
				.append("(")
					.append("select id, fiscal_year, fiscal_period, begin_date, end_date, period_sts ")
					.append("from t_fiscal_year_period ")
				.append(") t where rownum <=?")
			.append(") ")
			.append("where rn >?");
		
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
			List fiscalYearPeriodList = new ArrayList();
			while (rs.next()) {
				FiscalYearPeriod fiscalYearPeriod = new FiscalYearPeriod();
				fiscalYearPeriod.setId(rs.getInt("id"));
				fiscalYearPeriod.setFiscalYear(rs.getInt("fiscal_year"));
				fiscalYearPeriod.setFiscalPeriod(rs.getInt("fiscal_period"));
				fiscalYearPeriod.setBeginDate(rs.getDate("begin_date"));
				fiscalYearPeriod.setEndDate(rs.getDate("end_date"));
				fiscalYearPeriod.setPeriodSts(rs.getString("period_sts"));
				fiscalYearPeriodList.add(fiscalYearPeriod);
			}
			//取得所有的记录数
			int totalRecords = getTotalRecords(conn);
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(fiscalYearPeriodList);
			pageModel.setTotalRecords(totalRecords);
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
	 * 取得记录总数
	 * @param conn
	 * @return 记录数
	 */
	private int getTotalRecords(Connection conn) {
		String sql = "select count(*) from t_fiscal_year_period";
		int totalRecords = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecords = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return totalRecords;
	}	
}

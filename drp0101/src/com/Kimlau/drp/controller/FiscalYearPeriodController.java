package com.Kimlau.drp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Kimalu.drp.domain.FiscalYearPeriod;
import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.service.FiscalYearPeriodService;
import com.Kimalu.drp.service.UserService;
import com.Kimalu.drp.util.JsonUtil;
public class FiscalYearPeriodController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FiscalYearPeriodController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		String command=request.getParameter("command");
		
		if("add".equals(command)){
			String message=addFyp(request);
			if("success".equals(message)){
				request.getRequestDispatcher("/basedata/fiscal_year_period_maint.jsp").forward(request, response);
			}else{
				request.setAttribute("error message", message);
				request.getRequestDispatcher("/basedata/error.jsp").forward(request, response);
			}
		}else if("del".equals(command)){
			
		}else if("update".equals(command)){
			
		}else if("query".equals(command)){
			Page<FiscalYearPeriod> page=initPage(request);
			queryFypListByPage(page);
			if(page.getList()!=null){
				response.getWriter().print(getJson(page)); 
			}
		}else if("queryById".equals(command)){
			
		}
	}
	
	private String getJson(Page<FiscalYearPeriod> Page){
		return JsonUtil.object2json(Page);
	}
	
	private Page<FiscalYearPeriod> initPage(HttpServletRequest request){
		Page<FiscalYearPeriod> page=new Page<FiscalYearPeriod>();
		page.setTotalNum(this.getTotalNum());
		page.setCurrentPageNo(1);
		String pageNo=request.getParameter("pageNo");
		if(pageNo!=null && !"".equals(pageNo)){
			page.setCurrentPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(4);
		int totalPageNum=(page.getPageSize()+page.getTotalNum())/page.getPageSize();
		page.setTotalPageNum(totalPageNum);
		return page;
	}
	
	private List<FiscalYearPeriod> queryFypListByPage(Page<FiscalYearPeriod> p) {
		FiscalYearPeriodService fypService=FiscalYearPeriodService.getInstance();
		return fypService.queryUserListByPage(p).getList();
	}
	private String getFypListJson(List<FiscalYearPeriod> fypList) {
		return "{"+JsonUtil.list2json(fypList)+"}";
	}
	private String addFyp(HttpServletRequest request){
		String periodSts="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String fiscalYear=request.getParameter("fiscalYear");
		String fiscalMonth=request.getParameter("fiscalMonth");
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		if(request.getParameter("periodSts")==null){
			periodSts="n";
		}else{
			periodSts="y";
		}
		FiscalYearPeriod fyp=new FiscalYearPeriod();
		fyp.setFiscalYear(Integer.parseInt(fiscalYear));
		fyp.setFiscalmonth(Integer.parseInt(fiscalMonth));
		try {
			fyp.setBeginDate(sdf.parse(beginDate));
			fyp.setEndDate(sdf.parse(endDate));
		} catch (ParseException e) {
			System.out.println("日期格式错误");
			e.printStackTrace();
		}
		fyp.setPeriodStatus(periodSts);
		FiscalYearPeriodService fypService=FiscalYearPeriodService.getInstance();
		return fypService.addFiscalYearPeriod(fyp);
	}
	private int getTotalNum(){
		FiscalYearPeriodService fypService=FiscalYearPeriodService.getInstance();
		return fypService.getTotalNum();
	}

}

package com.bjpowernode.drp.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.manager.FiscalYearPeriodManager;

/**
 * 检查核算年和核算月是否重复
 *
 */
public class FiscalYearPeriodValidateServlet extends HttpServlet {
	
	private static final String CONTENT_TYPE = "text/html; charset=GB18030";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		
		int fiscalYear = Integer.parseInt(request.getParameter("fiscalYear"));					
		int fiscalPeriod = Integer.parseInt(request.getParameter("fiscalPeriod"));
		if (FiscalYearPeriodManager.getInstance().findFiscalYearPeriod(fiscalYear, fiscalPeriod) != null) {
			response.getWriter().println("核算年或核算月重复！");
		}
	}
}

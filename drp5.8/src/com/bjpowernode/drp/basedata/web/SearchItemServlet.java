package com.bjpowernode.drp.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.manager.ItemService;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.PageModel;

/**
 * 分页查询物料
 * @author Administrator
 *
 */
public class SearchItemServlet extends AbstractItemServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到表单参数
		int pageNo = 1;
		if (request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		//int pageSize = Integer.parseInt(this.getServletConfig().getInitParameter("page-size"));
		
		//从Application范围中取得page-size
		//Application范围对应的对象是ServletContext
		int pageSize = Integer.parseInt(request.getSession().getServletContext().getInitParameter("page-size"));
		String queryString = request.getParameter("clientIdOrName");
		
		//调用业务逻辑
		PageModel pageModel = itemService.findAllItem(queryString, pageNo, pageSize);
		//将结果设置到request中，转向
		request.setAttribute("pageModel", pageModel);
		//System.out.println("----------------------forward begin");
		request.getRequestDispatcher("/basedata/item_maint.jsp").forward(request, response);
		//System.out.println("----------------------forward end");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

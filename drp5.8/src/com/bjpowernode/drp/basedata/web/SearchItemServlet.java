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
 * ��ҳ��ѯ����
 * @author Administrator
 *
 */
public class SearchItemServlet extends AbstractItemServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�õ�������
		int pageNo = 1;
		if (request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		//int pageSize = Integer.parseInt(this.getServletConfig().getInitParameter("page-size"));
		
		//��Application��Χ��ȡ��page-size
		//Application��Χ��Ӧ�Ķ�����ServletContext
		int pageSize = Integer.parseInt(request.getSession().getServletContext().getInitParameter("page-size"));
		String queryString = request.getParameter("clientIdOrName");
		
		//����ҵ���߼�
		PageModel pageModel = itemService.findAllItem(queryString, pageNo, pageSize);
		//��������õ�request�У�ת��
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

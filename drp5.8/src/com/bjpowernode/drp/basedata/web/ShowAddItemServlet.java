package com.bjpowernode.drp.basedata.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.datadict.manager.DataDictManager;

/**
 * 显示物料添加页面Servlet
 * @author Administrator
 *
 */
public class ShowAddItemServlet extends AbstractItemServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			//取得物料类别列表
			List itemCategoryList = DataDictManager.getInstance().getItemCategoryList();
			
			//取得计量单位列表
			List itemUnitList = DataDictManager.getInstance().getItemUnitList();
			
			req.setAttribute("itemCategoryList", itemCategoryList);
			req.setAttribute("itemUnitList", itemUnitList);
	
			//转发，理解转发和重定向的区别？
			req.getRequestDispatcher("/basedata/item_add.jsp").forward(req, resp);
		}catch(Exception e) {
			throw new AppException("操作错误，稍后再试!");
		}
	}

}

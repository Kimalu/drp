package com.bjpowernode.drp.basedata.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.basedata.manager.ItemService;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.datadict.manager.DataDictManager;

/**
 * 修改物料
 * @author Administrator
 *
 */
public class ShowModifyItemServlet extends AbstractItemServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemNo = request.getParameter("itemNo");
		//取得物料信息
		Item item = itemService.findItemById(itemNo);
		request.setAttribute("item", item);
		
		//取得物料类别
		List itemCategoryList = DataDictManager.getInstance().getItemCategoryList();
		request.setAttribute("itemCategoryList", itemCategoryList);
		
		//取得计量单位
		List itemUnitList = DataDictManager.getInstance().getItemUnitList();
		request.setAttribute("itemUnitList", itemUnitList);
		
		//转发
		request.getRequestDispatcher("/basedata/item_modify.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}

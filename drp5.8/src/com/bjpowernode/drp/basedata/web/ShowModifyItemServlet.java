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
 * �޸�����
 * @author Administrator
 *
 */
public class ShowModifyItemServlet extends AbstractItemServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemNo = request.getParameter("itemNo");
		//ȡ��������Ϣ
		Item item = itemService.findItemById(itemNo);
		request.setAttribute("item", item);
		
		//ȡ���������
		List itemCategoryList = DataDictManager.getInstance().getItemCategoryList();
		request.setAttribute("itemCategoryList", itemCategoryList);
		
		//ȡ�ü�����λ
		List itemUnitList = DataDictManager.getInstance().getItemUnitList();
		request.setAttribute("itemUnitList", itemUnitList);
		
		//ת��
		request.getRequestDispatcher("/basedata/item_modify.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}

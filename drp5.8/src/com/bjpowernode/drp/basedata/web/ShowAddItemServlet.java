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
 * ��ʾ�������ҳ��Servlet
 * @author Administrator
 *
 */
public class ShowAddItemServlet extends AbstractItemServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			//ȡ����������б�
			List itemCategoryList = DataDictManager.getInstance().getItemCategoryList();
			
			//ȡ�ü�����λ�б�
			List itemUnitList = DataDictManager.getInstance().getItemUnitList();
			
			req.setAttribute("itemCategoryList", itemCategoryList);
			req.setAttribute("itemUnitList", itemUnitList);
	
			//ת�������ת�����ض��������
			req.getRequestDispatcher("/basedata/item_add.jsp").forward(req, resp);
		}catch(Exception e) {
			throw new AppException("���������Ժ�����!");
		}
	}

}

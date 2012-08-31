package com.bjpowernode.drp.basedata.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bjpowernode.drp.basedata.manager.ItemService;
import com.bjpowernode.drp.util.BeanFactory;

/**
 * ŒÔ¡œServlet≥ÈœÛ
 * @author Administrator
 *
 */
public class AbstractItemServlet extends HttpServlet {
	
	protected ItemService itemService;
	
	@Override
	public void init() throws ServletException {
		BeanFactory beanFactory = (BeanFactory)this.getServletContext().getAttribute(BeanFactory.class.getName());
		itemService = (ItemService)beanFactory.getBean(ItemService.class);
	}
}

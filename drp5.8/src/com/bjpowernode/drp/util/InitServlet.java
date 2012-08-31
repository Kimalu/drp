package com.bjpowernode.drp.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 负责系统初始化工作
 * @author Administrator
 *
 */
public class InitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("-----------InitServlet---------");
		//初始化BeanFactory
		BeanFactory beanFactory = BeanFactory.getInstance();
		
		//将BeanFactory放入到ServletContext中
		//ServletContext是Web和业务逻辑层交互一个桥梁
		this.getServletContext().setAttribute(BeanFactory.class.getName(), beanFactory);
		
		this.getServletContext().setAttribute("add", Constants.ADD);
		this.getServletContext().setAttribute("del", Constants.DEL);
		this.getServletContext().setAttribute("modify", Constants.MODIFY);
	}
}

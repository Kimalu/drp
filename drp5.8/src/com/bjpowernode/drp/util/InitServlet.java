package com.bjpowernode.drp.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * ����ϵͳ��ʼ������
 * @author Administrator
 *
 */
public class InitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("-----------InitServlet---------");
		//��ʼ��BeanFactory
		BeanFactory beanFactory = BeanFactory.getInstance();
		
		//��BeanFactory���뵽ServletContext��
		//ServletContext��Web��ҵ���߼��㽻��һ������
		this.getServletContext().setAttribute(BeanFactory.class.getName(), beanFactory);
		
		this.getServletContext().setAttribute("add", Constants.ADD);
		this.getServletContext().setAttribute("del", Constants.DEL);
		this.getServletContext().setAttribute("modify", Constants.MODIFY);
	}
}

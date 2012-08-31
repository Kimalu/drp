package com.bjpowernode.drp.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.Constants;

/**
 * ����ServletContextListener��BeanFactory���뵽ServletContext��
 * @author Administrator
 *
 */
public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-----------InitListener---------");
		//��ʼ��BeanFactory
		BeanFactory beanFactory = BeanFactory.getInstance();
		
		//��BeanFactory���뵽ServletContext��
		//ServletContext��Web��ҵ���߼��㽻��һ������
		sce.getServletContext().setAttribute(BeanFactory.class.getName(), beanFactory);
		
		sce.getServletContext().setAttribute("add", Constants.ADD);
		sce.getServletContext().setAttribute("del", Constants.DEL);
		sce.getServletContext().setAttribute("modify", Constants.MODIFY);
		
	}

}

package com.bjpowernode.drp.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.Constants;

/**
 * 采用ServletContextListener将BeanFactory放入到ServletContext中
 * @author Administrator
 *
 */
public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-----------InitListener---------");
		//初始化BeanFactory
		BeanFactory beanFactory = BeanFactory.getInstance();
		
		//将BeanFactory放入到ServletContext中
		//ServletContext是Web和业务逻辑层交互一个桥梁
		sce.getServletContext().setAttribute(BeanFactory.class.getName(), beanFactory);
		
		sce.getServletContext().setAttribute("add", Constants.ADD);
		sce.getServletContext().setAttribute("del", Constants.DEL);
		sce.getServletContext().setAttribute("modify", Constants.MODIFY);
		
	}

}

package com.bjpowernode.drp.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.manager.ClientManager;

/**
 * 验证分销商代码是否重复
 * @author Administrator
 *
 */
public class ClientIdValidateServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("ClientIdValidateServlet.init()");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=GB18030");
		String clientId = request.getParameter("clientId");
		if (ClientManager.getInstance().findClientByClientId(clientId)) {
			response.getWriter().print("分销商代码已经存在！");
		} 
	}
}

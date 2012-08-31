package com.bjpowernode.drp.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.sysmgr.domain.User;

/**
 * 检查原用户密码是否与session中的密码相符
 *
 */
public class PasswordValidateServlet extends HttpServlet {
	
	private static final String CONTENT_TYPE = "text/html; charset=GB18030";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		String oldPassword = request.getParameter("oldPassword");
		User user = (User)request.getSession().getAttribute("login_user");
		if (!oldPassword.equals(user.getPassword())) {
			response.getWriter().println("输入密码与原密码不符！");
		}
	}
}

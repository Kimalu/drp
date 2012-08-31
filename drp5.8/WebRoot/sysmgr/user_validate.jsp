<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="com.bjpowernode.drp.sysmgr.manager.*" %>    
<%	

	//可以采用清除缓存的方法，如下
	//response.setContentType("text/xml");
	//response.setHeader("Cache-Control", "no-store"); //HTTP1.1	
	//response.setHeader("Pragma", "no-cache"); //HTTP1.0
	//response.setDateHeader("Expires", 0); 

	//out.println("Hello");
	//Thread.currentThread().sleep(3000);
	String userId = request.getParameter("userId");
	if (UserManager.getInstance().findUserById(userId) != null) {
		out.println("用户代码已经存在");
	}
%>

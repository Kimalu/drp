<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.Kimalu.drp.service.UserService" %>
   <%@ page import="com.Kimalu.drp.domain.User" %> 
   <%@ page import="com.Kimalu.drp.exception.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String command=request.getParameter("command");
	if("login".equals(command)){
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		UserService userService=UserService.getInstance();
		try{
			User user=userService.login(userId,password);
			session.setAttribute("user",user);
			response.sendRedirect("main.jsp");
		}catch(UserNoFoundException unfe){
			out.println(unfe.getMessage());
		}catch(PasswordNoCorrect pnc){
			out.println(pnc.getMessage());
		}
	}
%>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<TITLE></TITLE>
<script type="text/javascript" src="script/javascriptx.js"></script>
<SCRIPT language=JavaScript>
	function init() {
		loginForm.userId.focus();
	}
	
	function submitForm() {
		document.getElementById("userIdTip").innerHTML="";
		document.getElementById("passwordTip").innerHTML="";
		var flag=true;
		if(document.getElementById("userId").value.trim()==""){
			document.getElementById("userIdTip").innerHTML="<font color='red'>用户名不能为空</font>";
			flag=false;
		}
		if(document.getElementById("password").value==""){
			document.getElementById("passwordTip").innerHTML="<font color='red'>密码不能为空</font>";
			flag=false;
		}
		if(flag){
			with(loginForm){
				action="login.jsp?command=login";
				method="post";
				submit();
			}
		}
		//loginForm.submit();
	}
</SCRIPT>


<META content="MSHTML 6.00.2800.1264" name=GENERATOR>
</HEAD>
<BODY leftMargin=0 topMargin=0 onload="init()">
<FORM name="loginForm">
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
	<TBODY>
		<TR height=50>
			<TD width=1 background="images/logobar_left.gif"><IMG height=50 src="images/spacer.gif" width=1 border=0></TD>
			<TD width="416" align=left vAlign=center background="images/logobar_mid.gif"><em><strong><font color="#FFFFFF" size="+3" face="宋体"><IMG height=1 src="images/spacer.gif"
				width=5 border=0>DRP分销管理系统</font> </strong> </em></TD>
			<TD width="478" align=right vAlign=center background="images/logobar_mid.gif">
			<div align="right"><a href="#"><IMG height=25 src="images/banner_help_button.gif" width=60 border=0 name=banner_help_button> </a> <IMG height=1 src="images/spacer.gif" width=5 border=0>
			</div>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE height="80%" cellSpacing=0 cellPadding=0 width="100%" border=0>
	<TBODY>
		<TR>
			<!-- The login dialog -->
			<TD vAlign=center align=middle width="100%"><!-- login dialog -->
			<TABLE cellSpacing=0 cellPadding=0 border=0>
				<!-- title bar -->
				<TBODY>
					<TR>
						<!-- this row is kind of a dummy row to set the column widths for the whole page-->
						<TD width=8><IMG height=1 src="images/spacer.gif" width=8 border=0></TD>
						<!-- left edge -->
						<TD width=15><IMG height=1 src="images/spacer.gif" width=15 border=0></TD>
						<!-- title image/left margin -->
						<TD width=200><IMG height=1 src="images/spacer.gif" width=200 border=0></TD>
						<!-- main area -->
						<TD width=14><IMG height=1 src="images/spacer.gif" width=14 border=0></TD>
						<!-- right margin -->
						<TD width=11><IMG height=1 src="images/spacer.gif" width=11 border=0></TD>
						<!-- right edge -->
					</TR>
					<TR>
						<TD width=8><IMG height=9 src="images/top_left.gif" width=8 border=0></TD>
						<TD background="images/top.gif" colSpan=3><IMG height=9 src="images/spacer.gif" width=1 border=0></TD>
						<TD width=11><IMG height=9 src="images/top_right.gif" width=11 border=0></TD>
					</TR>
					<TR>
						<TD width=8 background="images/left.gif"><IMG height=15 src="images/spacer.gif" width=8 border=0></TD>
						<TD align=left width=140 bgColor=#000033 colSpan=3><FONT face="verdana, arial, helvetica, sans-serif" color=#ffffff size=-1>&nbsp;<B>登录DRP</B> </FONT></TD>
						<TD width=11 background="images/right.gif"><IMG height=15 src="images/spacer.gif" width=11 border=0></TD>
					</TR>
					<TR>
						<TD width=8><IMG height=9 src="images/middle_left.gif" width=8 border=0></TD>
						<TD background="images/top.gif" colSpan=3><IMG height=9 src="images/spacer.gif" width=1 border=0></TD>
						<TD width=11><IMG height=9 src="images/middle_right.gif" width=11 border=0></TD>
					</TR>
					<TR bgColor=white>
						<TD width=8 background="images/left.gif"><IMG height=1 src="images/spacer.gif" width=8 border=0></TD>
						<TD></TD>
						<TD>
						<TABLE cellSpacing=5 cellPadding=0 align=center background="images/login1x1.gif" border=0 valign="middle">
							<TBODY>
								<TR>
									<TD><BR>
									</TD>
								</TR>
								<TR>
									<TD align=left><FONT face="verdana, arial, helvetica, sans-serif" size=-1>用户名:&nbsp;</FONT></TD>
									<TD align=left><INPUT id="userId" name="userId" type="text" size="20" maxlength="20"><span id="userIdTip"></span></TD>
								</TR>
								<TR>
									<TD align=left><FONT face="verdana, arial, helvetica, sans-serif" size=-1>密&nbsp&nbsp&nbsp码:</FONT></TD>
									<TD align=left><INPUT id="password" name="password" type="password" size="21" maxlength="20"><span id="passwordTip"></span></TD>
								</TR>
								<TR>
									<TD align=left><FONT face="verdana, arial, helvetica, sans-serif" size=-1>验证码：</FONT></TD>
									<TD align=left><INPUT name=userId2 type=text size="6" maxlength="6"> &nbsp; <img src="images/authImage.jpg"></TD>
								</TR>
								<TR>
									<TD><BR>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
						<TD></TD>
						<TD width=8 background="images/right.gif"><IMG height=1 src="images/spacer.gif" width=8 border=0></TD>
					</TR>
					<!-- end of inner main area, right side -->
					<!-- inner button bar -->
					<TR>
						<TD width=8><IMG height=6 src="images/command_top_left.gif" width=8 border=0></TD>
						<TD background="images/command_top.gif" colSpan=3><IMG height=6 src="images/spacer.gif" border=0></TD>
						<TD width=11><IMG height=6 src="images/command_top_right.gif" width=11 border=0></TD>
					</TR>
					<TR bgColor=#cccccc>
						<TD width=8><IMG height=20 src="images/command_mid_left.gif" width=8 border=0></TD>
						<TD><BR>
						</TD>
						<!-- command buttons -->
						<TD align=right colSpan=2>
						<TABLE cellSpacing=0 cellPadding=0 border=0>
							<TBODY>
								<TR>
									<TD><A href="javascript:submitForm()"><IMG height=20 src="images/login_button.gif" width=71 border=0 name=button> </A></TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
						<TD width=11><IMG height=20 src="images/command_mid_right.gif" width=11 border=0></TD>
					</TR>
					<TR>
						<TD width=8><IMG height=8 src="images/command_bottom_left.gif" width=8 border=0></TD>
						<TD background="images/command_bottom.gif" colSpan=3><IMG height=8 src="images/spacer.gif" width=1 border=0></TD>
						<TD width=11><IMG height=8 src="images/command_bottom_right.gif" width=11 border=0></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<CENTER></CENTER>
</FORM>
</BODY>
</HTML>

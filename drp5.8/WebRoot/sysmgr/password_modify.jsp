<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="com.bjpowernode.drp.sysmgr.manager.*" %>
<%@ page import="com.bjpowernode.drp.sysmgr.domain.*" %>        
<% 
	String command = request.getParameter("command");
	if (command != null && command.equals("modify")) {
		User user = (User)session.getAttribute("login_user");
		String password = request.getParameter("newPassword");
		UserManager.getInstance().modifyPassword(user.getUserId(), password);
		user.setPassword(password);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		out.println("��ʾ���޸�����ɹ���");
	}
 %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>�޸�����</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script type="text/javascript">
	
	function init() {
		document.getElementById("oldPassword").focus();
	}
	
	function modifyPasword() {
		if (document.getElementById("oldPassword").value.length < 6) {
			alert("ԭ�벻��С��6λ�ַ���");
			document.getElementById("oldPassword").focus();
			return; 
		}
		if (document.getElementById("responseMsg").innerHTML != "") {
			alert("�����������ԭ���벻��ͬ��");
			document.getElementById("oldPassword").focus();
			return;
		}
		if (document.getElementById("newPassword").value.length < 6) {
			alert("�����벻��С��6λ�ַ���");
			document.getElementById("newPassword").focus();
			return; 
		}
		if (document.getElementById("affirmNewPassowrd").value.length < 6) {
			alert("ȷ�������벻��С��6λ�ַ���");
			document.getElementById("affirmNewPassowrd").focus();
			return; 
		}
		if (document.getElementById("newPassword").value != document.getElementById("affirmNewPassowrd").value) {
			alert("�������ȷ�������������ͬ��");
			document.getElementById("affirmNewPassowrd").focus();
			return;
		}
		with (document.getElementById("userForm")) {
			method = "post";
			action = "password_modify.jsp?command=modify";
			submit();
		}
	}
	
	//---------------------------Ajax begin----------------------------	
	var xmlHttp;
	
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function validate() {
		var oldPassword = document.getElementById("oldPassword").value;
		if (oldPassword != "") {
			createXMLHttpRequest();
			var url = "../servlet/PasswordValidateServlet?oldPassword=" + oldPassword + "&timestampt=" + new Date().getTime();
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = callback;
			xmlHttp.send(null);
		}
	}

	function callback() {
		if(xmlHttp.readyState == 4) {
			if(xmlHttp.status == 200) {
				var responseMsgDiv = document.getElementById("responseMsg");
				if (xmlHttp.responseText != "") {
					responseMsgDiv.innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>";
				}else {
					responseMsgDiv.innerHTML = "";
				}
			}
		}
	}		
	
	//---------------------------Ajax end----------------------------		
</script>
	</head>

	<body class="body1" onload="init()">
		<form name="userForm" id="userForm">
			<div align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					height="51">
					<tr>
						<td class="p1" height="16" nowrap>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="p1" height="35" nowrap>
							&nbsp&nbsp
							<img src="../images/mark_arrow_02.gif" width="14" height="14">
							<b><strong>ϵͳ����&gt;&gt;</strong>�޸�����</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<table width="50%" height="91" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="30">
							<div align="right">
								<font color="#FF0000">*</font>&#21407;&#23494;&#30721;: 
							</div>
						</td>
						<td>
							<input name="oldPassword" type="password" class="text1"
								id="oldPassword" size="25" onblur="validate()">&nbsp<span id="responseMsg"></span>
						</td>
					</tr>
					<tr>
						<td height="27">
							<div align="right">
								<font color="#FF0000">*</font>������:
							</div>
						</td>
						<td>
							<input name="newPassword" type="password" class="text1"
								id="newPassword" size="25">
						</td>
					</tr>
					<tr>
						<td height="34">
							<div align="right">
								<font color="#FF0000">*</font>ȷ������:
							</div>
						</td>
						<td>
							<input name="affirmNewPassowrd" type="password" class="text1"
								id="affirmNewPassowrd" size="25">
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<p>
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="�޸�" onClick="modifyPasword()">
					&nbsp; &nbsp;&nbsp;
				</p>
			</div>
		</form>
	</body>
</html>

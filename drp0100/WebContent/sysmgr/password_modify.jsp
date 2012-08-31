<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.Kimalu.drp.domain.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User u=(User)session.getAttribute("user");
	String userId=u.getUserId(); 
	String password=u.getPassword();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>修改密码</title>
<link rel="stylesheet" href="../style/drp.css">
<script type="text/javascript" src="../script/javascriptx.js"></script>
<script type="text/javascript" src="../script/user_validate.js"></script>
<script type="text/javascript">
	var oldPassword="<%=password%>";
	var user_id="<%=userId%>";
	function modifyPasword() {
		var rs1=document.getElementById("oldPasswordTip").innerHTML=="";
		var rs2=document.getElementById("newPasswordTip").innerHTML=="";
		var rs3=document.getElementById("affirmNewPassowrdTip").innerHTML=="";
		if(rs1&&rs2&&rs3){
			with(document.getElementById("userForm")){
				method='post';
				action="<%=request.getContextPath()%>/sysmgr/UserController?command=updpw&random="+Math.random();
				submit();
			}
		}
	}
	function validateOld(){
		document.getElementById("oldPasswordTip").innerHTML="";
		var pageOldPw=document.getElementById("oldPassword").value;
		//alert(pageOldPw==oldPassword);
		if(pageOldPw!=oldPassword){
			document.getElementById("oldPasswordTip").innerHTML="<font color='red'>密码错误</font>";
			pageOldPw.focus();
		}
	}
	function validateNew(){
		var pageNewPwDom=document.getElementById("newPassword");
		var newPasswordTipDom=document.getElementById("newPasswordTip");
		newPasswordTipDom.innerHTML="";
		validate_password(pageNewPwDom,newPasswordTipDom);
	}
	function preValidateNew(){
		var newPwDom=document.getElementById("newPassword");
		if(newPwDom.value.trim()==""){
			alert("输入确认密码之前，请先输入新密码！");
			newPwDom.focus();
			return;
		}
	}
	function validateAffirm(){
		document.getElementById("affirmNewPassowrdTip").innerHTML="";
		var newPwDom=document.getElementById("newPassword");
		var pageAffirm=document.getElementById("affirmNewPassowrd").value;
		if(pageAffirm!=newPwDom.value){
			document.getElementById("affirmNewPassowrdTip").innerHTML="<font color='red'>确认密码与新密码不一致</font>";
			newPwDom.focus();
		}
	}
</script>
</head>

<body class="body1">
<form name="userForm" id="userForm">
<div align="center">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="51">
	<tr>
		<td class="p1" height="16" nowrap>&nbsp;</td>
	</tr>
	<tr>
		<td class="p1" height="35" nowrap>&nbsp&nbsp <img src="../images/mark_arrow_02.gif" width="14" height="14"> <b><strong>系统管理&gt;&gt;</strong>修改密码</b></td>
	</tr>
</table>
<hr width="100%" align="center" size=0>
<table width="50%" height="91" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30">
		<div align="right"><font color="#FF0000">*</font>原密码:</div>
		</td>
		<td align="left"><input name="oldPassword" type="password" class="text1" id="oldPassword" size="25" onblur="validateOld()"><span id="oldPasswordTip"/></td>
	</tr>
	<tr>
		<td height="27">
		<div align="right"><font color="#FF0000">*</font>新密码:</div>
		</td>
		<td align="left"><input name="newPassword" type="password" class="text1" id="newPassword" size="25" onblur="validateNew()"><span id="newPasswordTip"/></td>
	</tr>
	<tr>
		<td height="34">
		<div align="right"><font color="#FF0000">*</font>确认密码:</div>
		</td>
		<td align="left"><input name="affirmNewPassowrd" type="password" class="text1" id="affirmNewPassowrd" size="25" onblur="validateAffirm()"><span id="affirmNewPassowrdTip"/></td>
	</tr>
</table>
<div id="result"/>
<hr width="100%" align="center" size=0>
<p><input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyPasword()"> &nbsp; &nbsp;&nbsp;</p>
</div>
</form>
</body>
</html>

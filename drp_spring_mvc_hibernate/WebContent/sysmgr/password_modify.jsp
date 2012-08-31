<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>修改密码</title>
<link rel="stylesheet" href="style/drp.css">
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript" src="script/user_validate.js"></script>
<script type="text/javascript">
	//var oldPassword="${user.password}";
//	var user_id="${user.userId}";
	function modifyPasword() {
		var rs2=$("#newPasswordTip").html()=="";
		var rs3=$("#affirmNewPassowrdTip").html()=="";
		alert(rs2&&rs3);
		if(rs2&&rs3){
			$.get("<%=basePath%>/sysmgr/UserController.do?command=modifyPw&random="+Math.random(), { oldPassword:$("#oldPassword").val(), newPassword: $("#newPassword").val() }, function(data){
			    if("success"== data){
			    	alert("修改成功");
			    }else if("pwerror"==data){
			    	alert("原始密码错误,修改失败");
			    };
			  },"text");
		}
	}
	/*
	function validateOld(){
		$("#oldPasswordTip").html("");
		var pageOldPw=$("#oldPassword").val();
		//alert(pageOldPw==oldPassword);
		if(pageOldPw!=oldPassword){
			$("#oldPasswordTip").html("<font color='red'>密码错误</font>");
			pageOldPw.focus();
		}
	}
	*/
	function validateNew(){
		var pageNewPwDom=$("#newPassword");
		var newPasswordTipDom=$("#newPasswordTip");
		newPasswordTipDom.html("");
		validate_password(pageNewPwDom.get(0),newPasswordTipDom.get(0));
	}
	function preValidateNew(){
		var newPwDom=document.getElementById("newPassword");
		if($.trim(newPwDom.val())==""){
			alert("输入确认密码之前，请先输入新密码！");
			newPwDom.focus();
			return;
		}
	}
	function validateAffirm(){
		$("#affirmNewPassowrdTip").html("");
		var newPwDom=$("#newPassword");
		var pageAffirm=$("#affirmNewPassowrd").val();
		if(pageAffirm!=newPwDom.val()){
			$("#affirmNewPassowrdTip").html("<font color='red'>确认密码与新密码不一致</font>");
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
		<td class="p1" height="35" nowrap>&nbsp&nbsp <img src="images/mark_arrow_02.gif" width="14" height="14"> <b><strong>系统管理&gt;&gt;</strong>修改密码</b></td>
	</tr>
</table>
<hr width="100%" align="center" size=0>
<table width="50%" height="91" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30">
		<div align="right"><font color="#FF0000">*</font>原密码:</div>
		</td>
		<td align="left"><input name="oldPassword" type="password" class="text1" id="oldPassword" size="25" ><span id="oldPasswordTip"/></td>
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

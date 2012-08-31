<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>用户维护</title>
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<link rel="stylesheet" href="style/drp.css">

<script type="text/javascript">
	var jsonValue;
	function onload(){
		var url="<%=basePath %>sysmgr/UserController.do?command=getUserList&random="+Math.random();
		ajax(url);
	}
	function ajax(url){
		$.getJSON(url, function(data){
			jsonValue=data;
			displayUserPage();
			});
	}
	function displayUserPage(){
		clearTabel();
		$.each(jsonValue.list,function(i,user){
			display(user);
		});
		$("#pageNo").html(jsonValue.pageNo);
		$("#totalPageNum").html(jsonValue.totalPageNum);
	}
	function display(user){
		var $userListTable=$("#userListTable");
		var $tr=$("<tr></tr>");
		var $td1=$("<td></td>");
		$td1.addClass("rd8");
		$td1.html("<input type='checkBox' name='selectFlag' value='"+user.userId+"' onClick='allSelectOrNot()'/>");
		var $td2=$("<td></td>");
		$td2.addClass("rd8");
		$td2.html(user.userId);
		var $td3=$("<td></td>");
		$td3.addClass("rd8");
		$td3.html(user.userName);
		var $td4=$("<td></td>");
		$td4.addClass("rd8");
		$td4.html(user.contactTel);
		var $td5=$("<td></td>");
		$td5.addClass("rd8");
		$td5.html(user.email);
		var $td6=$("<td></td>");
		$td6.addClass("rd8");
		if(user.createDate!=null && user.createDate!=""){
			var date=new Date(parseInt(user.createDate.time));
			$td6.html(date.toLocaleString());
		}else{
			$td6.html("");
		}
		$tr.append($td1);
		$tr.append($td2);
		$tr.append($td3);
		$tr.append($td4);
		$tr.append($td5);
		$tr.append($td6);
		$userListTable.append($tr);
	}
	function clearTabel(){
		$("#userListTable tr:gt(0)").remove();
	}
	
	function addUser() {
		window.self.location = "user_add.jsp";
	}
	function modifyUser(){
		if(checkSelectOnlyOne()){
			var userId=getSelectElement().value;
		}else{
			return false;
		}
		var $form=$("#userform");
		var url="<%=basePath %>sysmgr/UserController.do?command=showModify&userId="+userId;
		$form.attr("action",url);
		$form.attr("method","post");
		$form.submit();
	}
	function getSelectElement(){
		return getSelectElements()[0];
	}
	function deleteUser(){
		if(checkSelected()){
			var $userIds=getSelectElements();
			var userIds=new Array();
			$.each($userIds,function(i,checkBox){
				userIds.push(checkBox.value);
			});
		}else{
			return false;
		}
		var $form=$("#userform");
		var url="<%=basePath %>sysmgr/UserController.do?command=del&userIds="+userIds;
		$form.attr("action",url);
		$form.attr("method","post");
		$form.submit();
	}
	function getSelectElements(){
		return $("#userListTable tr:gt(0) input:checked");
	}
	function checkSelectOnlyOne(){
		var selectCount=$("#userListTable tr:gt(0) input:checked").length;
		if(selectCount>1){
			alert("只能选中一条记录");
			return false;
		}else if(selectCount<1){
			alert("必须选中一条记录");
			return false;
		}
		return true;
	}
	function checkSelected(){
		var selectCount=$("#userListTable tr:gt(0) input:checked").length;
		if(selectCount<1){
			alert("至少选中一条记录");
			return false;
		}
		return true;
	}
	
	
	function topPage() {
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			var url="<%=basePath%>sysmgr/UserController.do?command=getUserList&pageNo=1&random="+Math.random();
			ajax(url);
		}
	}
	function previousPage() {
		var pn;
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			pn=parseInt(pageNo)-1;
			var url="<%=basePath%>sysmgr/UserController.do?command=getUserList&pageNo="+pn+"&random="+Math.random();
			alert(url);
			ajax(url);
		}
	}

	function nextPage() {
		var pn;
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			pn=parseInt(pageNo)+1;
			var url="<%=basePath%>sysmgr/UserController.do?command=getUserList&pageNo="+pn+"&random="+Math.random();
			ajax(url);
		}
	}

	function bottomPage() {
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			var url="<%=basePath%>sysmgr/UserController.do?command=getUserList&pageNo="+totalPageNum+"&random="+Math.random();
			ajax(url);
		}
	}
	
	function checkAll(){
		var $checkBoxes=$("input[name=selectFlag]");
		$checkBoxes.each(function(){
			$(this).attr("checked",$("#ifAll").attr("checked"));
		});
	}
	function allSelectOrNot(){
		if(getSelectElements().length==$("#userListTable tr:gt(0)").length){
			$("#userListTable tr:eq(0) input").attr("checked",true);
		}else{
			$("#userListTable tr:eq(0) input").attr("checked",false);
		}
	}
</script>
</head>
<body class="body1" onload="onload()">
<form name="userform" id="userform">
<div align="center">
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="35">
	<tr>
		<td class="p1" height="18" nowrap>&nbsp;</td>
	</tr>
	<tr>
		<td width="522" class="p1" height="17" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14"> &nbsp; <b>系统管理&gt;&gt;用户维护</b></td>
	</tr>
</table>
<hr width="100%" align="center" size=0>
</div>
<table width="95%" height="20" border="0" align="center" cellspacing="0" class="rd1" id="toolbar">
	<tr>
		<td width="49%" class="rd19"><font color="#FFFFFF">查询列表</font></td>
		<td width="27%" nowrap class="rd16">
		<div align="right"></div>
		</td>
	</tr>
</table>
<table id="userListTable" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
	<tr>
		<td width="55" class="rd6"><input type="checkbox" id="ifAll" name="ifAll" onClick="checkAll()"></td>
		<td width="119" class="rd6">用户代码</td>
		<td width="152" class="rd6">用户名称</td>
		<td width="166" class="rd6">联系电话</td>
		<td width="150" class="rd6">email</td>
		<td width="153" class="rd6">创建日期</td>
	</tr>
	</table>
<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2">
			<div align="left">
				<font color="#FFFFFF">共</font>
				<font id="totalPageNum" color="#FFFFFF"></font>
				<font color="#FFFFFF">页</font>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<font color="#FFFFFF">当前第</font>
				&nbsp; 
				<font id="pageNo" color="#FF0000"></font>
				&nbsp;
				 <font color="#FFFFFF">页</font>
			</div>
		</td>
		<td nowrap class="rd19">
			<div align="right">
				<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页" onClick="topPage()"> 
				<input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage" value=" &lt;  " title="上页" onClick="previousPage()">
				<input name="btnNextPage" class="button1" type="button" id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()">
				<input name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onClick="bottomPage()">
				<input name="btnAdd" type="button" class="button1" id="btnAdd" value="添加" onClick="addUser()">
				<input name="btnDelete" class="button1" type="button" id="btnDelete" value="删除" onClick="deleteUser()">
				<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyUser()">
			</div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
</form>
</body>
</html>

<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Kimalu.drp.domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>用户维护</title>
<link rel="stylesheet" href="../style/drp.css">
<script type="text/javascript">
	var xmlHttpRequest;
	var currentPageNo;
	var pageSize;
	var totalNum;
	var totalPageNum;
	
	function onload(){
		var url="<%=request.getContextPath()%>/sysmgr/UserController?command=query&random="+Math.random();
		queryAjax(url);
	}
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) {
			xmlHttpRequest = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function queryAjax(url) {
		createXMLHttpRequest();
		clearTabel(document.getElementById("userListTable"));
		xmlHttpRequest.open("GET", url, true);
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4) { //Ajax引擎初始化成功
				if (xmlHttpRequest.status == 200) { //http协议成功
					displayUserPage(xmlHttpRequest.responseText);
				} else {
					alert("请求失败，错误码=" + xmlHttpRequest.status);
				}
			}
		}
		xmlHttpRequest.send(null);
	}
	
	function displayUserPage(pageJson){
		//alert(pageJson);
		var userPage = eval('('+pageJson+')');
		currentPageNo=userPage.currentPageNo;
		pageSize=userPage.pageSize;
		totalNum=userPage.totalNum;
		totalPageNum=userPage.totalPageNum;
		var userList=userPage.list;
		for ( var i = 0; i < userList.length; i++) {
			display(userList[i]);
		}
		document.getElementById("pageNo").innerHTML=currentPageNo;
		document.getElementById("totalPageNum").innerHTML=totalPageNum;
	}
	function clearTabel(table){
		var trList=table.getElementsByTagName("tr");
		for(var i=1;i<trList.length;i++){
			trList[i].parentNode.removeChild(trList[i]);
			i--;
		}
	}
	function display(user){
		var userListTable=document.getElementById("userListTable");
		//alert(userListTable.nodeName);
		var tr=userListTable.insertRow();
		var td1=tr.insertCell();
		td1.className="rd8";
		td1.innerHTML="<input type='checkBox' name='selectFlag' value="+user.userId+" />";
		var td2=tr.insertCell();
		td2.className="rd8";
		td2.innerHTML=user.userId;
		var td3=tr.insertCell();
		td3.className="rd8";
		td3.innerHTML=user.userName;
		var td4=tr.insertCell();
		td4.className="rd8";
		td4.innerHTML=user.contactTel;
		var td5=tr.insertCell();
		td5.className="rd8";
		td5.innerHTML=user.email;
		var td6=tr.insertCell();
		td6.className="rd8";
		if(user.createDate!=null && user.createDate!=""){
			var date=new Date(parseInt(user.createDate.time));
			td6.innerHTML=date.toLocaleString();
		}else{
			td6.innerHTML="";
		}
	}

	function addUser() {
		window.self.location = "user_add.jsp";
	}

	function modifyUser() {
		var selectFlags=document.getElementsByName("selectFlag");
		var flag=countSelected(selectFlags);
		if(flag<1){
			alert("请选择要删除的用户");
			return false
		}else if(flag!=1){
			alert("只能选中一个用户");
			return false;
		}else{
			with(document.getElementById("userform")){
				action="user_modify.jsp";
				method="post";
				submit();
			}
		}
	}
	function countSelected(selectFlags){
		var flag=0;
		for(var i=0;i<selectFlags.length;i++){
			if(selectFlags[i].checked){
				flag++;
			}
		}
		return flag;
	}
	function deleteUser() {
		var selectFlags=document.getElementsByName("selectFlag");
		var flag=countSelected(selectFlags);
		if(flag>=1){
			if(window.confirm("确定删除?")){
				var url="<%=request.getContextPath()%>/sysmgr/UserController?command=del&random="+Math.random();
				with(document.getElementById("userform")){
					action=url;
					method="post";
					submit();
				}
			}
		}else{
			alert("请选择要删除的用户");
			return false;
		}
	}

	function checkAll() {
		var selectFlags=document.getElementsByName("selectFlag");
		var selectAll=document.getElementById("ifAll");
		//alert(selectAll.checked);
		for(var i=0;i<selectFlags.length;i++){
			selectFlags[i].checked=selectAll.checked;
		}
	}

	function topPage() {
		if(currentPageNo!=1){
			var url="<%=request.getContextPath()%>/sysmgr/UserController?command=query&pageNo=1&random="+Math.random();
			queryAjax(url);
		}
	}

	function previousPage() {
		var pn;
		if(currentPageNo!=1){
			pn=parseInt(currentPageNo)-1;
			var url="<%=request.getContextPath()%>/sysmgr/UserController?command=query&pageNo="+pn+"&random="+Math.random();
			queryAjax(url);
		}
	}

	function nextPage() {
		var pn;
		if(currentPageNo!=totalPageNum){
			pn=parseInt(currentPageNo)+1;
			var url="<%=request.getContextPath()%>/sysmgr/UserController?command=query&pageNo="+pn+"&random="+Math.random();
			queryAjax(url);
		}
	}

	function bottomPage() {
		if(currentPageNo!=totalPageNum){
			var url="<%=request.getContextPath()%>/sysmgr/UserController?command=query&pageNo="+totalPageNum+"&random="+Math.random();
			queryAjax(url);
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
		<td width="522" class="p1" height="17" nowrap><img src="../images/mark_arrow_02.gif" width="14" height="14"> &nbsp; <b>系统管理&gt;&gt;用户维护</b></td>
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
				<font id="totalPageNum" color="#FFFFFF">xx</font>
				<font color="#FFFFFF">页</font>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<font color="#FFFFFF">当前第</font>
				&nbsp; 
				<font id="pageNo" color="#FF0000">1</font>
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
				<input name="btnDelete" class="button1" type="button" id="btnDelete" value="删除" onClick="deleteUser()"> <input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyUser()">
			</div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
</form>
</body>
</html>

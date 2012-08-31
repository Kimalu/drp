<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Kimalu.drp.service.ClientService"%>
<%@ page import="com.Kimalu.drp.domain.Client"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//request.setCharacterEncoding("UTF-8");
	//String uri=request.getRequestURI();
	//String url=request.getRequestURL().toString();
	//System.out.println(url);
	int id = Integer.parseInt(request.getParameter("id"));
	ClientService cs = ClientService.getInstance();
	Client region = cs.queryById(id);
	if ("modify".equals(request.getParameter("command"))) {
		String newName = request.getParameter("name");
		//String name=new String(newName.getBytes("iso8859-1"),"utf-8"); 
		region.setName(newName);
		cs.updateClient(region);
	}
%>
<html>
<head>
<link rel="stylesheet" href="../style/drp.css" />
<script src="../script/client_validate.js"></script>
<script type="text/javascript" src="../script/javascriptx.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>修改区域节点</title>
<script type="text/javascript">
	var xmlHttpRequest;

function createXMLHttpRequest() {
	if(window.XMLHttpRequest) {
		xmlHttpRequest = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function queryAjax(url) {
	createXMLHttpRequest();
	//clearTabel(document.getElementById("userListTable"));
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.onreadystatechange = function() {
		if (xmlHttpRequest.readyState == 4) { //Ajax引擎初始化成功
			if (xmlHttpRequest.status == 200) { //http协议成功
				window.parent.location.reload();
			} else {
				alert("请求失败，错误码=" + xmlHttpRequest.status);
			}
		}
	}
	xmlHttpRequest.send(null);
}

	function modifyRegion() {
		//document.getElementById("command").value="modify";
		var name=document.getElementById("name").value.trim();
		if(name==""){
			alert("区域名字不能为空");
		}
		queryAjax("client_node_modify.jsp?command=modify&id=<%=region.getId()%>&name="+encodeURIComponent(name)+"&random="+Math.random());
		//with(document.getElementById("regionForm")){
		//	method="post";
		//	action="client_node_modify.jsp";
		//	submit();
		//}
		//window.parent.location.reload();
	}

	function goBack() {
		window.self.location = "client_node_crud.html";
	}
</script>
</head>

<body class="body1">
<form id="regionForm" name="regionForm" >
<!-- <input type="hidden" id="command" name="command"/> -->
<%-- <input type="hidden" id="id" name="id" value="<%=region.getId() %>"/> --%>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
	<tr>
		<td width="522" class="p1" height="2" nowrap="nowrap"><img src="../images/mark_arrow_03.gif" width="14" height="14" /> &nbsp; <b>基础数据管理&gt;&gt;分销商维护&gt;&gt;修改区域节点</b></td>
	</tr>
</table>
<hr width="97%" align="center" size="0" />
<p></p>
<p></p>
<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="213">
		<div align="right"><font color="#FF0000">*</font>区域名称：</div>
		</td>
		<td width="410"><label> <input name="name" type="text" class="text1" id="name" value="<%=region.getName() %>"/> </label></td>
	</tr>
</table>
<p></p>
<label> <br />
</label>
<hr />
<p align="center"><input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyRegion()" /> &nbsp;&nbsp;&nbsp;&nbsp; <input name="btnModify" class="button1"
	type="button" id="btnModify" value="返回" onclick="goBack()" /></p>
</form>
</body>
</html>

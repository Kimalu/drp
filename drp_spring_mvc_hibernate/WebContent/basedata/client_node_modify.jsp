<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="style/drp.css" />
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改区域节点</title>
<script type="text/javascript">
	function modifyRegion() {
		var id='${client.id}';
		var name=$.trim($("#name").val());
		window.parent.location="<%=basePath%>basedata/ClientController.do?command=doModifyRegion&id="+id+"&name="+name;
	}

	function goBack() {
		window.self.location = "client_node_crud.html";
	}
</script>
</head>

<body class="body1">
<form id="regionForm" name="regionForm" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
	<tr>
		<td width="522" class="p1" height="2" nowrap="nowrap"><img src="images/mark_arrow_03.gif" width="14" height="14" /> &nbsp; <b>基础数据管理&gt;&gt;分销商维护&gt;&gt;修改区域节点</b></td>
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
		<td width="410"><label> <input name="name" type="text" class="text1" id="name" value="${client.name}"/> </label></td>
	</tr>
</table>
<p></p>
<label> <br/>
</label>
<hr />
<p align="center">
	<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyRegion()" />
		&nbsp;&nbsp;&nbsp;&nbsp; 
	<input name="btnModify" class="button1" type="button" id="btnModify" value="返回" onclick="goBack()" />
</p>
</form>
</body>
</html>

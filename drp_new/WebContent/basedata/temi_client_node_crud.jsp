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
		<title>终端客户维护</title>
		
		<script type="text/javascript">
			function addTemiClient(){
				var id=$("#id").val();
				window.self.location="<%=basePath%>basedata/TemiClientController.do?command=showAddTemiClientPage&id="+id;
			}
		</script>
	</head>
	<body class="body1">
		<form id="regionForm" name="regionForm" method="post" action="">
		<input type="hidden" name="id" id="id" value="${client.id}">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="images/mark_arrow_02.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;终端客户维护</b>
					</td>
				</tr>
			</table>
			<hr width="97%" align="center" size="0" />
			<p></p>
			<p></p>
			<table width="95%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="213">
						<div align="right">
							当前区域名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="name" type="text" class="text1" id="name" size="40"
								maxlength="40" readonly="true" value="${client.name }"/>
						</label>
					</td>
				</tr>
			</table>
			<p></p>
			<label>
				<br />
			</label>
			<hr />
			<p align="center">
				<!--<input name="btnAddRegion" type="button" class="button1"
					id="btnAddRegion"
					onClick="self.location='temi_client_node_add.html'" value="添加区域" />
				&nbsp;
				<input name="btnDeleteRegion" type="button" class="button1"
					id="btnDeleteRegion" value="删除区域" />
				&nbsp;
				<input name="btnModifyRegion" type="button" class="button1"
					id="btnModifyRegion"
					onClick="self.location='temi_client_node_modify.html'" value="修改区域" />
				&nbsp;
				-->
				<input name="btnAddTemiClient" type="button" class="button1"
					id="btnAddTemiClient"
					onClick="addTemiClient()" value="添加终端客户" />
			</p>
		</form>
	</body>
</html>
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
		<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
		<link rel="stylesheet" href="style/drp.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>分销商维护</title>
		
		<script type="text/javascript">
		
		
		
		function modifyClient(){
			self.location='<%=basePath%>basedata/ClientController.do?command=showClientModifyPage&id=${client.id}';
		}
		function showDetail(){
			self.location='<%=basePath%>basedata/ClientController.do?command=showClientDetailPage&id=${client.id}';
		}
		function delClient(){
			window.parent.location='<%=basePath%>basedata/ClientController.do?command=delClient&id=${client.id}';
			
		}
		</script>
	</head>

	<body class="body1">
		<form id="clientForm" name="clientForm" method="post" action="">
		<input type="hidden" name='id' id='id' value="${client.id}">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="images/mark_arrow_02.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;分销商维护</b>
					</td>
				</tr>
			</table>
			<hr width="97%" align="center" size="0" />
			<p>
			<p>
			<table width="95%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="213">
						<div align="right">
							当前分销商名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="clientName" type="text" class="text1" id="clientName" readonly="true" value="${client.name}"/>
						</label>
					</td>
				</tr>
			</table>
			<p>
				<label>
					<br />
				</label>
			<hr />
			<p align="center">
				<input name="btnModifyClient" type="button" class="button1" id="btnModifyClient" onClick="modifyClient()" value="修改分销商" />
				&nbsp;
				<input name="btinDeleteClient" type="button" class="button1" id="btinDeleteClient" value="删除分销商" onclick="delClient()"/>
				&nbsp;
				<input name="btnViewDetail" type="button" class="button1" id="btnViewDetail" onClick="showDetail()" value="查看详细信息" />
			</p>
		</form>
	</body>
</html>

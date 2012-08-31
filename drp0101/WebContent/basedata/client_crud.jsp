<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.Kimalu.drp.service.ClientService" %>
    <%@page import="com.Kimalu.drp.domain.Client" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String command=request.getParameter("command");
	if("del".equals(command)){
		
		ClientService cs=ClientService.getInstance();
		Client c=cs.queryById(Integer.parseInt(request.getParameter("id")));
		cs.del(c);
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
		<title>分销商维护</title>
		
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
		
		
		function modifyClient(){
			self.location='client_modify.jsp?id=<%=request.getParameter("id")%>';
		}
		function showDetail(){
			self.location='client_detail.jsp?id=<%=request.getParameter("id")%>';
		}
		function delClient(){
			var url='client_crud.jsp?command=del&id=<%=request.getParameter("id")%>';
			queryAjax(url);
		}
		</script>
	</head>

	<body class="body1">
		<form id="clientForm" name="clientForm" method="post" action="">
		<input type="hidden" name='id' id='id' value="<%=request.getParameter("id") %>">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_02.gif" width="14" height="14" />
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
							<input name="clientName" type="text" class="text1" id="clientName" readonly="true" value="<%=request.getParameter("name") %>"/>
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
				<input name="btnModifyClient" type="button" class="button1"
					id="btnModifyClient" onClick="modifyClient()"
					value="修改分销商" />
				&nbsp;
				<input name="btinDeleteClient" type="button" class="button1"
					id="btinDeleteClient" value="删除分销商" onclick="delClient()"/>
				&nbsp;
				<input name="btnViewDetail" type="button" class="button1"
					id="btnViewDetail" onClick="showDetail()"
					value="查看详细信息" />
			</p>
		</form>
	</body>
</html>

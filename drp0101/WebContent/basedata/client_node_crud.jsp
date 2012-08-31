<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Kimalu.drp.service.ClientService" %>
<%@ page import="com.Kimalu.drp.domain.Client" %>
<%request.setCharacterEncoding("UTF-8"); %>
<% 
	if("del".equals(request.getParameter("command"))){
	int id=Integer.parseInt(request.getParameter("id"));
	ClientService cs=ClientService.getInstance();
	Client region=cs.queryById(id);
	cs.del(region);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
		
		

	function addRegion() {
		window.self.location = "client_node_add.jsp?pid=<%=request.getParameter("id")%>";	
	}
	
	function modifyRegion() {
		clientForm.method="post";
		clientForm.action="client_node_modify.jsp";
		clientForm.submit();
	}
	
	function deleteRegion() {
		var url="client_node_crud.jsp?id="+document.getElementById("id").value+"&command=del&random="+Math.random();
		queryAjax(url);
	}
	
	function addClient() {
		window.self.location = "client_add.jsp?id=<%=request.getParameter("id")%>";
	}
	
</script>
	</head>

	<body class="body1">
		<form id="clientForm" name="clientForm">
		<input type="hidden" id="id" name="id" value="<%=request.getParameter("id") %>"/>
		<input type="hidden" id="command" name="command" />
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
							<input name="name" type="text" class="text1" id="name" readonly="true" value="<%=request.getParameter("name")%>"/>
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
				<input name="btnAddRegion" type="button" class="button1"
					id="btnAddRegion" onClick="addRegion()" value="添加区域" />
				&nbsp;
				<input name="btnDeleteRegion" type="button" class="button1"
					id="btnDeleteRegion" value="删除区域" onClick="deleteRegion()" />
				&nbsp;
				<input name="btnModifyRegion" type="button" class="button1"
					id="btnModifyRegion" onClick="modifyRegion()" value="修改区域" />
				&nbsp;
				<input name="btnAddClient" type="button" class="button1"
					id="btnAddClient" onClick="addClient()" value="添加分销商" />
			</p>
		</form>
	</body>
</html>
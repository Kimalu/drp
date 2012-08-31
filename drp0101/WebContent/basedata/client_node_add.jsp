<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Kimalu.drp.service.ClientService"%>
<%@ page import="com.Kimalu.drp.domain.Client"%>
<%@ page import="com.Kimalu.drp.domain.ClientLevel"%>
<%@ page import="com.Kimalu.drp.util.IDCreator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int pid=Integer.parseInt(request.getParameter("pid"));
if("add".equals(request.getParameter("command"))){
	String name=request.getParameter("name");
	Client region=new Client();
	region.setId(IDCreator.getInstance().getID("t_client"));
	region.setPid(pid);
	region.setName(name);
	ClientLevel cl=new ClientLevel();
	region.setClientLevel(cl);
	region.setIsLead("y");
	region.setIsClient("n");
	ClientService cs=ClientService.getInstance();
	cs.addClient(region);
}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<script type="text/javascript" src="../script/javascriptx.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
		<title>添加区域节点</title>
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

			function add() {
				var name=document.getElementById("name").value.trim();
				if(name==""){
					alert("区域名字不能为空");
				}
				var command=document.getElementById("command").value;
				queryAjax("client_node_add.jsp?command="+command+"&pid=<%=pid%>&name="+encodeURIComponent(name)+"&random="+Math.random());
			}
		</script>
	</head>

	<body class="body1">
		<form name="regionForm" method="post" action="">
		<input type="hidden" id="command" name="command" value="add"/>
		<input type="hidden" id="pid" name="pid" value="<%=pid %>"/>
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_03.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;添加区域节点</b>
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
							<font color="#FF0000">*</font>区域名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="name" type="text" class="text1" id="name" />
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
				<input name="btnAdd" class="button1" type="button" value="添加" onclick="add();"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="btnBack" class="button1" type="button" value="返回"
					onclick="history.go(-1)" />
			</p>
		</form>
	</body>
</html>

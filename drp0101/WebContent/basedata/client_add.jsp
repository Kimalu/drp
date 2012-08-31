<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Kimalu.drp.service.ClientService" %>
<%@ page import="com.Kimalu.drp.domain.ClientLevel" %>
<%@ page import="com.Kimalu.drp.domain.*" %>
<%@ page import="com.Kimalu.drp.daoImpl.*" %>
<%@ page import="com.Kimalu.drp.util.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	
	List<AbstractDataDict> list=DataDictDaoImpl.getInstance().getListByType("A");
	if("add".equalsIgnoreCase(request.getParameter("command"))){
		int id=IDCreator.getInstance().getID("t_client");
		//System.out.println(request.getParameter("pid"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		String clientId=request.getParameter("clientId");
		String clientName=request.getParameter("clientName");
		ClientLevel cl=new ClientLevel();
		cl.setId(request.getParameter("clientLevel"));
		String bankAcctNo=request.getParameter("bankAcctNo");
		String contactTel=request.getParameter("contactTel");
		String address=request.getParameter("address");
		String zipCode=request.getParameter("zipCode");
		Client client=new Client();
		client.setId(id);
		client.setPid(pid);
		client.setName(clientName);
		client.setClientId(clientId);
		client.setClientLevel(cl);
		client.setContactTel(contactTel);
		client.setBankAcctNo(bankAcctNo);
		client.setAddress(address);
		client.setZipCode(zipCode);
		client.setIsLead("y");
		client.setIsClient("y");
		ClientService cs=ClientService.getInstance();
		cs.addClient(client);
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加分销商</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
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
			function addClient(){
				var command="add";
				var pid=<%=request.getParameter("id")%>;
				var clientId=document.getElementById("clientId").value;
				var clientName=document.getElementById("clientName").value;
				var clientLevel=document.getElementById("clientLevel").value;
				var contactTel=document.getElementById("contactTel").value;
				var bankAcctNo=document.getElementById("bankAcctNo").value;
				var address=document.getElementById("address").value;
				var zipCode=document.getElementById("zipCode").value;
				var url="client_add.jsp?command="+command+"&pid="+pid+"&clientId="+clientId+"&contactTel="+contactTel+"&clientName="+encodeURIComponent(clientName)+"&clientLevel="+clientLevel+"&bankAcctNo="+bankAcctNo+"&address="+address+"&zipCode="+zipCode+"&random="+Math.random();
				queryAjax(url);
			}
		</script>
	</head>
	<body class="body1">
		<form name="form1">
		<!-- <input type="hidden" id="pid" value="<%=request.getParameter("id")%>"> -->
		<!--<input type="hidden" id="command" value="add"/>-->
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="8">
					<tr>
						<td width="522" class="p1" height="2" nowrap>
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;添加分销商</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								<font color="#FF0000">*</font>分销商代码:&nbsp;
							</div>
						</td>
						<td align="left" width="78%">
							<input name="clientId" type="text" class="text1" id="clientId"
								size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>分销商名称:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="clientName" type="text" class="text1"
								id="clientName" size="40" maxlength="40">
						</td>
					</tr>
					<tr>
						<td height="15">
							<div align="right">
								<font color="#FF0000">*</font>分销商类型:&nbsp;
							</div>
						</td>
						<td align="left" >
							<select name="clientLevel" class="select1" id="clientLevel">
							<%
								for(Iterator<AbstractDataDict> i=list.iterator();i.hasNext();){ 
								AbstractDataDict add=i.next(); 
							%>
								<option value="<%=add.getId() %>">
									<%=add.getName() %>
								</option>
							<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								银行帐号:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="bankAcctNo" type="text" class="text1"
								id="bankAcctNo" size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="contactTel" type="text" class="text1"
								id="contactTel" size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								地址:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="address" type="text" class="text1" id="address"
								size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								邮编:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="zipCode" type="text" class="text1" id="zipCode"
								size="10" maxlength="10">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="添加" onclick="addClient()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="history.go(-1)" />
				</div>
			</div>
		</form>
	</body>
</html>

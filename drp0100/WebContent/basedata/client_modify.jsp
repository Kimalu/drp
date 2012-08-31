<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Kimalu.drp.service.ClientService" %>
<%@ page import="com.Kimalu.drp.domain.ClientLevel" %>
<%@ page import="com.Kimalu.drp.domain.*" %>
<%@ page import="com.Kimalu.drp.daoImpl.*" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("Utf-8");
	String id=request.getParameter("id");
	ClientService cs=ClientService.getInstance();
	Client client=cs.queryById(Integer.parseInt(id));
	
	ClientLevel cl=client.getClientLevel();
	List<AbstractDataDict> list=DataDictDaoImpl.getInstance().getListByType("A");
	
	if("modify".equals(request.getParameter("command"))){
		String clientId=request.getParameter("clientId");
		String clientName=request.getParameter("clientName");
		String clientLevel=request.getParameter("clientLevel");
		String bankAcctNo=request.getParameter("bankAcctNo");
		String contactTel=request.getParameter("contactTel");
		String address=request.getParameter("address");
		String zipCode=request.getParameter("zipCode");
		client.setClientId(clientId);
		client.setName(clientName);
		ClientLevel cl2=new ClientLevel();
		cl2.setId(clientLevel);
		client.setClientLevel(cl2);
		client.setBankAcctNo(bankAcctNo);
		client.setContactTel(contactTel);
		client.setAddress(address);
		client.setZipCode(zipCode);
		cs.updateClient(client);
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>修改分销商</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script language="javascript">
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
			xmlHttpRequest.open("POST", url, true);
			xmlHttpRequest.onreadystatechange = function() {
				if (xmlHttpRequest.readyState == 4) { //Ajax引擎初始化成功
					if (xmlHttpRequest.status == 200) { //http协议成功
						window.parent.location.reload();
					} else {
						alert("请求失败，错误码=" + xmlHttpRequest.status);
					}
				}
			};
			xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
			var command=document.getElementById("command").value;
			var id=document.getElementById("id").value;
			var clientId=document.getElementById("clientId").value;
			var clientName=document.getElementById("clientName").value;
			var clientLevel=document.getElementById("clientLevel").value;
			var bankAcctNo=document.getElementById("bankAcctNo").value;
			var contactTel=document.getElementById("contactTel").value;
			var address=document.getElementById("address").value;
			var	zipCode=document.getElementById("zipCode").value;
			var	param="command="+command+"&id="+id+"&clientId="+clientId+"&clientName="+clientName+"&clientLevel="+clientLevel+"&bankAcctNo="+bankAcctNo+"&contactTel="+contactTel+"&address="+address+"&zipCode="+zipCode+"&random="+Math.random();
			alert(param);
			xmlHttpRequest.send(param);
		}

			function update(){
				queryAjax("client_modify.jsp");
			}
		</script>
	</head>

	<body class="body1">
		<form name="clientForm" target="_self" id="clientForm">
		<input type="hidden" id="command" name="command" value="modify"/>
		<input type="hidden" id="id" name="id" value="<%=id%>"/>
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
							<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;修改分销商</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								分销商代码:&nbsp;
							</div>
						</td>
						<td align="left" width="78%">
							<input  name="clientId" type="text" class="text1" id="clientId"
								size="10" maxlength="10" readonly="true" value="<%=client.getClientId() %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>分销商名称:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="clientName" type="text" class="text1"
								id="clientName" size="40" maxlength="40" value="<%=client.getName() %>">
						</td>
					</tr>
					<tr>
						<td height="15">
							<div align="right">
								<font color="#FF0000">*</font>分销商类型:&nbsp;
							</div>
						</td>
						<td align="left">
							<select name="clientLevel" class="select1" id="clientLevel" >
							<% for(Iterator<AbstractDataDict> i=list.iterator();i.hasNext();){ 
								AbstractDataDict add=i.next(); 
								if(add.getName().equals(client.getClientLevel().getName())){
							%>
								<option value="<%=add.getId() %>" selected="selected">
								<%}else{ %>
									<option value="<%=add.getId() %>">
								<%} %>
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
						<td align="left">
							<input name="bankAcctNo" type="text" class="text1"
								id="bankAcctNo" size="10" maxlength="10" value="<%=client.getBankAcctNo()==null?"":client.getBankAcctNo() %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="contactTel" type="text" class="text1"
								id="contactTel" size="10" maxlength="10" value="<%=client.getContactTel()==null?"":client.getContactTel() %>"> 
						</td>
					</tr>
					<tr >
						<td height="26">
							<div align="right">
								地址:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="address" type="text" class="text1" id="address"
								size="10" maxlength="10" value="<%=client.getAddress()==null?"":client.getAddress() %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								邮编:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="zipCode" type="text" class="text1" id="zipCode"
								size="10" maxlength="10" value="<%=client.getZipCode()==null?"":client.getZipCode() %>">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="修改" onclick="update()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="history.go(-1)" />
				</div>
			</div>
		</form>
	</body>
</html>

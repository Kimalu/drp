<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改终端客户</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
		<script type="text/javascript">
		function getClientType(url){
			$.getJSON(url, function(data){
				for(index in data){
					displayClientType(data[index]);
				}
			});
		}
		
		function displayClientType(clientType){
			
			var $option=$("<option></option>");
			$option.html(clientType.name);
			$option.val(clientType.id);
			if(clientType.id=='${temiClient.temiClientType.id}'){
				$option.attr("selected",true);
			}
			$("#temiType").append($option);
		}
		
		function init(){
			var url="<%=basePath%>basedata/TemiClientController.do?command=getTemiClientType";
			getClientType(url);
		}
		
		function doModify(){
			
			var clientJson;
			var client="{";
			$("input:not(:button),select").each(
				function(i){
					client+='"'+$(this).attr("name")+'"'+":"+'"'+$(this).val()+'"'+",";
				}
			);
			clientJson=eval("["+client.substring(0, client.length-1)+"}]");
			var url="<%=basePath%>basedata/TemiClientController.do?command=modifyTemiClient";
			$.post(url,clientJson[0],function(){
				window.parent.location.reload();
			});
		}
		</script>
	</head>

	<body class="body1" onload="init()">
		<form name="temiClientForm" target="_self" id="temiClientForm">
		<input type="hidden" id="id" name="id" value="${temiClient.id}"/>
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
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;修改终端客户</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								终端客户代码:&nbsp;
							</div>
						</td>
						<td width="78%" align="left">
							<input name="ClientNo" type="text" class="text1" id="ClientNo"
								size="10" maxlength="10" readonly="true" value="${temiClient.clientNo }">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>终端客户名称:&nbsp;
							</div>
						</td>
						<td  align="left">
							<input name="name" type="text" class="text1" id="name"
								size="10" maxlength="10" value="${temiClient.name }">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>终端客户类型:&nbsp;
							</div>
						</td>
						<td align="left">
							<select name="temiClientType.id" class="select1" id="temiType">
								
							</select>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系人:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="contactPerson" type="text" class="text1" id="contactPerson"
								size="10" maxlength="10" value="${temiClient.contactPerson }">
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
								id="contactTel" size="10" maxlength="10" value="${temiClient.contactTel }"/>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系地址:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="address" type="text" class="text1" id="address"
								size="10" maxlength="10" value="${temiClient.address }">
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
								size="10" maxlength="10" value="${temiClient.zipCode }">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onclick="doModify()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack" value="返回" onclick="history.go(-1)" />
				</div>
			</div>
		</form>
	</body>
</html>
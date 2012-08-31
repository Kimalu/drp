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
		<title>添加分销商</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
		<script type="text/javascript">
			function getClientLevel(url){
				$.getJSON(url, function(data){
					for(index in data){
						displayClientLevel(data[index]);
					}
				});
			}
			
			function displayClientLevel(clientLevel){
				var $option=$("<option></option>");
				$option.html(clientLevel.name);
				$option.val(clientLevel.id);
				$("#clientLevel").append($option);
			}
			
			function init(){
				var url="<%=basePath%>basedata/ClientController.do?command=getClientLevel";
				getClientLevel(url);
			}
			
			function addClient(){
				
				var clientJson;
				var client="{";
				$("input:not(:button),select").each(
					function(i){
						client+='"'+$(this).attr("name")+'"'+":"+'"'+$(this).val()+'"'+",";
					}
				);
				//alert(client.substring(0, client.length-1)+"}");
				clientJson=eval("["+client.substring(0, client.length-1)+"}]");
				//alert(clientJson[0].name);
				//$(window.parent).load("<%=basePath%>basedata/ClientController.do?command=modifyClient",clientJson);
				var url="<%=basePath%>basedata/ClientController.do?command=addClient";
				$.post(url,clientJson[0],function(){
					window.parent.location.reload();
				});
			}
		</script>
	</head>
	<body onload="init()" class="body1">
		<form name="clientForm" id="clientForm">
		<input type="hidden" id="pId" name="parentClient.id" value="${id}"/>
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
							<input name="clientId" type="text" class="text1" id="clientId" size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>分销商名称:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="name" type="text" class="text1" id="name" size="40" maxlength="40">
						</td>
					</tr>
					<tr>
						<td height="15">
							<div align="right">
								<font color="#FF0000">*</font>分销商类型:&nbsp;
							</div>
						</td>
						<td align="left" >
							<select name="clientLevel.id" class="select1" id="clientLevel">
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
							<input name="bankAcctNo" type="text" class="text1" id="bankAcctNo" size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="contactTel" type="text" class="text1" id="contactTel" size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								地址:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="address" type="text" class="text1" id="address" size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								邮编:&nbsp;
							</div>
						</td>
						<td align="left" >
							<input name="zipCode" type="text" class="text1" id="zipCode" size="10" maxlength="10">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd" value="添加" onclick="addClient()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack" value="返回" onclick="history.go(-1)" />
				</div>
			</div>
		</form>
	</body>
</html>

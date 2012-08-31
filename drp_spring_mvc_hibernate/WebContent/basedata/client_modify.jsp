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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改分销商</title>
		<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
		function init(){
			var url="<%=basePath%>basedata/ClientController.do?command=getClientLevel";
			getClientLevelList(url);
		}
		
		function getClientLevelList(url){
			$.getJSON(url, function(data){
				for(index in data){
					displayClientLevel(data[index]);
				}
			});
		}
		
		function displayClientLevel(clientLevel){
			var clientLevelId='${client.clientLevel.id}';
			var $option=$("<option></option>");
			$option.html(clientLevel.name);
			$option.val(clientLevel.id);
			if(clientLevelId==clientLevel.id){
				$option.attr("selected",true);
			}
			$("#clientLevel").append($option);
		}
		
		function modifyClient(){
			
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
			var url="<%=basePath%>basedata/ClientController.do?command=modifyClient";
			$.post(url,clientJson[0],function(){
				window.parent.location.reload();
			});
		}
		
		</script>
	</head>

	<body class="body1" onload="init()">
		<form name="clientForm" target="_self" id="clientForm">
		<input type="hidden" id="id" name="id" value="${client.id }"/>
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
							<input  name="clientNo" type="text" class="text1" id="clientNo" size="10" maxlength="10" readonly="true" value="${client.clientNo }">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>分销商名称:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="name" type="text" class="text1" id="name" size="40" maxlength="40" value="${client.name }">
						</td>
					</tr>
					<tr>
						<td height="15">
							<div align="right">
								<font color="#FF0000">*</font>分销商类型:&nbsp;
							</div>
						</td>
						<td align="left">
							<select name="clientLevel.id" class="select1" id="clientLevel" >
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
							<input name="bankAcctNo" type="text" class="text1" id="bankAcctNo" size="10" maxlength="10" value="${client.bankAcctNo }">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="contactTel" type="text" class="text1" id="contactTel" size="10" maxlength="10" value="${client.contactTel }"> 
						</td>
					</tr>
					<tr >
						<td height="26">
							<div align="right">
								地址:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="address" type="text" class="text1" id="address" size="10" maxlength="10" value="${client.address }">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								邮编:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="zipCode" type="text" class="text1" id="zipCode" size="10" maxlength="10" value="${client.zipCode }">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onclick="modifyClient()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack" value="返回" onclick="history.go(-1)" />
				</div>
			</div>
		</form>
	</body>
</html>

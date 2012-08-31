<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>流向单明细信息</title>
<link rel="stylesheet" href="style/drp.css">
</head>
<body class="body1">
	<div align="center">
		<form name="flowCardForm" id="flowCardForm">
			<table width="95%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14"> &nbsp; <b>流向单详细信息</b></td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="8%" nowrap="nowrap">
						<div align="right">&nbsp;&nbsp;&nbsp;流向单号:&nbsp;</div></td>
					<td width="7%" nowrap="nowrap">${flowCard.flowCardNo }</td>
					<td width="13%" nowrap="nowrap">
						<div align="right">&nbsp;&nbsp;&nbsp;供方分销商代码:&nbsp;</div></td>
					<td width="6%">${flowCard.client.clientNo }</td>
					<td width="15%" nowrap="nowrap">
						<div align="right">
							<div align="right">&nbsp;&nbsp;&nbsp;供方分销商名称:&nbsp;</div>
						</div></td>
					<td width="18%" nowrap="nowrap">${flowCard.client.name }</td>
					<td width="10%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;录入日期:&nbsp;</td>
					<td width="23%" nowrap="nowrap">${flowCard.optDate }</td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
				<tr>
					<td width="89" class="rd6">需方客户代码</td>
					<td width="156" class="rd6">需方客户名称</td>
					<td width="74" class="rd6">物料代码</td>
					<td width="136" class="rd6">物料名称</td>
					<td width="120" class="rd6">规格</td>
					<td width="103" class="rd6">型号</td>
					<td width="68" class="rd6">计量单位</td>
					<td width="69" class="rd6">操作数量</td>
				</tr>
				<c:forEach items="${flowCardDetails}" var="fcd"> 
				<tr>
				<td class="rd8">${fcd.temiClient.clientNo }</td>
					<td class="rd8">${fcd.temiClient.name }</td>
					<td class="rd8">${fcd.item.itemNo }</td>
					<td class="rd8">${fcd.item.itemName }</td>
					<td class="rd8">${fcd.item.spec }</td>
					<td class="rd8">${fcd.item.pattern }</td>
					<td class="rd8">${fcd.item.itemUnit.name }</td>
					<td class="rd8">${fcd.optQty }</td>
					</tr>
				</c:forEach>
			</table>
			<p>
				<input name="btnClose" type="button" id="btnClose" onClick="window.close()" value="关闭">
			</p>
		</form>
	</div>
</body>
</html>

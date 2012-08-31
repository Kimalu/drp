<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="com.Kimalu.drp.domain.*" %>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>修改物料</title>
<link rel="stylesheet" href="style/drp.css">
<script src="script/client_validate.js"></script>
<script type="text/javascript">
	function modify(){
		var itemName=document.getElementById("itemName").value;
		if(itemName.length==0){
			alert("物料名称不能为空");
			document.getElementById("itemName").focus();
			return;
		}
		with(itemForm){
			method='post';
			action="<%=basePath%>basedata/ItemController?command=modify";
			submit();
		}
	}
</script>
</head>

<body class="body1">
<form name="itemForm" target="_self" id="itemForm">
<div align="center">
<table width="95%" height="21" border="0" cellpadding="2" cellspacing="2">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="25">
	<tr>
		<td width="522" class="p1" height="25" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;物料维护&gt;&gt;修改</b></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="22%" height="29">
		<div align="right">物料代码:&nbsp;</div>
		</td>
		<td width="78%" align="left"><input name="itemNo" type="text" class="text1" id="itemNo" size="10" maxlength="10" readonly="true" value="${item.itemNo }"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>物料名称:&nbsp;</div>
		</td>
		<td align="left"><input name="itemName" type="text" class="text1" id="itemName" size="20" maxlength="20" value="${item.itemName }"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">物料规格:&nbsp;</div>
		</td>
		<td align="left"><label> <input name="spec" type="text" class="text1" id="spec" size="20" maxlength="20" value="${item.spec }"> </label></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">物料型号:&nbsp;</div>
		</td>
		<td align="left"><input name="pattern" type="text" class="text1" id="pattern" size="20" maxlength="20" value="${item.pattern }"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>类别:&nbsp;</div>
		</td>
		<td align="left"><select name="category" class="select1" id="category">
		<c:forEach items="${categoryList }" var="itemCategory">
		<c:choose>
			<c:when test="${itemCategory.id eq item.itemCategory.id }">
				<c:set value="selected" var="selected"/>
			</c:when>
			<c:otherwise>
				<c:set value="" var="selected"/>
			</c:otherwise>
		</c:choose>
			<option value="${itemCategory.id }" ${selected}>${itemCategory.name}</option>
		</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>计量单位:&nbsp;</div>
		</td>
		<td align="left"><select name="unit" class="select1" id="unit">
		<c:forEach items="${unitList }" var="itemUnit">
		<c:choose>
			<c:when test="${itemUnit.id eq item.itemUnit.id }">
				<c:set value="selected" var="selected"/>
			</c:when>
			<c:otherwise>
				<c:set value="" var="selected"/>
			</c:otherwise>
		</c:choose>
			<option value="${itemUnit.id }" ${selected}>${itemUnit.name}</option>
		</c:forEach>
		</select></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<div align="center"><input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onclick="modify()"> &nbsp;&nbsp;&nbsp;&nbsp; <input name="btnModify" class="button1" type="button"
	id="btnModify" value="返回" onClick="history.go(-1)"></div>
</div>
</form>
</body>
</html>

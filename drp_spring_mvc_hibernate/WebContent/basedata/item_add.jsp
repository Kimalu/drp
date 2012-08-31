<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>添加物料</title>
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<link rel="stylesheet" href="style/drp.css">
<script type="text/javascript">
	var jsonValue;
	function onload(){
		var url="<%=basePath %>basedata/ItemController.do?command=getItemCatagory&random="+Math.random();
		ajax("category",url);
		url="<%=basePath %>basedata/ItemController.do?command=getItemUnit&random="+Math.random();
		ajax("unit",url);
	}
	function ajax(type,url){
		$.getJSON(url, function(data){
			displaySelector(type,data);
			});
	}
	/*+++++++++++++++++++++display-start++++++++++++++*/
	function displaySelector(type,data){
		var queryElementId="#"+type;
		$.each(data,function(i,item){
			display($(queryElementId),item);
		});
	}
	function display($selector,item){
		var $option=$("<option>"+item.name+"</option>");
		$option.val(item.id);
		$selector.append($option);
	}
	/*--------------------display-end------------------------*/
</script>	
</head>

<body onload="onload();" class="body1">
<form name="itemForm" target="_self" id="itemForm" action="basedata/ItemController.do?command=addItem"  method="post" /*onsubmit="return validateItem()"*/>
<input type="hidden" name="command" value="add"></input>
<div align="center">
<table width="95%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="25">
	<tr>
		<td width="522" class="p1" height="25" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;物料维护&gt;&gt;添加</b></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="22%" height="29">
		<div align="right"><font color="#FF0000">*</font>物料代码:&nbsp;</div>
		</td>
		<td width="78%"><input name="itemNo" type="text" class="text1" id="itemNo" size="10" maxlength="10"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>物料名称:&nbsp;</div>
		</td>
		<td><input name="itemName" type="text" class="text1" id="itemName" size="20" maxlength="20"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">物料规格:&nbsp;</div>
		</td>
		<td><label> <input name="spec" type="text" class="text1" id="spec" size="20" maxlength="20"> </label></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">物料型号:&nbsp;</div>
		</td>
		<td><input name="pattern" type="text" class="text1" id="pattern" size="20" maxlength="20"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>类别:&nbsp;</div>
		</td>
		<td><select name="category" class="select1" id="category">
		</select></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>计量单位:&nbsp;</div>
		</td>
		<td><select name="unit" class="select1" id="unit">
		</select></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<div align="center">
	<input name="btnAdd" class="button1" type="submit" id="btnAdd" value="添加"> &nbsp;&nbsp;&nbsp;&nbsp; 
	<input name="btnBack" class="button1" type="button" id="btnBack" value="返回" onClick="history.go(-1)">
</div>
</div>
</form>
</body>
</html>

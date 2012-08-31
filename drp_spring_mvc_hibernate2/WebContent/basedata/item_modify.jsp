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
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<script src="script/client_validate.js"></script>
<script type="text/javascript">
	/*
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
	*/
</script>
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
		if(item.id =="${item.itemCategory.id}"){
				$option.attr("selected",true);
		}else if(item.id =="${item.itemUnit.id}"){
			   $option.attr("selected",true);
		}
		$selector.append($option);
	}
	/*--------------------display-end------------------------*/
	
	function modify(){
		validate();
		var url="<%=basePath %>basedata/ItemController.do?command=doModify&random="+Math.random();
		$form=$("#itemForm");
		$form.attr("action",url);
		$form.attr("method","post");
		$form.submit();
	}
	function validate(){
		alert("validated");
	}
</script>	
</head>

<body class="body1" onload="onload()">
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
	<input type="hidden" name="id" value="${item.id}"/>
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
		<td align="left">
		<select name="categoryId" class="select1" id="category">
		</select>
		</td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>计量单位:&nbsp;</div>
		</td>
		<td align="left">
		<select name="unitId" class="select1" id="unit">
		</select>
		</td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<div align="center"><input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onclick="modify()"> &nbsp;&nbsp;&nbsp;&nbsp; <input name="btnModify" class="button1" type="button"
	id="btnModify" value="返回" onClick="history.go(-1)"></div>
</div>
</form>
</body>
</html>

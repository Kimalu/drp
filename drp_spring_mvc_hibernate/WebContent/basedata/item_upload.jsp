<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.Kimalu.drp.domain.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>物料维护</title>
<link rel="stylesheet" href="style/drp.css">
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<script src="script/client_validate.js"></script>
<script type="text/javascript" src="script/javascriptx.js"></script>
<script language="javascript">

	function upload(){
		var fileDom=document.getElementById("file");
		if(fileDom.value==null || fileDom.value.trim().length==0){
			alert("请选择要上传的图片！");
			fileDom.focus();
			return false;
		}
	}
	
</script>
</head>

<body class="body1">
<form onsubmit="return upload()" name="itemForm" target="_self" id="itemForm" enctype="multipart/form-data" method="post" action="<%=basePath%>basedata/ItemController.do?command=doUploadPic4Item">
<input type="hidden" id="id" name="id" value="${item.id}"/>
<div align="center">
<table width="95%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
	<tr>
		<td width="522" class="p1" height="2" nowrap>
			<img src="images/mark_arrow_03.gif" width="14" height="14"> &nbsp; 
			<b>基础数据管理&gt;&gt;物料维护&gt;&gt;上传物料图片</b>
		</td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="29">
		<div align="right">物料代码:&nbsp;</div>
		</td>
		<td>${item.itemNo}</td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">物料名称:&nbsp;</div>
		</td>
		<td>${item.itemName}</td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">物料规格:&nbsp;</div>
		</td>
		<td>${item.spec}</td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">物料型号:&nbsp;</div>
		</td>
		<td>${item.pattern}</td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">类别:&nbsp;</div>
		</td>
		<td>${item.itemCategory.name}</td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">计量单位:&nbsp;</div>
		</td>
		<td>${item.itemUnit.name}</td>
	</tr>
	<tr>
		<td height="74">
			<div align="right">图片:&nbsp;</div>
		</td>
		<c:choose>
			<c:when  test="${empty item.uploadFileName}">
				<c:set var="imgName" value="noImg"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="imgName" value="${item.itemNo}"></c:set>
			</c:otherwise>
		</c:choose>
		<td>
			<img src="upload/${imgName}.gif" width="85" height="49">
		</td>
	</tr>
	<tr>
		<td width="22%" height="29">
		<div align="right"><font color="#FF0000">*</font>选择图片:&nbsp;</div>
		</td>
		<td width="78%"><input id="file" name="file" type="file" class="text1" size="40" maxlength="40"></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<div align="center">
	<input name="btn_upload" class="button1" type="submit" id="btn_upload" value="上传"/> 
	&nbsp;&nbsp;&nbsp;&nbsp; 
	<input name="btnBack" class="button1" type="button" id="btnBack" value="返回" onClick="history.go(-1)"/>
</div>
</div>
</form>
</body>
</html>

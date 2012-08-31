<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<title>修改会计核算期间</title>
		<link rel="stylesheet" href="style/drp.css">
		<link rel="stylesheet" type=text/css href="style/drp.css">
		<link rel="stylesheet" type=text/css href="style/base/jquery.ui.all.css">
		<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
		<script src="script/ui/jquery.ui.core.js"></script> 
		<script src="script/ui/jquery.ui.widget.js"></script> 
		<script src="script/ui/jquery.ui.datepicker.js"></script>
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
			$(function() {
				$( "#beginDate" ).datepicker();
				$( "#endDate" ).datepicker();
			});
			function modifyFYP(){
				with(fiscalYearPeriodForm){
					action="<%=basePath%>basedata/FypController.do?command=doModify";
					method="post";
					submit();
				}
			}
			
		</script>
	</head>

	<body class="body1">
		<form name="fiscalYearPeriodForm" target="_self" id="fiscalYearPeriodForm">
		<input type="hidden" id="id" name="id" value="${fyp.id}">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;会计核算期间维护&gt;&gt;修改</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								核算年:&nbsp;
							</div>
						</td>
						<td width="78%" align="left">
							<input name="fiscalYear" type="text" class="text1" value="${fyp.fiscalYear }"
								id="fiscalYear" size="10" maxlength="10" readonly="true">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								核算月:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="fiscalMonth" type="text" class="text1" value="${fyp.fiscalMonth }"
								id="fiscalMonth" size="10" maxlength="10" readonly="true">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>开始日期:&nbsp;
							</div>
						</td>
						<td align="left">
							<label>
								<input type="text" name="beginDate" id="beginDate" value="${fn:substring(fyp.beginDate,0,10 )}" size="10" maxlength="10">
							</label>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>结束日期:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="endDate" type="text" id="endDate" value="${fn:substring(fyp.endDate,0,10 )}" size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>是否可用:&nbsp;
							</div>
						</td>
						<td align="left">
						<c:choose>
							<c:when test="${fyp.periodStatus eq \"n\"}">
								<c:set var="select" value=""/>
							</c:when>
							<c:otherwise>
								<c:set var="select" value="checked"/>
							</c:otherwise>
						</c:choose>
							<input name="periodStatus" type="checkbox" class="checkbox1"
								id="periodStatus" value="checkbox" ${select}>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyFYP()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack" value="返回" onClick="history.go(-1)">
				</div>
			</div>
		</form>
	</body>
</html>

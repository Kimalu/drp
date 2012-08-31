<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会计核算期间维护</title>
<link rel="stylesheet" href="<%= request.getContextPath()%>/style/drp.css">
<script type="text/javascript">
			var yearOrder="asc";
			function add(){
				window.self.location = "fiscal_year_period_add.jsp";
				//window.self.loaction = "fiscal_year_period_add.jsp";
			}
			var xmlHttpRequest;
			
			function createXMLHttpRequest() {
				if(window.XMLHttpRequest) {
					xmlHttpRequest = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			function init(){
				var fypList;
				createXMLHttpRequest();
				var url="<%=request.getContextPath()%>/basedata/FiscalYearPeriodController?command=query";
		xmlHttpRequest.open("GET", url, true);
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4) { //Ajax引擎初始化成功
				if (xmlHttpRequest.status == 200) { //http协议成功
					fypList = eval(xmlHttpRequest.responseText);
					for ( var i = 0; i < fypList.length; i++) {
						display(fypList[i]);
					}
					//document.getElementById("userIdSpan").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>";
				} else {
					alert("请求失败，错误码=" + xmlHttpRequest.status);
				}
			}
		}
		xmlHttpRequest.send(null);
	}
	function display(fyp) {
		var fypTable = document.getElementById("fypTable");
		var tr = fypTable.insertRow();
		var td1 = tr.insertCell();
		td1.className = "rd8";
		td1.abbr = "radio";
		td1.innerHTML = "<input name='selectFlag' value='radiobutton' id='selectFlag' type='radio'/>";
		var td2 = tr.insertCell();
		td2.className = "rd8";
		td2.abbr = "year";
		td2.innerHTML = fyp.fiscalYear;
		var td3 = tr.insertCell();
		td3.className = "rd8";
		td3.abbr = "month";
		td3.innerHTML = fyp.fiscalmonth;
		var td4 = tr.insertCell();
		td4.className = "rd8";
		td4.abbr = "beginDate";
		var begDate = new Date(parseInt(fyp.beginDate.time));
		td4.innerHTML = begDate.toLocaleDateString();

		var td5 = tr.insertCell();
		td5.className = "rd8";
		td5.abbr = "endDate";
		var eDate = new Date(parseInt(fyp.endDate.time));
		td5.innerHTML = eDate.toLocaleDateString();

		var td6 = tr.insertCell();
		td6.className = "rd8";
		td6.abbr = "periodStatus";
		if (fyp.periodStatus == 'n') {
			td6.innerHTML = "不可用";
		} else if (fyp.periodStatus == 'y') {
			td6.innerHTML = "可用";
		}
	}
	function sort(obj) {
		if (obj.innerHTML == "核算年") {
			popsrot("year");
		}
	}

	function popsrot(file) {
		var trList = document.getElementById("fypTable").getElementsByTagName("tr");
		for ( var i = 1; i < trList.length; i++) {
			for ( var j = 1; j < trList.length - i; j++) {
				var td1Value = (trList[j].getElementsByTagName("td"))[1].innerHTML;
				var td2Value = (trList[j + 1].getElementsByTagName("td"))[1].innerHTML;
				parseInt(td1Value);
				parseInt(td2Value);
				if(yearOrder=="asc"){
					if (parseInt(td1Value) < parseInt(td2Value)) {
						exchange(trList[j], trList[j + 1]);
					}
				}else{
					if (parseInt(td1Value) > parseInt(td2Value)) {
						exchange(trList[j], trList[j + 1]);
					}
				}
			}
		}
		if(yearOrder=="asc"){
			yearOrder="desc";
		}else{
			yearOrder="asc";
		}
	}
	function exchange(obj1, obj2) {
		var parent = obj1.parentNode;
			parent.insertBefore(obj2, obj1);
	}
</script>
</head>

<body class="body1" onload="init()">
<form name="fiscalYearPeriodForm">
<div align="center">
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="35">
	<tr>
		<td class="p1" height="18" nowrap>&nbsp;</td>
	</tr>
	<tr>
		<td width="522" class="p1" height="17" nowrap><img src="../images/mark_arrow_02.gif" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;会计核算期间维护</b></td>
	</tr>
</table>
<hr width="100%" align="center" size=0>
</div>
<table width="95%" height="20" border="0" align="center" cellspacing="0" class="rd1" id="toolbar">
	<tr>
		<td width="49%" class="rd19"><font color="#FFFFFF">查询列表</font></td>
		<td width="27%" nowrap class="rd16">
		<div align="right"></div>
		</td>
	</tr>
</table>
<table id="fypTable" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
	<tr>
		<td width="79" class="rd6">选择</td>
		<td width="123" class="rd6" onclick="sort(this)">核算年</td>
		<td width="146" class="rd6" onclick="sort(this)">核算月</td>
		<td width="188" class="rd6" onclick="sort(this)">开始日期</td>
		<td width="204" class="rd6" onclick="sort(this)">结束日期</td>
		<td width="172" class="rd6" onclick="sort(this)">核算期状态</td>
	</tr>
	<!--<tr>
		<td class="rd8"><input type="radio" name="selectFlag" value="radiobutton"></td>
		<td class="rd8">2007</td>
		<td class="rd8">06</td>
		<td class="rd8">2007-06-01</td>
		<td class="rd8">2007-06-30</td>
		<td class="rd8">可用</td>
	</tr>
	<tr>
		<td class="rd8">&nbsp;</td>
		<td class="rd8">&nbsp;</td>
		<td class="rd8">&nbsp;</td>
		<td class="rd8">&nbsp;</td>
		<td class="rd8">&nbsp;</td>
		<td class="rd8">&nbsp;</td>
	</tr>
	<tr>
		<td width="79" class="rd7">&nbsp;</td>
		<td width="123" class="rd7">&nbsp;</td>
		<td width="146" class="rd7">&nbsp;</td>
		<td width="188" class="rd7">&nbsp;</td>
		<td width="204" class="rd7">&nbsp;</td>
		<td width="172" class="rd7">&nbsp;</td>
	</tr>
-->
</table>
<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2">
		<div align="left"><font color="#FFFFFF">&nbsp;共&nbspxx&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">当前第</font>&nbsp <font color="#FF0000">x</font>&nbsp <font
			color="#FFFFFF">页</font></div>
		</td>
		<td nowrap class="rd19">
		<div align="right"><input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页"> <input name="btnPreviousPage" class="button1" type="button"
			id="btnPreviousPage" value=" &lt;  " title="上页"> <input name="btnNextPage" class="button1" type="button" id="btnNextPage" value="  &gt; " title="下页"> <input name="btnBottomPage"
			class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页"> <input name="btnAdd" type="button" class="button1" id="btnAdd" value="添加" onClick="add()"> <input
			name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="self.location='fiscal_year_period_modify.html'"></div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
</form>
</body>
</html>

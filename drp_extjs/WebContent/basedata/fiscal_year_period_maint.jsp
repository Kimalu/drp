<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会计核算期间维护</title>
<link rel="stylesheet" href="style/drp.css">
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>

<script type="text/javascript">
	var jsonValue;
/*+++++++++++++++++++++other-start++++++++++++++++++++++++++++++*/
	function init(){
		read();
	}
	
	function ajax(url){
		$.getJSON(url, function(data){
			jsonValue=data;
			displayFYPPage();
			});
	}
	function getSelectedElement(){
		return $("#fypTable input:checked")[0];
	}
	function checkSelected(){
		var selectCount=$("#fypTable input:checked").length;
		if(selectCount<1){
			alert("至少选中一条记录");
			return false;
		}
		return true;
	}
/*---------------------other-end--------------------------------*/

/*+++++++++++++++++++++display-start++++++++++++++++++++++++++++++*/ 

	function clearTabel(){
		$("#fypTable tr:gt(0)").remove();
	}
	function display(fyp) {
		var $fypTable = $("#fypTable");
		var $tr =$("<tr></tr>");
		
		var $td1 =$("<td></td>");
		$td1.addClass("rd8");
		$td1.html("<input name='selectFlag' value="+fyp.id+" id='selectFlag' type='radio'/>");
		
		var $td2 =$("<td></td>");
		$td2.addClass("rd8");
		$td2.html(fyp.fiscalYear);
		
		var $td3 =$("<td></td>");
		$td3.addClass("rd8");
		$td3.html(fyp.fiscalMonth);
		
		var $td4 = $("<td></td>");
		$td4.addClass("rd8");
		var begDate = new Date(parseInt(fyp.beginDate.time));
		$td4.html(begDate.toLocaleDateString());

		var $td5 =$("<td></td>");
		$td5.addClass("rd8");
		var eDate = new Date(parseInt(fyp.endDate.time));
		$td5.html(eDate.toLocaleDateString());

		var $td6 =$("<td></td>");
		$td6.addClass("rd8");
		if (fyp.periodStatus == 'n') {
			$td6.html("不可用");
		} else if (fyp.periodStatus == 'y') {
			$td6.html("可用");
		}
		$tr.append($td1);
		$tr.append($td2);
		$tr.append($td3);
		$tr.append($td4);
		$tr.append($td5);
		$tr.append($td6);
		$fypTable.append($tr);
	}
	function displayFYPPage(){
 		clearTabel();
 		$.each(jsonValue.list,function(i,fyp){
			display(fyp);
		});
		$("#pageNo").html(jsonValue.pageNo);
		$("#totalPageNum").html(jsonValue.totalPageNum);
	}
	
/*---------------------display-end--------------------------------*/

/*++++++++++++++++++++CRUD-start+++++++++++++++++++++++++++++++++*/
	function add(){
		window.self.location = "fiscal_year_period_add.jsp";
	}
	function read(){
		var url="<%=basePath%>basedata/FypController.do?command=getFYPList";
		ajax(url);
	}
	function modifyFYP(){
		var id;
		if(checkSelected()){
			id=getSelectedElement().value;
			window.self.location= "<%=basePath%>basedata/FypController.do?command=showModify&id="+id;
		}
	}
/*---------------------CRUD-end--------------------------------*/
 
/*+++++++++++++++++++++page-start+++++++++++++++++++++++++++++++++*/ 
  	
	function topPage() {
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			var url="<%=basePath%>basedata/FypController.do?command=getFYPList&pageNo=1&random="+Math.random();
			ajax(url);
		}
	}
	function previousPage() {
		var pn;
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			pn=parseInt(pageNo)-1;
			var url="<%=basePath%>basedata/FypController.do?command=getFYPList&pageNo="+pn+"&random="+Math.random();
			ajax(url);
		}
	}

	function nextPage() {
		var pn;
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			pn=parseInt(pageNo)+1;
			var url="<%=basePath%>basedata/FypController.do?command=getFYPList&pageNo="+pn+"&random="+Math.random();
			ajax(url);
		}
	}

	function bottomPage() {
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			var url="<%=basePath%>basedata/FypController.do?command=getFYPList&pageNo="+totalPageNum+"&random="+Math.random();
			ajax(url);
		}
	}


/*---------------------page-end-----------------------------------*/
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
		<td width="522" class="p1" height="17" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;会计核算期间维护</b></td>
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
		<td width="123" class="rd6" >核算年</td>
		<td width="146" class="rd6" >核算月</td>
		<td width="188" class="rd6" >开始日期</td>
		<td width="204" class="rd6" >结束日期</td>
		<td width="172" class="rd6" >核算期状态</td>
	</tr>
</table>
<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2">
		<div align="left"><font color="#FFFFFF">&nbsp;共<span id="totalPageNum"></span>页</font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">当前第</font>&nbsp <font color="#FF0000"><span
			id="pageNo"></span></font>&nbsp <font color="#FFFFFF">页</font></div>
		</td>
		<td nowrap class="rd19">
			<div align="right">
				<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页" onclick="topPage()">
				<input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage" value=" &lt;  " title="上页" onclick="previousPage()">
				<input name="btnNextPage" class="button1" type="button" id="btnNextPage" value="  &gt; " title="下页" onclick="nextPage()">
				<input name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onclick="bottomPage()">
				<input name="btnAdd" type="button" class="button1" id="btnAdd" value="添加" onClick="add()">
				<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyFYP()">
			</div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
</form>
</body>
</html>

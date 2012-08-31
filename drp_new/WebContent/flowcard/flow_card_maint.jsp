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
<title>流向单维护</title>
<link rel="stylesheet" href="style/drp.css" type=text/css>
<script src="script/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">
		function init(){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getFlowCardList";
			ajaxQuery(url);
		}
		function ajaxQuery(url){
			$.getJSON(url,function(data){
				clearTable();
				$.each(data.list,function(i){
					displayFlowCardPage(this);
				});
				setPageInfo(data);
			})
		}
		function showFlowCardDetail(fcid){
			window.open('<%=basePath%>flowcard/FlowCardController.do?command=showFlowCardDetail&fcid='+fcid, '流向单详细信息', 'width=800, height=400, scrollbars=no');
			return false;
		}

		function displayFlowCardPage(flowCard){
			var $table=$("#flowCardTable");
		 	var $tr=$("<tr></tr>");
		 	var $td1=$("<td></td>");
		 	$td1.addClass("rd8");
		 	$td1.attr("width",37);
		 	$td1.html("<input name='selectFlag' type='checkbox' class='checkbox1' id='selectFlag' value='"+flowCard.fcId+"'>");
		 	var $td2=$("<td></td>");
		 	$td2.addClass("rd8");
		 	$td2.attr("width",88);
		 	$td2.html("<a href='#' onClick=\"return showFlowCardDetail('"+flowCard.fcId+"');\">"+flowCard.flowCardNo+"</a>");
		 	var $td3=$("<td></td>");
		 	$td3.addClass("rd8");
		 	$td3.attr("width",158);
		 	$td3.html(flowCard.client.clientNo);
		 	var $td4=$("<td></td>");
		 	$td4.addClass("rd8");
		 	$td4.attr("width",211);
		 	$td4.html(flowCard.client.name);
		 	var $td5=$("<td></td>");
		 	$td5.addClass("rd8");
		 	$td5.attr("width",100);
		 	$td5.html(flowCard.recorder.userName);
		 	var $td6=$("<td></td>");
		 	$td6.addClass("rd8");
		 	$td6.attr("width",195);
		 	$td6.html(flowCard.optDate);
		 	var $td7=$("<td></td>");
		 	$td7.addClass("rd8");
		 	$td7.attr("width",100);
		 	$td7.append("<input type='hidden' id='status' name='status' value='"+flowCard.vouSts+"'/>");
		 	var status;
		 	if(flowCard.vouSts=='N'){
		 		status="未送审";
		 	}else if(flowCard.vouSts=='S'){
		 		status="审核中";
		 	}
		 	$td7.append(status);
		 	$tr.append($td1);
		 	$tr.append($td2);
		 	$tr.append($td3);
		 	$tr.append($td4);
		 	$tr.append($td5);
		 	$tr.append($td6);
		 	$tr.append($td7);
		 	$table.append($tr);
		}
		
		function setPageInfo(data){
			$("#totalPageNum").html(data.totalPageNum);
			$("#pageNo").html(data.pageNo);
		}
		
		function clearTable(){
			$("#flowCardTable tr:gt(0)").remove();
		}
		
		
		function checkSelectOnlyOne(){
			var selectCount=$("#flowCardTable tr:gt(0) input:checked").length;
			if(selectCount>1){
				alert("只能选中一条记录");
				return false;
			}else if(selectCount<1){
				alert("必须选中一条记录");
				return false;
			}
			return true;
		}	
		
		
		
	function addFlowCard() {
		window.self.location = "<%=basePath%>flowcard/flow_card_add.jsp";
	}

	function modifyFlowCard() {
		
		if(!checkSelectOnlyOne()){
			return;
		}
		if(isChecking()){
			alert("审核中，不能修改");
			return;
		}
		var fcid=$("input:checked").val();
		window.self.location = "<%=basePath%>flowcard/FlowCardController.do?command=showFlowCardModifyPage&fcid="+fcid;
	}
	function isChecking() {
		var status=$("tr:has(input:checked) [name=status]").val();
		if(status=='N'){
			return false;
		}else{
			return true;
		}
		
		var fcid=$("input:checked").val();
		window.self.location = "<%=basePath%>flowcard/FlowCardController.do?command=showFlowCardModifyPage&fcid="+fcid;
	}

	function deleteFlowCard() {
	}

	function topPage() {
		window.self.location = "flow_card_maint.html";
	}

	function previousPage() {
		window.self.location = "flow_card_maint.html";
	}

	function nextPage() {

		window.self.location = "flow_card_maint.html";
	}

	function bottomPage() {
		window.self.location = "flow_card_maint.html";
	}

	function queryFlowCard() {
	}

	function resetFlowCard() {

	}

	function auditFlowCard() {
		if(!checkSelectOnlyOne()){
			return;
		}
		if(isChecking()){
			alert("该流向单已送审");
			return;
		}
		var fcid=$("input:checked").val();
		var url="<%=basePath%>flowcard/FlowCardController.do?command=submitToCheck&fcid="+fcid;
		window.self.location=url;
	}
</script>
</head>

<body class="body1" onload="init()">
<form name="flowCardForm">
<div align="center">
<table width="95%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
	<tr>
		<td width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14"> &nbsp; <b>分销商库存管理&gt;&gt;流向单维护</b></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15%" height="29">
		<div align="right">供方分销商代码:&nbsp;</div>
		</td>
		<td width="23%"><input name="clientId" type="text" class="text1" id="clientId" size="10" maxlength="10" readonly="true"> <input name="btnSelectClient" type="button" id="btnSelectClient"
			value="..." class="button1" onClick="window.open('<%=basePath%>flowcard/client_select.jsp', '选择分销商', 'width=700, height=400, scrollbars=no')"></td>
		<td width="14%">
		<div align="right">供方分销商名称:&nbsp;</div>
		</td>
		<td width="37%"><input name="clientName" type="text" class="text1" id="clientName" size="40" maxlength="40" readonly="true"></td>
		<td width="11%"><input name="btnQuery" type="button" class="button1" id="btnQuery" value="查询" onClick="queryFlowCard()"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right">开始日期:&nbsp;</div>
		</td>
		<td><input type="text" name="beginDate" size="10" maxlength="10" value="2001-01-01" readonly="true" onClick=JSCalendar(this)></td>
		<td>
		<div align="right">结束日期:&nbsp;</div>
		</td>
		<td><input type="text" name="endDate" size="10" maxlength="10" value="2001-01-01" readonly="true" onClick=JSCalendar(this)></td>
		<td><input name="btnReset" type="button" class="button1" id="btnReset" value="重置" onClick="resetFlowCard()"></td>
	</tr>
</table>
<table height=8 width="95%">
	<tr>
		<td height=3></td>
	</tr>
</table>
<table width="95%" height="20" border="0" cellspacing="0" id="toolbar" class="rd1">
	<tr>
		<td class="rd19" width="434"><font color="#FFFFFF">查询列表</font></td>
		<td nowrap class="rd16" width="489">
		<div align="right"></div>
		</td>
	</tr>
</table>
<table id="flowCardTable" name="flowCardTable" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1" title="点击选中的数据查看详细信息...">
	<tr>
		<td width="37" class="rd6"><input type="checkbox" name="ifAll" onClick="checkAll()"></td>
		<td width="88" class="rd6">流向单号</td>
		<td width="158" class="rd6">供方分销商代码</td>
		<td width="211" class="rd6">供方分销商名称</td>
		<td width="100" class="rd6">录入人</td>
		<td width="195" class="rd6">录入日期</td>
		<td width="100" class="rd6">状态</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="30" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2" width="36%">
		<div align="left"><font color="#FFFFFF">&nbsp;共<span id="totalPageNum"></span>页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">当前第</font>&nbsp<font color="#FF0000" id="pageNo"></font>&nbsp <font
			color="#FFFFFF">页</font></div>
		</td>
		<td nowrap class="rd19" width="64%">
		<div align="right">
		<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页" onClick="topPage()"> 
		<input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage" value=" &lt;  " title="上页" onClick="previousPage()"> 
		<input name="btnNextPage" class="button1" type="button" id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()"> 
		<input name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onClick="bottomPage()"> 
		<input name="btnAdd" type="button" class="button1" id="btnAdd" value="添加" onClick="addFlowCard()"> 
		<input name="btnDelete" class="button1" type="button" id="btnDelete" value="删除" onClick="deleteFlowCard()"> 
		<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyFlowCard()"> 
		<input name="btnAudit" class="button1" type="button" id="btnAudit" value="送审" onClick="auditFlowCard()"></div>
		</td>
	</tr>
</table>
</div>
</form>
</body>
</html>

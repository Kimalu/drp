<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请选择分销商</title>
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<link rel="stylesheet" href="style/drp.css">
<script type="text/javascript">

	function init(){
		var url="<%=basePath%>flowcard/FlowCardController.do?command=getClientList";
		ajaxQuery(url);
	}
	function ajaxQuery(url){
		$.getJSON(url,function(data){
			/*jquery的遍历方式
			$.each(data.list,function(i){
				alert(this.id);
			});
			*/
			/*javascript的遍历方式
			var clientList=data.list;
			for(index in clientList){
				alert(clientList[index].id);
			}
			*/
			clearTable();
			$.each(data.list,function(i){
				displayClient(this);
			});
			setPageInfo(data);
		})
	}
	function setPageInfo(data){
		$("#totalPageNum").html(data.totalPageNum);
		$("#pageNo").html(data.pageNo);
	}
	
	function displayClient(client){
		var $tr=$("<tr></tr>");
		var $td1=$("<td></td>");
		var $radio=$("<input type='radio' name='selectFlag' value='"+client.id+"' onDblClick='selectOk()'>");
		$td1.addClass("rd8");
		$td1.append($radio);
		var $td2=$("<td></td>");
		$td2.addClass("rd8");
		$td2.html(client.clientNo);
		var $td3=$("<td></td>");
		$td3.addClass("rd8");
		$td3.html(client.name);
		var $td4=$("<td></td>");
		$td4.addClass("rd8");
		$td4.html(client.clientLevel.name);
		$tr.append($td1);
		$tr.append($td2);
		$tr.append($td3);
		$tr.append($td4);
		$("#clientTable").append($tr);
	}
	function clearTable(){
		$("#clientTable tr:gt(0)").remove();
	}

	function queryClientByName() {
		var queryByClientName=$.trim($("#queryByClientName").val());
		var url="<%=basePath%>flowcard/FlowCardController.do?command=getClientList&queryByClientName="+queryByClientName;
		ajaxQuery(url);
	}

	function selectOk() {
		if($("input:checked").length!=1){
			alert("请选择一个分销商");
			return;
		}
		var id=$("tr input:checked").val();
		var clientId=$("tr:has(:radio:checked) td:eq(1)").html();
		var clientName=$("tr:has(:radio:checked) td:eq(2)").html();
		window.opener.$("#cid").val(id);
		window.opener.$("#clientId").val(clientId);
		window.opener.$("#clientName").val(clientName);
		//window.parent.location="<%=basePath%>flowcard/FlowCardController.do?command=clientSelected&id="+id;
		//window.opener.location="<%=basePath%>flowcard/FlowCardController.do?command=clientSelected&id="+id;
	//	$(window.opener.document).filter("#clientId").val()
		window.close();
	}
/*+++++++++++++++++++++page-start+++++++++++++++++++++++++++++++++*/ 
  	
	function topPage() {
		var queryByClientName=$.trim($("#queryByClientName").val());
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getClientList&pageNo=1&random="+Math.random();
			if(queryByClientName!=null && queryByClientName!=""){
				url+="&queryByClientName="+queryByClientName;
			}
			ajaxQuery(url);
		}
	}
	function previousPage() {
		var queryByClientName=$.trim($("#queryByClientName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			pn=parseInt(pageNo)-1;
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getClientList&pageNo="+pn+"&random="+Math.random();
			if(queryByClientName!=null && queryByClientName!=""){
				url+="&queryByClientName="+queryByClientName;
			}
			ajaxQuery(url);
		}
	}

	function nextPage() {
		var queryByClientName=$.trim($("#queryByClientName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			pn=parseInt(pageNo)+1;
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getClientList&pageNo="+pn+"&random="+Math.random();
			if(queryByClientName!=null && queryByClientName!=""){
				url+="&queryByClientName="+queryByClientName;
			}
			ajaxQuery(url);
		}
	}

	function bottomPage() {
		var queryByClientName=$.trim($("#queryByClientName").val());
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getClientList&pageNo="+totalPageNum+"&random="+Math.random();
			if(queryByClientName!=null && queryByClientName!=""){
				url+="&queryByClientName="+queryByClientName;
			}
			ajaxQuery(url);
		}
	}


/*---------------------page-end-----------------------------------*/
</script>
</head>
<body class="body1" onload="init()">
<form name="clientForm">
<div align="center">
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="34">
	<tr>
		<td width="522" class="p1" height="34" nowrap><img src="images/search.gif" width="32" height="32"/> &nbsp; <b>请选择分销商</b></td>
	</tr>
</table>
<hr width="100%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="17%" height="29">
		<div align="left">分销商代码/名称:</div>
		</td>
		<td width="57%"><input name="queryByClientName" type="text" class="text1" id="queryByClientName" size="50" maxlength="50"></td>
		<td width="26%">
			<div align="left"><input name="btnQuery" type="button" class="button1" id="btnQuery" value="查询" onClick="queryClientByName()"></div>
		</td>
	</tr>
	<tr>
		<td height="16">
		<div align="right"></div>
		</td>
		<td>&nbsp;</td>
		<td>
		<div align="right"></div>
		</td>
	</tr>
</table>

</div>
<table width="95%" border="0" cellspacing="0" cellpadding="0" class="rd1" align="center">
	<tr>
		<td nowrap height="10" class="p2">分销商信息</td>
		<td nowrap height="10" class="p3">&nbsp;</td>
	</tr>
</table>
<table id="clientTable" name="clienttable" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
	<tr>
		<td class="rd6">选择</td>
		<td class="rd6">分销商代码</td>
		<td class="rd6">分销商名称</td>
		<td class="rd6">分销商类型</td>
	</tr>
</table>
<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2" width="36%">
		<div align="left">
			<font color="#FFFFFF">&nbsp;共<span id="totalPageNum"></span>页</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<font color="#FFFFFF">当前第</font>
			&nbsp
			<font color="#FF0000" id="pageNo"></font>
			&nbsp 
			<font color="#FFFFFF">页</font>
		</div>
		</td>
		<td nowrap class="rd19" width="64%">
		<div align="right">
			<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页" onClick="topPage()"> 
			<input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage" value=" &lt;  " title="上页" onClick="previousPage()"> 
			<input name="btnNextPage" class="button1" type="button" id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()"> 
			<input name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onClick="bottomPage()"> 
			<input name="btnOk" class="button1" type="button" id="btnOk" value="确定" onClick="selectOk()"> 
			<input name="btnClose" class="button1" type="button" id="btnClose" value="关闭" onClick="window.close()">
		</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>

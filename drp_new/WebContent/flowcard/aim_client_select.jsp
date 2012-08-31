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
<title>请选择需方客户</title>
<link rel="stylesheet" href="style/drp.css">
<script src="script/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">
	var index=${index};
	function init(){
		var url="<%=basePath%>flowcard/FlowCardController.do?command=getAimClientList";
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
				displayAimClientPage(this);
			});
			setPageInfo(data);
		})
	}

	function displayAimClientPage(aimClient){
		var clientType;
		if(aimClient.clientLevel==null){
			clientType=aimClient.temiClientType.name;
		}else if(aimClient.temiClientType==null){
			clientType=aimClient.clientLevel.name;
		}
	
		var $table=$("#aimClientTable");
	 	var $tr=$("<tr></tr>");
	 	var $td1=$("<td></td>");
	 	$td1.addClass("rd8");
	 	$td1.html("<input type='radio' name='selectFlag' value='"+aimClient.id+"' onDblClick='selectOk()'/>");
	 	var $td2=$("<td></td>");
	 	$td2.addClass("rd8");
	 	$td2.html(aimClient.clientNo);
	 	var $td3=$("<td></td>");
	 	$td3.addClass("rd8");
	 	$td3.html(aimClient.name);
	 	var $td4=$("<td></td>");
	 	$td4.addClass("rd8");
	 	$td4.html(clientType);
	 	$tr.append("<input type='hidden' id='aimClientCategoryId' name='aimClientCategoryId' value='"+aimClient.categoryId+"'/>");
	 	//var $td5=$("<td></td>");
	 	//$td5.addClass("rd8");
	 	//$td5.html("<input type='hidden' value='"+aimClient.categoryId+"'/>");
	 	$tr.append($td1);
	 	$tr.append($td2);
	 	$tr.append($td3);
	 	$tr.append($td4);
	 	//$tr.append($td5);
	 	$table.append($tr);
	}
	
	function setPageInfo(data){
		$("#totalPageNum").html(data.totalPageNum);
		$("#pageNo").html(data.pageNo);
	}
	
	function clearTable(){
		$("#aimClientTable tr:gt(0)").remove();
	}
	
	function queryClient() {
		var queryByAimClientName=$.trim($("#queryByAimClientName").val());
		var url="<%=basePath%>flowcard/FlowCardController.do?command=getAimClientList&random="+Math.random();
		if(queryByAimClientName!=null && queryByAimClientName!=""){
			url+="&queryByAimClientName="+queryByAimClientName;
		}
		ajaxQuery(url);
	}
	
/*+++++++++++++++++++++page-start+++++++++++++++++++++++++++++++++*/ 
  	
	function topPage() {
		var queryByAimClientName=$.trim($("#queryByAimClientName").val());
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getAimClientList&pageNo=1&random="+Math.random();
			if(queryByAimClientName!=null && queryByAimClientName!=""){
				url+="&queryByAimClientName="+queryByAimClientName;
			}
			ajaxQuery(url);
		}
	}
	function previousPage() {
		var queryByAimClientName=$.trim($("#queryByAimClientName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			pn=parseInt(pageNo)-1;
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getAimClientList&pageNo="+pn+"&random="+Math.random();
			if(queryByAimClientName!=null && queryByAimClientName!=""){
				url+="&queryByAimClientName="+queryByAimClientName;
			}
			ajaxQuery(url);
		}
	}

	function nextPage() {
		var queryByAimClientName=$.trim($("#queryByAimClientName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			pn=parseInt(pageNo)+1;
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getAimClientList&pageNo="+pn+"&random="+Math.random();
			if(queryByAimClientName!=null && queryByAimClientName!=""){
				url+="&queryByAimClientName="+queryByAimClientName;
			}
			ajaxQuery(url);
		}
	}

	function bottomPage() {
		var queryByAimClientName=$.trim($("#queryByAimClientName").val());
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getAimClientList&pageNo="+totalPageNum+"&random="+Math.random();
			if(queryByAimClientName!=null && queryByAimClientName!=""){
				url+="&queryByAimClientName="+queryByAimClientName;
			}
			ajaxQuery(url);
		}
	}


/*---------------------page-end-----------------------------------*/
	
	
	function queryAimClient() {
		var queryByAimClientName=$.trim($("#queryByAimClientName").val());
		var url="<%=basePath%>flowcard/FlowCardController.do?command=getAimClientList&random="+Math.random();
		if(queryByAimClientName!=null && queryByAimClientName!=""){
			url+="&queryByAimClientName="+queryByAimClientName;
		}
		ajaxQuery(url);
	}

	function selectOk() {
		if($("input:checked").length!=1){
			alert("请选择一个分销商");
			return;
		}
		var id=$("input:checked").val();
		var aimClientTemiId=$("tr:has(:radio:checked) td:eq(1)").html();
		var aimClientName=$("tr:has(:radio:checked) td:eq(2)").html();
		//var aimClientCategoryName=$("tr:has(:radio:checked) td:eq(3)").html();
		//alert(aimClientCategoryName);
		window.opener.$("[id=aid]:eq("+index+")").val(id);
		window.opener.$("[id=aimId]:eq("+index+")").val(aimClientTemiId);
		window.opener.$("[id=aimName]:eq("+index+")").val(aimClientName);
		//window.opener.$("#itemId").val(categoryName);
		//window.opener.$("[name=unit]:eq("+index+")").val(unitName);
		//window.opener.location="<%=basePath%>flowcard/FlowCardController.do?command=itemSelected&id="+id;
		window.close();
	}
</script>

</head>

<body onload="init()" class="body1">
<form name="aimClientForm">
<div align="center">
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="34">
	<tr>
		<td width="522" class="p1" height="34" nowrap><img src="images/search.gif" width="32" height="32"> &nbsp; <b>请选择需方客户</b></td>
	</tr>
</table>
<hr width="100%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="17%" height="29">
		<div align="left">需方客户代码/名称:</div>
		</td>
		<td width="57%"><input name="queryByAimClientName" type="text" class="text1" id="queryByAimClientName" size="50" maxlength="50"></td>
		<td width="26%">
		<div align="left"><input name="btnQuery" type="button" class="button1" id="btnQuery" value="查询" onclick="queryAimClient()"></div>
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
<table id="aimClientTable" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
	<tr>
		<td class="rd6">选择</td>
		<td class="rd6">需方客户代码</td>
		<td class="rd6">需方客户名称</td>
		<td class="rd6">客户类型</td>
	</tr>
	
	
</table>
<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2" width="36%">
		<div align="left"><font color="#FFFFFF">&nbsp;共<span id="totalPageNum"></span>页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">当前第</font>&nbsp <font color="#FF0000" id="pageNo"></font>&nbsp <font
			color="#FFFFFF" >页</font></div>
		</td>
		<td nowrap class="rd19" width="64%">
		<div align="right">
			<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页" onClick="topPage()">
			<input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage" value=" &lt;  " title="上页" onClick="previousPage()">
			<input name="btnNextPage" class="button1" type="button" id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()">
			<input name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onClick="bottomPage()">
			<input name="btnOk" class="button1" type="button" id="btnOk" value="确定" onClick="selectOk()"> <input name="btnClose" class="button1" type="button" id="btnClose" value="关闭" onClick="window.close()">
		</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>
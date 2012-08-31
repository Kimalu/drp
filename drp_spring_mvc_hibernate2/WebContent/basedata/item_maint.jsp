<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>物料维护</title>
<link rel="stylesheet" href="style/drp.css">
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<script type="text/javascript">
	var jsonValue;
/*+++++++++++++++++++++other-start++++++++++++++++++++++++++++++*/
	function checkAll(){
		var $checkBoxes=$("input[name=selectFlag]");
		$checkBoxes.each(function(){
			$(this).attr("checked",$("#ifAll").attr("checked"));
		});
	}
	function allSelectOrNot(){
		if(getSelectedElements().length==$("#itemTable tr:gt(0)").length){
			$("#itemTable tr:eq(0) input").attr("checked",true);
		}else{
			$("#itemTable tr:eq(0) input").attr("checked",false);
		}
	}
	function init(){
		query();
	}
	
	function ajax(url){
		$.getJSON(url, function(data){
			jsonValue=data;
			displayItemPage();
			});
	}
	function getSelectedElement(){
		return getSelectedElements()[0];
	}
	function getSelectedElements(){
		return $("#itemTable tr:gt(0) input:checked");
	}
	function checkSelected(){
		var selectCount=$("#itemTable tr:gt(0) input:checked").length;
		if(selectCount<1){
			alert("至少选中一条记录");
			return false;
		}
		return true;
	}
	function checkSelectOnlyOne(){
		var selectCount=$("#itemTable tr:gt(0) input:checked").length;
		if(selectCount>1){
			alert("只能选中一条记录");
			return false;
		}else if(selectCount<1){
			alert("必须选中一条记录");
			return false;
		}
		return true;
	}
	function uploadPic4Item(){
		if(!checkSelectOnlyOne()){
			return
		}
		id=getSelectedElement().value;
		window.self.location="<%=basePath%>basedata/ItemController.do?command=showUploadPic4Item&id="+id;
	}
	function showItemDetail(obj){
		
		window.open('<%=basePath%>basedata/ItemController.do?command=showItemDetail&itemNo='+$(obj).html(), '物料详细信息', 'width=400, height=400, scrollbars=no');
		return false;
	}
/*---------------------other-end--------------------------------*/

/*+++++++++++++++++++++display-start++++++++++++++++++++++++++++++*/ 

	function clearTabel(){
		$("#itemTable tr:gt(0)").remove();
	}
	function display(item) {
		var $itemTable = $("#itemTable");
		var $tr =$("<tr></tr>");
		
		var $td1 =$("<td></td>");
		$td1.addClass("rd8");
		$td1.html("<input type='checkbox' name='selectFlag' class='checkbox1' value='"+item.id+"' onClick='allSelectOrNot()'/>");
		
		var $td2 =$("<td></td>");
		$td2.addClass("rd8");
		$td2.html("<a href='#' onClick='return showItemDetail(this);'>"+item.itemNo+"</a>");
		
		var $td3 =$("<td></td>");
		$td3.addClass("rd8");
		$td3.html(item.itemName);
		
		var $td4 = $("<td></td>");
		$td4.addClass("rd8");
		$td4.html(item.spec);

		var $td5 =$("<td></td>");
		$td5.addClass("rd8");
		$td5.html(item.pattern);

		var $td6 =$("<td></td>");
		$td6.addClass("rd8");
		$td6.html(item.itemCategory.name);
		
		var $td7 =$("<td></td>");
		$td7.addClass("rd8");
		$td7.html(item.itemUnit.name);
		
		$tr.append($td1);
		$tr.append($td2);
		$tr.append($td3);
		$tr.append($td4);
		$tr.append($td5);
		$tr.append($td6);
		$tr.append($td7)
		$itemTable.append($tr);
	}
	function displayItemPage(){
 		clearTabel();
 		$.each(jsonValue.list,function(i,item){
			display(item);
		});
		$("#pageNo").html(jsonValue.pageNo);
		$("#totalPageNum").html(jsonValue.totalPageNum);
	}
	
/*---------------------display-end--------------------------------*/

/*++++++++++++++++++++CRUD-start+++++++++++++++++++++++++++++++++*/
	function add(){
		window.self.location = "item_add.jsp";
	}
	function modify(){
		var id;
		if(checkSelectOnlyOne()){
			id=getSelectedElement().value;
			window.self.location= "<%=basePath%>basedata/ItemController.do?command=showModify&id="+id;
		}
	}
	function deleteItem(){
		if(checkSelected()){
			var $itemIds=getSelectedElements();
			var itemIds=new Array();
			$.each($itemIds,function(i,checkBox){
				itemIds.push(checkBox.value);
			});
		}else{
			return false;
		}
		var $form=$("#itemForm");
		var url="<%=basePath %>basedata/ItemController.do?command=del&itemIds="+itemIds;
		$form.attr("action",url);
		$form.attr("method","post");
		$form.submit();
	}
	
	function query(){
		var queryByItemName=$.trim($("#queryByItemName").val());
		var url="<%=basePath%>basedata/ItemController.do?command=getItemList&random="+Math.random();
		if(queryByItemName!=null && queryByItemName!=""){
			url+="&queryByItemName="+queryByItemName;
		}
		ajax(url);
	}
/*---------------------CRUD-end--------------------------------*/
 
/*+++++++++++++++++++++page-start+++++++++++++++++++++++++++++++++*/ 
  	
	function topPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			var url="<%=basePath%>basedata/ItemController.do?command=getItemList&pageNo=1&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajax(url);
		}
	}
	function previousPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			pn=parseInt(pageNo)-1;
			var url="<%=basePath%>basedata/ItemController.do?command=getItemList&pageNo="+pn+"&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajax(url);
		}
	}

	function nextPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			pn=parseInt(pageNo)+1;
			var url="<%=basePath%>basedata/ItemController.do?command=getItemList&pageNo="+pn+"&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajax(url);
		}
	}

	function bottomPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			var url="<%=basePath%>basedata/ItemController.do?command=getItemList&pageNo="+totalPageNum+"&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajax(url);
		}
	}


/*---------------------page-end-----------------------------------*/
</script>
</head>

<body class="body1" onload="init()">
<form name="itemForm" id="itemForm">
<div align="center">
<table width="95%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
	<tr>
		<td width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" alt="我" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;物料维护</b></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="1%" height="29">
		<div align="left" width="100%">物料代码/名称:</div>
		</td>
		<td align="left" width="3%">
		<input  width="100%" name="queryByItemName" type="text" class="text1" id="queryByItemName" size="50" maxlength="50" value="${queryByItemName}"/>
		</td>
		<td align="left" width="5%">
		<div align="left">
			<input name="btnQuery" type="button" class="button1" id="btnQuery" value="查询"  onClick="query()"/>
		</div>
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
		<td nowrap height="10" class="p2">物料信息</td>
		<td nowrap height="10" class="p3">&nbsp;</td>
	</tr>
</table>
<table id="itemTable" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
	<tr>
		<td width="35" class="rd6"><input type="checkbox" name="ifAll" onClick="checkAll()" id="ifAll"></td>
		<td width="155" class="rd6">物料代码</td>
		<td width="155" class="rd6">物料名称</td>
		<td width="155" class="rd6">物料规格</td>
		<td width="155" class="rd6">物料型号</td>
		<td width="138" class="rd6">类别</td>
		<td width="101" class="rd6">计量单位</td>
	</tr>
</table>
<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2" width="36%">
		<div align="left"><font color="#FFFFFF">&nbsp;共<span id="totalPageNum"></span>页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">当前第</font>&nbsp <font color="#FF0000"><span id="pageNo"></span></font>&nbsp <font
			color="#FFFFFF">页</font></div>
		</td>
		<td nowrap class="rd19" width="64%">
		<div align="right">
			<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页" onClick="topPage()"> 
			<input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage" value=" &lt;  " title="上页" onClick="previousPage()"> 
			<input name="btnNextPage" class="button1" type="button" id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()"> 
			<input name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onClick="bottomPage()"> 
			<input name="btnAdd" type="button" class="button1" id="btnAdd" value="添加" onClick="add()"> 
			<input name="btnDelete" class="button1" type="button" id="btnDelete" value="删除" onClick="deleteItem()"> 
			<input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modify()"> 
			<input name="btnUpload" class="button1" type="button" id="btnUpload" value="上传图片" onClick="uploadPic4Item()">
		</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set value="${page}" var="itemPage"/>
<c:set value="${itemPage.list}" var="itemList"/>
<c:set value="${itemPage.totalPageNum}" var="pageTotalNum"/>
<c:set value="${itemPage.currentPageNo }" var="pageNo"/>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>物料维护</title>
<link rel="stylesheet" href="style/drp.css">
<script src="script/windows.js"></script>
<script type="text/javascript">
	function addItem() {
		window.self.location ="<%=basePath%>"+ "basedata/ItemController?command=addItem";
	}

	function modifyItem() {
		var selectFlags=document.getElementsByName("selectFlag");
		var index=selectedOnlyOne(selectFlags);
		if(index==-1){
			return false;
		}
		itemNo=selectFlags[index].value;
		//alert(itemNo);
		window.self.location = "<%=basePath%>"+ "basedata/ItemController?command=showModify&itemNo="+itemNo;
	}

	function showItemDetail(obj){
		//alert(obj.innerHTML);
		window.open('<%=basePath%>basedata/ItemController?command=itemDetail&itemNo='+obj.innerHTML, '物料详细信息', 'width=400, height=400, scrollbars=no');
		return false;
	}
	
	function queryByItemName(){
		 var queryItemName= document.getElementById("clientId4").value;
		 var url="<%=basePath%>basedata/ItemController?command=queryByItemName&queryItemName="+queryItemName;
		// alert(url);
		 window.self.location=url;
	}
	
	function deleteItem() {
		var selectFlags=document.getElementsByName("selectFlag");
		var index=selectedOnlyOne(selectFlags);
		if(index==-1){
			return false;
		}
		itemNo=selectFlags[index].value;
		var url="<%=basePath%>basedata/ItemController?command=del&itemNo="+itemNo;
		window.self.location=url;
	}

	function topPage() {
		var queryItemName= document.getElementById("clientId4").value;
		var pageNo=parseInt(document.getElementById("pageNo").innerHTML);
		if(pageNo<=1){
			return;
		}
		 var url="<%=basePath%>basedata/ItemController?command=queryByItemName&queryItemName="+queryItemName+"&pageNo=1";
		 window.self.location=url;
	}

	function previousPage() {
		var queryItemName= document.getElementById("clientId4").value;
		var pageNo=parseInt(document.getElementById("pageNo").innerHTML);
		if(pageNo<=1){
			return;
		}
		var url="<%=basePath%>basedata/ItemController?command=queryByItemName&queryItemName="+queryItemName+"&pageNo="+(parseInt(pageNo)-1);
		window.self.location=url;
	}

	function nextPage() {
		var queryItemName= document.getElementById("clientId4").value;
		var pageTotalNum=parseInt(document.getElementById("pageTotalNum").innerHTML);
		var pageNo=parseInt(document.getElementById("pageNo").innerHTML);
		if(pageNo>=pageTotalNum){
			return;
		}
		var url="<%=basePath%>basedata/ItemController?command=queryByItemName&queryItemName="+queryItemName+"&pageNo="+(parseInt(pageNo)+1);
		window.self.location=url;
	}

	function bottomPage() {
		var pageTotalNum=parseInt(document.getElementById("pageTotalNum").innerHTML);
		var pageNo=parseInt(document.getElementById("pageNo").innerHTML);
		if(pageNo>=pageTotalNum){
			return;
		}
		var queryItemName= document.getElementById("clientId4").value;
		 var url="<%=basePath%>basedata/ItemController?command=queryByItemName&queryItemName="+queryItemName+"&pageNo="+pageTotalNum;
		 window.self.location=url;
	}

	function checkAll() {

	}

	function uploadPic4Item() {
		
		var selectFlags=document.getElementsByName("selectFlag");
		var flag=countSelected(selectFlags);
		if(flag<1){
			alert("请选择要删除的物料");
			return false;
		}
		if(flag>1){
			alert("只能选中一种物料");
			return false;
		}
		with(itemForm){
			action="<%=basePath%>"+"/basedata/ItemController?command=upload";
			method="post";
			submit();
		}
	}
	function countSelected(selectFlags){
		var flag=0;
		for(var i=0;i<selectFlags.length;i++){
			if(selectFlags[i].checked){
				flag++;
			}
		}
		return flag;
	}
	function selectedOnlyOne(selectFlags){
		var flag=0;
		var index=0;
		for(var i=0;i<selectFlags.length;i++){
			if(selectFlags[i].checked){
				flag++;
				index=i;
			}
		}
		if(flag<1){
			alert("请选择要操作的物料");
			return -1;
		}
		if(flag>1){
			alert("只能选中一种物料");
			return -1;
		}
		return index;
	}
</script>
</head>

<body class="body1">
<form name="itemForm">
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
		<td align="left" width="3%"><input  width="100%" name="clientIdOrName" type="text" class="text1" id="clientId4" size="50" maxlength="50" value="${queryItemName}"></td>
		<td align="left" width="5%">
		<div align="left"><input name="btnQuery" type="button" class="button1" id="btnQuery" value="查询" onclick="queryByItemName()"></div>
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
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
	<tr>
		<td width="35" class="rd6"><input type="checkbox" name="ifAll" onClick="checkAll()"></td>
		<td width="155" class="rd6">物料代码</td>
		<td width="155" class="rd6">物料名称</td>
		<td width="155" class="rd6">物料规格</td>
		<td width="155" class="rd6">物料型号</td>
		<td width="138" class="rd6">类别</td>
		<td width="101" class="rd6">计量单位</td>
	</tr>
	<c:forEach items="${itemList }" var="item">
		<tr>
			<td class="rd8"><input type="checkbox" name="selectFlag" class="checkbox1" value="${item.itemNo }"></td>
			<td class="rd8"><a href="#" onClick="return showItemDetail(this);">${item.itemNo}</a></td>
			<td class="rd8">${item.itemName}</td>
			<td class="rd8">${item.spec}</td>
			<td class="rd8">${item.pattern}</td>
			<td class="rd8">${item.itemCategory.name}</td>
			<td class="rd8">${item.itemUnit.name}</td>
			</tr>
	</c:forEach>
</table>
<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
	<tr>
		<td nowrap class="rd19" height="2" width="36%">
		<div align="left"><font color="#FFFFFF">&nbsp;共<span id="pageTotalNum">${pageTotalNum}</span>页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">当前第</font>&nbsp <font color="#FF0000"><span id="pageNo">${pageNo}</span></font>&nbsp <font
			color="#FFFFFF">页</font></div>
		</td>
		<td nowrap class="rd19" width="64%">
		<div align="right"><input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页" onClick="topPage()"> <input name="btnPreviousPage"
			class="button1" type="button" id="btnPreviousPage" value=" &lt;  " title="上页" onClick="previousPage()"> <input name="btnNextPage" class="button1" type="button" id="btnNextPage"
			value="  &gt; " title="下页" onClick="nextPage()"> <input name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onClick="bottomPage()"> <input
			name="btnAdd" type="button" class="button1" id="btnAdd" value="添加" onClick="addItem()"> <input name="btnDelete" class="button1" type="button" id="btnDelete" value="删除"
			onClick="deleteItem()"> <input name="btnModify" class="button1" type="button" id="btnModify" value="修改" onClick="modifyItem()"> <input name="btnUpload" class="button1" type="button"
			id="btnUpload" value="上传图片" onClick="uploadPic4Item()"></div>
		</td>
	</tr>
</table>
</form>
</body>
</html>

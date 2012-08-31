<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>请选择物料</title>
		<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		var index=${index};
		function init(){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getItemList";
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
					displayItem(this);
				});
				setPageInfo(data);
			})
		}
		function setPageInfo(data){
			$("#totalPageNum").html(data.totalPageNum);
			$("#pageNo").html(data.pageNo);
		}
		
		function displayItem(item){
			var $tr=$("<tr></tr>");
			var $td1=$("<td></td>");
			var $radio=$("<input type='radio' name='selectFlag' value='"+item.id+"' onDblClick='selectOk()'>");
			$td1.addClass("rd8");
			$td1.attr("width",35);
			$td1.append($radio);
			var $td2=$("<td></td>");
			$td2.addClass("rd8");
			$td2.attr("width",170);
			$td2.html(item.itemNo);
			var $td3=$("<td></td>");
			$td3.addClass("rd8");
			$td3.attr("width",222);
			$td3.html(item.itemName);
			var $td4=$("<td></td>");
			$td4.addClass("rd8");
			$td4.attr("width",195);
			$td4.html(item.spec);
			var $td5=$("<td></td>");
			$td5.addClass("rd8");
			$td5.attr("width",293);
			$td5.html(item.pattern);
			var $td6=$("<td></td>");
			$td6.addClass("rd8");
			$td6.attr("width",293);
			$td6.html(item.itemCategory.name);
			var $td7=$("<td></td>");
			$td7.addClass("rd8");
			$td7.attr("width",293);
			$td7.html(item.itemUnit.name);
			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);
			$tr.append($td5);
			$tr.append($td6);
			$tr.append($td7);
			$("#itemTable").append($tr);
		}
		
		function clearTable(){
			$("#itemTable tr:gt(0)").remove();
		}
		
	function queryClient() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var url="<%=basePath%>flowcard/FlowCardController.do?command=getItemList&random="+Math.random();
		if(queryByItemName!=null && queryByItemName!=""){
			url+="&queryByItemName="+queryByItemName;
		}
		ajaxQuery(url);
	}
	
	function selectOk(obj) {
		if($("input:checked").length!=1){
			alert("请选择一种物料");
			return;
		}
		var id=$("input:checked").val();
		var itemNo=$("tr:has(:radio:checked) td:eq(1)").html();
		var itemName=$("tr:has(:radio:checked) td:eq(2)").html();
		var spec=$("tr:has(:radio:checked) td:eq(3)").html();
		var pattern=$("tr:has(:radio:checked) td:eq(4)").html();
		var categoryName=$("tr:has(:radio:checked) td:eq(5)").html();
		var unitName=$("tr:has(:radio:checked) td:eq(6)").html();
		//alert($("tr:eq("+(index+1)+")").length);
		window.opener.$("[id=itid]:eq("+index+")").val(id);
		window.opener.$("[name=itemNo]:eq("+index+")").val(itemNo);
		window.opener.$("[name=itemName]:eq("+index+")").val(itemName);
		window.opener.$("[name=spec]:eq("+index+")").val(spec);
		window.opener.$("[name=pattern]:eq("+index+")").val(pattern);
		//window.opener.$("#itemId").val(categoryName);
		window.opener.$("[name=unit]:eq("+index+")").val(unitName);
		//window.opener.location="<%=basePath%>flowcard/FlowCardController.do?command=itemSelected&id="+id;
		window.close();
	}
	
/*+++++++++++++++++++++page-start+++++++++++++++++++++++++++++++++*/ 
  	
	function topPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getItemList&pageNo=1&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajaxQuery(url);
		}
	}
	function previousPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		if(pageNo!=1){
			pn=parseInt(pageNo)-1;
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getItemList&pageNo="+pn+"&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajaxQuery(url);
		}
	}

	function nextPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pn;
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			pn=parseInt(pageNo)+1;
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getItemList&pageNo="+pn+"&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajaxQuery(url);
		}
	}

	function bottomPage() {
		var queryByItemName=$.trim($("#queryByItemName").val());
		var pageNo=$("#pageNo").html();
		var totalPageNum=$("#totalPageNum").html();
		if(pageNo!=totalPageNum){
			var url="<%=basePath%>flowcard/FlowCardController.do?command=getItemList&pageNo="+totalPageNum+"&random="+Math.random();
			if(queryByItemName!=null && queryByItemName!=""){
				url+="&queryByItemName="+queryByItemName;
			}
			ajaxQuery(url);
		}
	}


/*---------------------page-end-----------------------------------*/
</script>
	</head>

	<body onload="init()" class="body1">
		<form name="selectItemForm">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="34">
					<tr>
						<td width="522" class="p1" height="34" nowrap>
							<img src="images/search.gif" width="32" height="32">
							&nbsp;
							<b>请选择物料</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="17%" height="29">
							<div align="left">
								物料代码/名称:
							</div>
						</td>
						<td width="57%">
							<input name="queryByItemName" type="text" class="text1"
								id="queryByItemName" size="50" maxlength="50" value="${queryByItemName}">
						</td>
						<td width="26%">
							<div align="left">
								<input name="btnQuery" type="button" class="button1"
									id="btnQuery" value="查询" onclick="queryClient()">
							</div>
						</td>
					</tr>
					<tr>
						<td height="16">
							<div align="right"></div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="right"></div>
						</td>
					</tr>
				</table>

			</div>
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				class="rd1" align="center">
				<tr>
					<td nowrap height="10" class="p2">
						物料信息
					</td>
					<td nowrap height="10" class="p3">
						&nbsp;
					</td>
				</tr>
			</table>
			<table id="itemTable" name="itemTable" width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td width="35" class="rd6">
						选择
					</td>
					<td width="170" class="rd6">
						物料代码
					</td>
					<td width="222" class="rd6">
						物料名称
					</td>
					<td width="195" class="rd6">
						物料规格
					</td>
					<td width="293" class="rd6">
						物料型号
					</td>
					<td width="293" class="rd6">
						类别
					</td>
					<td width="293" class="rd6">
						计量单位
					</td>
				</tr>
			</table>
			<table width="95%" height="30" border="0" align="center"
				cellpadding="0" cellspacing="0" class="rd1">
				<tr>
					<td nowrap class="rd19" height="2" width="36%">
						<div align="left">
							<font color="#FFFFFF">&nbsp;共<span id="totalPageNum"></span>页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="#FFFFFF">当前第</font>&nbsp
							<font color="#FF0000" id="pageNo"></font>&nbsp
							<font color="#FFFFFF">页</font>
						</div>
					</td>
					<td nowrap class="rd19" width="64%">
						<div align="right">
							<input name="btnTopPage" class="button1" type="button"
								id="btnTopPage" value="|&lt;&lt; " title="首页"
								onclick="topPage()">
							<input name="btnPreviousPage" class="button1" type="button"
								id="btnPreviousPage" value=" &lt;  " title="上页"
								onclick="previousPage()">
							<input name="btnNextPage" class="button1" type="button"
								id="btnNextPage" value="  &gt; " title="下页" onclick="nextPage()">
							<input name="btnBottomPage" class="button1" type="button"
								id="btnBottomPage" value=" &gt;&gt;|" title="尾页"
								onclick="bottomPage()">
							<input name="btnOk" class="button1" type="button" id="btnOk"
								value="确定" onclick="selectOk()">
							<input name="btnClose" class="button1" type="button"
								id="btnClose" value="关闭" onclick="window.close()">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
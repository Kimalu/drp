<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>添加流向单维护</title>
<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
<link rel="stylesheet" href="style/drp.css">
<link href="style/JSCalendar.css" rel=stylesheet type=text/css>
<script language="javascript">
	var rowIndex = 0;

	function selectAimClient(index) {
		window.open('<%=basePath%>flowcard/FlowCardController.do?command=showAimClientSelect&index=' + index, '请选择需方客户', 'width=700, height=400, scrollbars=no');
	}

	function selectItem(index) {
		window.open("<%=basePath%>flowcard/FlowCardController.do?command=showItemSelect&index="+index, '请选择物料', 'width=700, height=400, scrollbars=no');
	}

	function addOneLineOnClick(){
		var $table=$("#tblFlowCardDetail");
		var $tr1=$("<tr></tr>");
		var $td0=$("<td></td>");
		$td0.html("<input type='hidden' id='aid' name='aid'/><input readonly='true' maxLength='6' size='6' id='aimId' name='aimId'><input type='button'  value ='...'   name='btnSelectAimClient' index='"+ rowIndex +"' onclick='selectAimClient(this.index)'/>");
		var $td1=$("<td></td>");
		$td1.html("<input id='aimName' name='aimName' size='25' maxlength='25' readonly='true'/>");
		var $td2=$("<td></td>");
		$td2.html("<input type='hidden' id='itid' name='itid'/><input readonly='true' maxLength='6' size='6' id='itemNo' name='itemNo'><input type=button  value ='...'   name='btnSelectAimClient' index='"+rowIndex+"' onclick='selectItem(this.index)'/>");
		var $td3=$("<td></td>");
		$td3.html("<input id='itemName' name='itemName' size='25' maxlength='25' readonly='true'/>");
		var $td4=$("<td></td>");
		$td4.html("<input id='spec' name='spec' size='10' maxlength='10' readonly='true'/>");
		var $td5=$("<td></td>");
		$td5.html("<input id='pattern' name='pattern' size='10' maxlength='10' readonly='true'/>");
		var $td6=$("<td></td>");
		$td6.html("<input id='unit' name='unit' size='4' maxlength='4' readonly='true'/>");
		var $td7=$("<td></td>");
		$td7.html("<input id='qty' name='qty' size='6' maxlength='6'/>");
		var $td8=$("<td></td>");
		$td8.html("<input type='button' value='删除' id='btnDeleteLine' name='btnDeleteLine' onclick=\"return DeleteRow(" + rowIndex + ")\"/>");
		$tr1.append($td0);
		$tr1.append($td1);
		$tr1.append($td2);
		$tr1.append($td3);
		$tr1.append($td4);
		$tr1.append($td5);
		$tr1.append($td6);
		$tr1.append($td7);
		$tr1.append($td8);
		$table.append($tr1);
		rowIndex++;
	}

	function DeleteRow(rowTag){
		var idx=parseInt(rowTag)+1;
		//alert($("#tblFlowCardDetail tr:gt(0)").length);
		$("#tblFlowCardDetail tr:gt("+idx+") input[index]").each(function(i){
 	  		$(this).attr("index",$(this).attr("index")-1);
 	  	});
 	  	$("#tblFlowCardDetail tr:eq("+idx+")").remove();
 	  	rowIndex--;
 	  	
	}

	function modifyFlowCard() {

	}

	function goBack() {
		history.go(-1);
	}
</script>
</head>

<body class="body1">
<div align="center">
<form name="flowCardAddForm" method="post" action="<%=basePath%>flowcard/FlowCardController.do?command=modifyFlowCard">
<table width="95%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14"> &nbsp; <b>分销商库存管理&gt;&gt;流向单维护&gt;&gt;修改</b></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="11%" height="29">
		<div align="right">流向单号:</div>
		</td>
		<td width="8%"><input type="hidden" id="fcId" name="fcId" value="${flowCard.fcId}"/>${flowCard.flowCardNo}</td>
		<td width="14%">
		<div align="right"><font color="#FF0000">*</font>供方分销商代码:&nbsp;</div>
		</td>
		<td width="16%">
		<input type="hidden" name="cid" id="cid" value="${flowCard.client.id }"/>
		<input name="clientNo" type="text" class="text1" id="clientNo" size="10" maxlength="10" readonly="true" value="${flowCard.client.clientNo }"/> 
		<input name="btnSelectClient" type="button" id="btnSelectClient" value="..." class="button1" onClick="window.open('<%=basePath%>flowcard/FlowCardController.do?command=showClientSelect', '选择分销商', 'width=700, height=400, scrollbars=no')">
			</td>
		<td width="13%">
		<div align="right">
		<div align="right">供方分销商名称:&nbsp;</div>
		</div>
		</td>
		<td width="29%"><input name="clientName" type="text" class="text1" id="clientName" size="40" maxlength="40" readonly="true" value="${flowCard.client.name }"></td>
		<td width="5%">&nbsp;</td>
		<td width="4%">&nbsp;</td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0" id="tblFlowCardDetail">
	<tr>
		<td nowrap>
		<div align="left"><font color="#FF0000">*</font>需方客户代码</div>
		</td>
		<td nowrap>
		<div align="left">需方客户名称</div>
		</td>
		<td nowrap>
		<div align="left"><font color="#FF0000">*</font>物料代码</div>
		</td>
		<td nowrap>
		<div align="left">物料名称</div>
		</td>
		<td nowrap>规格</td>
		<td nowrap>型号</td>
		<td nowrap>计量单位</td>
		<td nowrap><font color="#FF0000">*</font>操作数量</td>
		<td nowrap>
		<div align="left">删除</div>
		</td>
	</tr>
	<c:forEach var="fcd" items="${fcdList }" varStatus="i">
	<tr>
		<td>
		<input type='hidden' id='aid' name='aid' value='${fcd.temiClient.id }'/>
		<input readonly='true' maxLength='6' size='6' id='aimId' name='aimId' value='${fcd.temiClient.clientNo }'>
		<input type='button'  value ='...'   name='btnSelectAimClient' index='${i.index}' onclick='selectAimClient(this.index)'/>
		</td>
		<td>
		<input id='aimName' name='aimName' size='25' maxlength='25' readonly='true' value='${fcd.temiClient.name}'/>
		</td>
		<td>
		<input type='hidden' id='itid' name='itid' value="${fcd.item.id }"/>
		<input readonly='true' maxLength='6' size='6' id='itemNo' name='itemNo' value='${fcd.item.itemNo }'>
		<input type=button  value ='...'   name='btnSelectAimClient' index='${i.index}' onclick='selectItem(this.index)'/>
		</td>
		<td>
		<input id='itemName' name='itemName' size='25' maxlength='25' readonly='true' value="${fcd.item.itemName }"/>
		</td>
		<td>
		<input id='spec' name='spec' size='10' maxlength='10' readonly='true' value="${fcd.item.spec }"/>
		</td>
		<td>
		<input id='pattern' name='pattern' size='10' maxlength='10' readonly='true' value="${fcd.item.pattern }"/>
		</td>
		<td>
		<input id='unit' name='unit' size='4' maxlength='4' readonly='true' value="${fcd.item.itemUnit.name }"/>
		</td>
		<td>
		<input id='qty' name='qty' size='6' maxlength='6' value="${fcd.optQty }"/>
		</td>
		<td>
		<input type='button' value='删除' id='btnDeleteLine' name='btnDeleteLine' onclick='return DeleteRow(${i.index})'/>
		</td>
		<script type="text/javascript">
		rowIndex++;
		</script>
	</tr>
	</c:forEach>
</table>
<p><input name="btnModifyLine" type="button" id="btnModifyLine" onClick="return addOneLineOnClick()" value="加入一行"/> 
	<input name="btnSave" type="submit" id="btnSave" value="保存"/> 
	<input name="btnBack" type="button" id="btnBack" onClick='goBack()' value="返回"/>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</form>
<p>&nbsp;</p>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@page import="java.text.*" %>    
<%@page import="java.util.*" %>
<%@page import="com.bjpowernode.drp.basedata.manager.*" %>
<%@page import="com.bjpowernode.drp.basedata.domain.*" %>
<%  
	String command = request.getParameter("command");
	if (command != null && command.equals("add")) { 
		FiscalYearPeriod fiscalYearPeriod = new FiscalYearPeriod();
		fiscalYearPeriod.setFiscalYear(Integer.parseInt(request.getParameter("fiscalYear")));
		fiscalYearPeriod.setFiscalPeriod(Integer.parseInt(request.getParameter("fiscalPeriod")));
		fiscalYearPeriod.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("beginDate")));
		fiscalYearPeriod.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")));
		fiscalYearPeriod.setPeriodSts(request.getParameter("periodSts") == null?"N":"Y");
		FiscalYearPeriodManager.getInstance().addFiscalYearPeriod(fiscalYearPeriod);
		out.println("��ʾ����Ӻ����ڼ�ɹ���");
	}
 %>    
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>��ӻ�ƺ����ڼ�</title>
		<link rel="stylesheet" href="../style/drp.css">
		<link href="../style/JSCalendar.css" rel=stylesheet type=text/css>
		<script src="../script/JSCalendar.js"></script>
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
			function init() {
				document.getElementById("fiscalYear").focus();
			}
		
			function addFiscalYearPeriod() {
				if (!isinteger(trim(document.getElementById("fiscalYear").value))) {
					alert("���������Ϊ������");
					document.getElementById("fiscalYear").focus();
					return;
				}
				
				if (!isinteger(trim(document.getElementById("fiscalPeriod").value))) {
					alert("�����±���Ϊ������");
					document.getElementById("fiscalPeriod").focus();
					return;
				}
				
				if (document.getElementById("responseMsg").innerHTML != "" ) {
					alert("�������������ظ���");
					document.getElementById("fiscalYear").focus();
					return;
				}
				
				if (trim(document.getElementById("beginDate").value) > trim(document.getElementById("endDate").value)) {
					alert("��ʼ���ڱ���С�ڵ��ڽ������ڣ�");
					document.getElementById("beginDate").focus();
					return;
				}
				
				with (document.getElementById("fiscalYearPeriodForm")) {
					method = "post";
					action = "fiscal_year_period_add.jsp?command=add";
					submit();
				}
			}
			
			function goBack() {
				window.self.location = "fiscal_year_period_maint.jsp";
			}
			
			function fiscalPeriodOnkeyPress() {
				if (!(window.event.keyCode >=48 && window.event.keyCode<=57)) {
					window.event.keyCode = 0;		
				}
			}
			
			function fiscalYearOnkeypress() {
				if (!(window.event.keyCode >=48 && window.event.keyCode<=57)) {
					window.event.keyCode = 0;		
				}
			}
	//---------------------------Ajax begin----------------------------	
	var xmlHttp;
	
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function validate() {
		if (trim(document.getElementById("fiscalYear").value) != "" &&
		    trim(document.getElementById("fiscalPeriod").value) != "") {
			createXMLHttpRequest();
			var fiscalYear = document.getElementById("fiscalYear");
			var fiscalPeriod = document.getElementById("fiscalPeriod");
			var url = "../servlet/FiscalYearPeriodValidateServlet?fiscalYear=" + fiscalYear.value + "&fiscalPeriod=" + fiscalPeriod.value + "&timestampt=" + new Date().getTime();
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = callback;
			xmlHttp.send(null);
		}
	}

	function callback() {
		if(xmlHttp.readyState == 4) {
			if(xmlHttp.status == 200) {
				var responseMsgDiv = document.getElementById("responseMsg");
				if (trim(xmlHttp.responseText) != "") {
					responseMsgDiv.innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>";
				}else {
					responseMsgDiv.innerHTML = "";
				}
			}
		}
	}		
	
	//---------------------------Ajax end----------------------------	
		</script>
	</head>

	<body class="body1" onload="init()">
		<form name="fiscalYearPeriodForm" target="_self"
			id="fiscalYearPeriodForm">
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
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>�������ݹ���&gt;&gt;��ƺ����ڼ�ά��&gt;&gt;���</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								<font color="#FF0000">*</font>������:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="fiscalYear" type="text" class="text1" "
								id="fiscalYear" size="10" maxlength="4" onblur="validate()" onkeypress="fiscalYearOnkeypress()" > 
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>������:&nbsp;
							</div>
						</td>
						<td>
							<input name="fiscalPeriod" type="text" class="text1""
								id="fiscalPeriod" size="10" maxlength="2" onblur="validate()" onkeypress="fiscalPeriodOnkeyPress()">&nbsp<span id="responseMsg"></span>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>��ʼ����:&nbsp;
							</div>
						</td>
						<td>
							<label>
								<input type="text" name="beginDate" id="beginDate" size="10" maxlength="10"
									value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" readonly="true" onClick=JSCalendar(this)>
							</label>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>��������:&nbsp;
							</div>
						</td>
						<td>
							<input name="endDate" type="text" id="endDate"
								onClick=JSCalendar(this) value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" size="10"
								maxlength="10" readonly="true">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>�Ƿ����:&nbsp;
							</div>
						</td>
						<td>
							<input name="periodSts" type="checkbox" class="checkbox1"
								id="periodSts">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd" onclick="addFiscalYearPeriod()"
						value="���">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="����" onClick="goBack()">
				</div>
			</div>
		</form>
	</body>
</html>

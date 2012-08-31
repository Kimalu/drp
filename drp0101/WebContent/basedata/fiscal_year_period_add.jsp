<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加会计核算期间</title>
<link rel="stylesheet" type=text/css href="../style/drp.css">
<link href="../style/JSCalendar.css" rel=stylesheet type=text/css>
<script type="text/javascript" src="../script/javascriptx.js"></script>
<script src="../script/JSCalendar.js"></script>
<script src="../script/client_validate.js"></script>
<script language="javascript">
			var re=new RegExp();
			function add(){
				var flag=validate();
				if(flag){
					var form=document.getElementById("fiscalYearPeriodForm");
					form.action="<%=request.getContextPath()%>/basedata/FiscalYearPeriodController?command=add";
					form.method="post";
					form.submit();
				}
			}
			function validate(){
				var fiscalYear=v_year();
				var fiscalMonth=v_month();
				if(!(fiscalYear===undefined)){
					if(!(fiscalMonth===undefined)){
						if(v_date()){
							return true;
						}
					}	
				}
				return false;
				
			}
			
			function v_year(){
				var fiscalYear=document.getElementById("fiscalYear");
				var fiscalYearValue=fiscalYear.value;
				re.compile(/\d{4}/);
				if(fiscalYearValue!=null && fiscalYearValue.trim()!=""){
					if(re.test(fiscalYearValue)){
						return fiscalYearValue;
					}else{
						fiscalYear.focus();
						alert("输入四位数的年份");
					}
				}
			}
			function v_month(){
				var fiscalMonth=document.getElementById("fiscalMonth");
				var fiscalMonthValue=fiscalMonth.value;
				re.compile(/[0-1]\d{1}/);
				
				if(fiscalMonthValue!=null && fiscalMonthValue!=""){
					
					if(re.test(fiscalMonthValue)){
						
						return fiscalMonthValue;
					}else{
						fiscalMonth.focus();
						alert("输入两位数的月份");
					}
				}
			}
			function v_date(){
				var beginDate=document.getElementById("beginDate");
				var endDate=document.getElementById("endDate");
				
				return compare_time(beginDate.value,endDate.value);
			}
			
			function compare_time(a,b) {
				   var arr=a.split("-");
				   var starttime=new Date(arr[0],arr[1],arr[2]);
				   var starttimes=starttime.getTime(); 
				   var arrs=b.split("-"); 
				   var endtime=new Date(arrs[0],arrs[1],arrs[2]);
				   var endtimes=endtime.getTime();
					
				   if(starttimes>endtimes)//开始大于结束
				   {
					   alert("开始日期不能大于结束日期");
				     return false;
				   } 
				   else{ 
				    return true; 
				   }
				}
		</script>
</head>

<body class="body1">
<form name="fiscalYearPeriodForm" target="_self" id="fiscalYearPeriodForm">
<div align="center">
<table width="95%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" height="25">
	<tr>
		<td width="522" class="p1" height="25" nowrap><img src="../images/mark_arrow_03.gif" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;会计核算期间维护&gt;&gt;添加</b></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<table width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="22%" height="29">
		<div align="right"><font color="#FF0000">*</font>核算年:&nbsp;</div>
		</td>
		<td align="left" width="78%"><input name="fiscalYear" type="text" class="text1" id="fiscalYear" size="10" maxlength="10"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>核算月:&nbsp;</div>
		</td>
		<td align="left"><input name="fiscalMonth" type="text" class="text1" id="fiscalMonth" size="10" maxlength="10"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>开始日期:&nbsp;</div>
		</td>
		<td align="left"><label> <input id="beginDate" type="text" name="beginDate" size="10" maxlength="10" value="2001-01-01" readonly="true" onClick=JSCalendar(this)> </label></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>结束日期:&nbsp;</div>
		</td>
		<td align="left"><input id="endDate" name="endDate" type="text" id="endDate" onClick=JSCalendar(this) value="2001-01-01" size="10" maxlength="10" readonly="true"></td>
	</tr>
	<tr>
		<td height="26">
		<div align="right"><font color="#FF0000">*</font>是否可用:&nbsp;</div>
		</td>
		<td align="left"><input name="periodSts" type="checkbox" class="checkbox1" id="periodSts" value="checkbox"></td>
	</tr>
</table>
<hr width="97%" align="center" size=0>
<div align="center"><input name="btnAdd" class="button1" type="button" id="btnAdd" value="添加" onclick="add()"> &nbsp;&nbsp;&nbsp;&nbsp; <input name="btnBack" class="button1"
	type="button" id="btnBack" value="返回" onClick="history.go(-1)"></div>
</div>
</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.Kimalu.drp.service.ClientService" %>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<link rel="stylesheet" href="../style/drp.css">
		<style type="text/css">
a:link {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
}
a:visited {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
	
}
a:hover {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;

}
a:active {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
}
		</style>

<script language="JavaScript">
function display(id) {
	 eval("var div=div"+id);
	 eval("var img=img"+id);
	 eval("var im=im"+id);
	 div.style.display=div.style.display=="block"?"none":"block";
	 img.src=div.style.display=="block"?"../images/minus.gif":"../images/plus.gif";
	 im.src=div.style.display=="block"?"../images/openfold.gif":"../images/closedfold.gif";
	 img.alt=div.style.display=="block"?"关闭":"展开";
}
</script>
	</head>
	<body class="body1">
		<table>
			<tr>
				<td valign="top" nowrap="nowrap">
					<%
						ClientService cs=ClientService.getInstance(); 
						out.print(cs.getClientTree());
					%>
				</td>
			</tr>
		</table>
	</body>
</html>


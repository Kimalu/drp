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
	<script type="text/javascript" src="script/jquery/jquery-1.4.4.js"></script>
	<script type="text/javascript" src="script/xtree2/xtree2.js"></script>
	<script type="text/javascript" src="script/xtree2/xloadtree2.js"></script>
	<link type="text/css" rel="stylesheet" href="style/tree2/xtree2.css" />
	
	<style type="text/css">
		body {
			background: white;
			color: black;
		}
	</style>
	<script type="text/javascript">
	
	</script>
</head>
<body>
<script type="text/javascript"> 
	var tree = new WebFXLoadTree("","<%=basePath%>basedata/ClientController.do?command=getClientTree");
	tree.setShowRootLines(false);
	tree.setShowRootNode(false);
	tree.write();
</script>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="u" value="${user}"></c:set>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="ext4/resources/css/ext-all.css" />
<!-- <link type="text/css" rel="stylesheet" href="test.css" /> -->
<script type="text/javascript" src="ext4/bootstrap.js"></script>
<script type="text/javascript" src="ext4/locale/ext-lang-zh_CN.js"></script>
<title>DRP分销系统</title>
<script type="text/javascript">
	Ext.onReady(function() {
		var header = Ext.create('Ext.panel.Panel', {
			//title : 'DRP分销系统',
			frame : false,
			autoScroll : false,
			collapsible : true,
			height : 140,
			region : 'north',
			bodyStyle : 'background-image: url(images/maintop.gif);'
		});
		
		
		Ext.regModel('sysMenu',{
			fields:[
				{name:'id',type:'string'},
				{name:'nodeName',type:'string'},
				{name:'url',type:'string'},
				{name:'pid',type:'string'},
				{name:'isMainMenu',type:'string'}
			]
		});
		var ajaxProxy=new Ext.data.proxy.Ajax({
			url:'<%=basePath%>sysmgr/SysMenuController.do?command=getSysMainMenuList',
			model:'sysMenu',
			reader: 'json'
		});
		var operation=new Ext.data.Operation({
			action:'read'
		});
		ajaxProxy.doRequest(operation,callback);
		function callback(operation){
			//var responseText=operation.response.responseText;
			var mainMenuList = Ext.JSON.decode(operation.response.responseText); 
			initMainMenu(mainMenuList);
		}
		
		function initMainMenu(mainMenuList){
			var item;
			for(i in mainMenuList){
				//alert(i);
				item=Ext.create('Ext.panel.Panel',{
					title:mainMenuList[i].nodeName,
					html:'&lt;empty panel&gt;',
					cls: 'empty'
				});
				Ext.getCmp('mainAccordion').add(item);
			}
		}
		/* sysMenuData.load(1,{
			scope: this,
	        
	        success: function(record, operation) {
	        	alert(record.data.nodeName);
	        	createMainMenu(record);
	        }
		}); */
		
		/* function createMainMenu(record){
			var item=Ext.create('Ext.panel.Panel',{
				title:record.data.nodeName,
				html:'&lt;empty panel&gt;',
				cls: 'empty'
			});
			Ext.getCmp('mainAccordion').add(item);
		} */
		
		
		
		
		
		/* var item1 = Ext.create('Ext.Panel', {
			title : 'Accordion Item 1',
			html : '&lt;empty panel&gt;',
			cls : 'empty'
		});

		var item2 = Ext.create('Ext.Panel', {
			title : 'Accordion Item 2',
			html : '&lt;empty panel&gt;',
			cls : 'empty'
		});

		var item3 = Ext.create('Ext.Panel', {
			title : 'Accordion Item 3',
			html : '&lt;empty panel&gt;',
			cls : 'empty'
		});

		var item4 = Ext.create('Ext.Panel', {
			title : 'Accordion Item 4',
			html : '&lt;empty panel&gt;',
			cls : 'empty'
		});
 */
		var accordion = Ext.create('Ext.Panel', {
			title : '主菜单',
			id:'mainAccordion',
			region : 'west',
			margins : '5 0 5 5',
			split : true,
			width : 210,
			collapsible : true,
			layout : 'accordion'
		//	items : [ item1, item2, item3, item4 ]
		});

		var viewport = Ext.create('Ext.Viewport', {
			layout : 'border',
			items : [ accordion, header, {
				region : 'center',
				margins : '5 5 5 0',
				cls : 'empty',
				bodyStyle : 'background:#f1f1f1',
				html : '<br/><br/>&lt;empty center panel&gt;'
			} ]
		});

	})
</script>
</head>

<!-- <frameset rows="67,600*" cols="*" frameborder="NO" border="0" framespacing="0">
	<frame src="head.html" name="topFrame" frameborder="no" scrolling="NO" noresize marginwidth="0" marginheight="0">
	<frameset cols="171,836*" frameborder="NO" border="0" framespacing="0" rows="*" name="workaround">
		<frameset rows="15,*" cols="*" framespacing="0" frameborder="NO" border="0">
			<frame src="hidden_left_frame.html" name="topFrame1" frameborder="no" scrolling="no" noresize>
			<frame name="leftFrame" noresize scrolling="NO" src="menu.html" frameborder=NO marginwidth="0" marginheight="0">
		</frameset>
		<frameset rows="34,*" cols="*" framespacing="0" frameborder="no" border="0">
			<frame src="toolbar.jsp" name="toolBar" frameborder="no" scrolling="no" noresize marginwidth="0" marginheight="0" id="toolBar">
			<frame name="main" src="about.html" marginWidth=0 scrolling="NO" marginheight="0" noresize>
		</frameset>
	</frameset>
</frameset> -->
<!-- <noframes> -->
<body bgcolor="#FFFFFF">

</body>
</noframes>
</html>

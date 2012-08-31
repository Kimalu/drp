<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<img alt='展开' style='cursor:hand;' onClick="display('10000');" id='img10000' src='../images/plus.gif'>
	<img id='im10000' src='../images/closedfold.gif'>
	<a href='client_node_crud.jsp' target='clientDispAreaFrame'>所有分销商</a>
		<div style='display:none;' id='div10000'>
			<div>
			<img src='../images/white.gif'>
			<img alt='展开' style='cursor:hand;' onClick="display('10001');" id='img10001' src='../images/plus.gif'>
			<img id='im10001' src='../images/closedfold.gif'>
			<a href='client_node_crud.jsp' target='clientDispAreaFrame'>华北区</a>
				<div style='display:none;' id='div10001'>
					<div>
					<img src='../images/white.gif'>
					<img src='../images/white.gif'>
					<img alt='展开' style='cursor:hand;' onClick="display('10002');" id='img10002' src='../images/plus.gif'>
					<img id='im10002' src='../images/closedfold.gif'>
					<a href='client_node_crud.jsp' target='clientDispAreaFrame'>北京市</a>
						<div style='display:none;' id='div10002'>
							<div>
							<img src='../images/white.gif'>
							<img src='../images/white.gif'>
							<img src='../images/white.gif'>
							<img alt='展开' style='cursor:hand;' onClick="display('10003');" id='img10003' src='../images/minus.gif'>
							<img id='im10003' src='../images/closedfold.gif'>
							<a href='client_node_crud.jsp' target='clientDispAreaFrame'>北京医药股份有限公司</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		<img alt='展开' style='cursor:hand;' onClick="display('10004');" id='img10004' src='../images/minus.gif'>
		<img id='im10004' src='../images/closedfold.gif'>
		<a href='client_node_crud.jsp' target='clientDispAreaFrame'>东北区</a>
		</div>
	<img alt='展开' style='cursor:hand;' onClick="display('10005');" id='img10005' src='../images/minus.gif'>
	<img id='im10005' src='../images/closedfold.gif'>
	<a href='client_node_crud.jsp' target='clientDispAreaFrame'>华南区</a>
</div>





					<div>
						<img alt="展开" style="cursor:hand;" onClick="display('1');"
							id="img1" src="../images/plus.gif">
						<img id="im1" src="../images/closedfold.gif">
						<a href="client_node_crud.html" target="clientDispAreaFrame">所有分销商</a>
						<div style="display:none;" id="div1">
							<div>
								<img src="../images/white.gif">
								<img alt="展开" style="cursor:hand;" onClick="display('2');"
									id="img2" src="../images/plus.gif">
								<img id="im2" src="../images/closedfold.gif">
								<a href="client_node_crud.html" target="clientDispAreaFrame">华北区</a>
								<div style="display:none;" id="div2">
									<div>
										<img src="../images/white.gif">
										<img src="../images/white.gif">
										<img alt="展开" style="cursor:hand;" onClick="display('3');"
											id="img3" src="../images/plus.gif">
										<img id="im3" src="../images/closedfold.gif">
										<a href="client_node_crud.html" target="clientDispAreaFrame">北京市</a>
										<div style="display:none;" id="div3">
											<div>
												<img src="../images/white.gif">
												<img src="../images/white.gif">
												<img src="../images/white.gif">
												<img src="../images/minus.gif">
												<img src="../images/openfold.gif">
												<a href="client_crud.html" target="clientDispAreaFrame">北京医药股份有限公司</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div>
								<img src="../images/white.gif">
								<img src="../images/minus.gif">
								<img src="../images/openfold.gif">
								<a href="client_node_crud.html" target="clientDispAreaFrame">东北区</a>
							</div>
							<div>
								<img src="../images/white.gif">
								<img src="../images/minus.gif">
								<img src="../images/openfold.gif">
								<a href="client_node_crud.html" target="clientDispAreaFrame">华南区</a>
							</div>
						</div>
					</div>
</body>
</html>
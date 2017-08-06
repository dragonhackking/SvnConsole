<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is index page">

<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/my.js"></script>
</head>

<body>
	<div class="content">
		<div class="header">
			<img src="images/header.jpg" width="100%" />
		</div>
		<div id="nav" class="nav">
			<ul>
				<li><a href="login.do?loginOp=toIndex">首页</a></li>
				<li><a href="userManage.do?userOp=toUserManage" style="background-color:#66D9EF;color:#E3EEEB;">用户管理</a></li>
				<li><a href="authManage.do?authOp=toAuthManage">权限管理</a></li>
			</ul>
		</div>
		<div id="user-middle" class="user-middle">
			<p>已接入<c:out value="${fn:length(server) } "/>台svn服务器：</p>
			<div class="showUsers">
				<c:forEach items="${allUsers }" var="map">
					<div class="showUsersItem">
						<div class="userTitle">
							<p class="ip">svn服务器：${map.key }</p>
							<p class="userNum">用户数：${fn:length(map.value) }</p>
						</div>
						<div class="users">
							<c:forEach items="${map.value }" var="u">
								<p><input type="checkbox" value="${u }"/>&nbsp;${u }</p>
							</c:forEach>
						</div>		
					</div>			
				</c:forEach>
			</div>
		</div>
		<div id="footer" class="footer">
			
		</div>
	</div>
</body>
</html>

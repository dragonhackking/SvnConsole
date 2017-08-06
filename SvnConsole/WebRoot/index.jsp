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
				<li><a href="login.do?loginOp=toIndex" style="background-color:#66D9EF;color:#E3EEEB;">首页</a></li>
				<li><a href="userManage.do?userOp=toUserManage">用户管理</a></li>
				<li><a href="authManage.do?authOp=toAuthManage">权限管理</a></li>
			</ul>
		</div>
		<div id="index-middle" class="index-middle">
			<p>已接入<c:out value="${fn:length(server) } "/>台svn服务器：</p>
			<c:forEach items="${server }" var="s" varStatus="item">
				<!-- <p>${item.count }:&nbsp;${s }</p> -->
				<p>${s }</p>
			</c:forEach>
		</div>
		<div id="footer" class="footer">
			
		</div>
	</div>
</body>
</html>

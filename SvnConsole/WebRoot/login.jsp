<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="login.do" method="post" style="width:300px;height:200px;border:1px solid grey;margin:0 auto;padding:0 auto;">
    	<input type="hidden" name="loginOp" value="login"> 
    	<label>用户名：</label><input type="text" name="username"/><br/>
    	<br/>
    	<label>密&nbsp;&nbsp;&nbsp;码：</label><input type="password" name="pwd"/><br/>
    	<br/>
    	<button type="submit" value="login">login</button>
    </form>
  </body>
</html>

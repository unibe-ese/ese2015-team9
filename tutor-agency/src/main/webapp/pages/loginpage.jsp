<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/css/style.css" var="css" />

<!DOCTYPE HTML>
<html>
	<head>
		<title>ese2015-Team9-Login</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="login page" />
		<meta name="keywords" content="" />
		<link type="text/css" rel="stylesheet" href="${css}" />
	</head>

<body>


<div class="schatten">

<h1>
 
Login</h1><div id="login-error">
 
 
${error}</div><form action="<c:url value="/j_spring_security_check"></c:url>" method="post" >
<p>

<label for="j_username">Username</label>
<input id="j_username" name="j_username" type="text" />
</p><p>
 
<label for="j_password">Password</label>
<input id="j_password" name="j_password" type="password" />
</p><input  type="submit" value="Login"/>        
 
</form>

<div class="container">
    	<h1></h1>

  	<div class="stripe"></div>

    <div class="sidebar"></div>
    
    <div class="footer">2015 &copy; All rights reserved | <a href="agb.html">AGB</a></div>
    
</div>

</div>
</body>
</html>
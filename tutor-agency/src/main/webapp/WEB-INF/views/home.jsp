<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/resources/style.css" var="css" />

<!DOCTYPE HTML>
<html>
	<head>
		<title>ese2015-Team9</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link type="text/css" rel="stylesheet" href="${css}" />
	</head>

<body>
<div class="schatten">

<div class="header">
    <img src="<c:url value="/resources/header.jpg"/>">
       	<div id="nav">
           	<ul>
               	<li class="main"><a href="index.html">Home</a></li>
                <li class="main"><a href="info.html">Infos</a></li>
                <li class="main"><a href="ssearch.html">Suchen</a>
                   	<ul>
                   		<li class="sub"><a href="ssearch.html">Einfache Suche</a></li>
                    	<li class="sub"><a href="asearch.html">Erweiterte Suche</a></li>
                    </ul>
                   	</li>
                <li class="main"><a href="register.html">Registrieren</a></li>
                <li class="main"><a href="faq.html">FAQs</a></li>
            </ul>
        </div>       
</div>


<div class="container">
    	<h1></h1>

  	<div class="stripe"></div>

    <div class="sidebar"></div>
    
    <div class="footer">2015 &copy; All rights reserved | <a href="agb.html">AGB</a></div>
    
</div>

</div>
</body>
</html>



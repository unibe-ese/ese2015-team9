<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>




<spring:url value="./css/style.css" var="css" />

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
	<div class="schatten"></div>

<!-- 
	A different navigation is created according to the access-role of the user. 
-->

	<div class="header">
		<img src="<c:url value="/pages/template/header.jpg"/>">
		<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
		<c:choose>
			<c:when test="${loggedIn}">
				<div id="nav">
					<ul>
						<li class="main"><a href="index">Home</a>
							<ul>
								<li class="sub"><a href="info">Informations</a></li>
								<li class="sub"><a href="faq">FAQ's</a></li>
							</ul></li>
						<li class="main"><a href="">Suchen</a>
							<ul>
								<li class="sub"><a href="ssearch">Einfache Suche</a></li>
								<li class="sub"><a href="asearch">Erweiterte Suche</a></li>
							</ul></li>
						<li class="main"><a href="profile">Profile</a></li>
						<li class="main"><a
							href="<c:url value="/j_spring_security_logout"></c:url>">
								Logout</a></li>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<div id="nav">
					<ul>
						<li class="main"><a href="index">Home</a></li>
						<li class="main"><a href="info">Infos</a>
							<ul>
								<li class="main"><a href="faq">FAQs</a></li>
							</ul></li>

						<li class="main"><a href="">Suchen</a>
							<ul>
								<li class="sub"><a href="ssearch">Einfache Suche</a></li>
								<li class="sub"><a href="asearch">Erweiterte Suche</a></li>
							</ul></li>
						<li class="main"><a href="register">Registrieren</a></li>
						<li class="main"><a href="login">Login</a></li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
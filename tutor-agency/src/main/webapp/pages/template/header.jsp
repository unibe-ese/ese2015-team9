<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>




<spring:url value="/css/style.css" var="css" />
<c:url value="/img/cog.svg" var="svg"/>

<!DOCTYPE HTML>
<html>
<head>
<title>ese2015-Team9</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link type="text/css" rel="stylesheet" href="${css}" />
<link type="text/css" rel="stylesheet"
	href="/tutor-agency/css/searchForm.css" />
</head>

    <body>



        <div id="wrapper">

            <header>
                <div class="img"><h1><span>Tutorium</span></h1>
                    <h2><span>learning by learning</span></h2>
                    <h2><span>guaranteed no shortcuts</span></h2>
                    <h2><span>get ready to read some books</span></h2></div>
		<form action="search" method="GET" class="searchForm">
			<input type="search" name="text" /> <input type="submit"
				value="Search" />
		</form>
            </header>
            
                        <!-- 
    A different navigation is created according to the access-role of the user. 
            -->

            <sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
            <c:choose>
                <c:when test="${loggedIn}">
                    <style>header{display: none;}</style>
                    <nav>
                        <ul class="navigation">

                            <li><a href="<c:url value="/index" />">Home</a></li>
                            <li><a href="<c:url value="/quicksearch" />">Suche</a></li>
                            <li class="flex-fill"></li>
                            <li class="nav-user"><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
                            <li><div class="nav-svg"><a href="<c:url value="/profile" />"><%@include file="/img/cog.svg" %></a></div>

                        </ul>
                    </nav>

                </c:when>
                <c:otherwise>
                    <nav>
                        <ul class="navigation">

                            <li><a href="<c:url value="/index" />">Home</a></li>
                            <li><a href="<c:url value="/quicksearch" />">Suche</a></li>
                            <li><a href="<c:url value="/register#register" />">Registrieren</a></li>
                            <li class="flex-fill"></li>
                            <li><a href="<c:url value="/login" />">Login</a></li>
<!--                            <li><div class="nav-svg"><%@include file="/img/cog.svg" %>
                                    <label class="collapse" for="cogmenu"><%@include file="/img/cog.svg" %></label>
                                    <input id="cogmenu" type="checkbox">
                                    <div class="nav-ext">Content 1</div>
                                </div>
                            </li>-->

                        </ul>
                    </nav>
                </c:otherwise>
            </c:choose>

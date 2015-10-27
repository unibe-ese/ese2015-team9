<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
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
<div class="schatten">

<div class="header">
    <img src="<c:url value="/pages/template/header.jpg"/>">
       	<div id="nav">
           	<ul>
               	<li class="main"><a href="index">Home</a></li>
                <li class="main"><a href="info">Infos</a>
                	<ul>
                		<li class="main"><a href="faq">FAQs</a></li>
                	</ul>
                </li>
                
                <li class="main"><a href="">Suchen</a>
                   	<ul>
                   		<li class="sub"><a href="ssearch">Einfache Suche</a></li>
                    	<li class="sub"><a href="asearch">Erweiterte Suche</a></li>
                    </ul>
                   	</li>
                <li class="main"><a href="register">Registrieren</a></li>
                <li class="main"><a href="login">Login</a></li>
                
            </ul>
        </div>       
</div>


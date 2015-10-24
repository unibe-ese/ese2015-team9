<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ESE2015-Team9 | Index</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="schatten">

		<div class="header">
			<img src="img/header2.jpg" alt="headerImg" />
			<div id="nav">
				<ul>
					<li class="main"><a href="index.html">Home</a></li>
					<li class="main"><a href="info.html">Infos</a></li>
					<li class="main"><a href="ssearch.html">Suchen</a>
						<ul>
							<li class="sub"><a href="ssearch.html">Einfache Suche</a></li>
							<li class="sub"><a href="asearch.html">Erweiterte Suche</a></li>
						</ul></li>
					<li class="main"><a href="register.html">Registrieren</a></li>
					<li class="main"><a href="faq.html">FAQs</a></li>
				</ul>
			</div>
		</div>


		<div class="container">
			<h1>Mein Profil</h1>
			<br /> <br />
			<table class="forms">
				<tr>
					<td class="profil"><img src="img/profil.png" /></td>
					<td>
						<table class="forms">
							<tr>
								<td style="width: 150px"><strong>Vorname:</strong></td>
								<td>${member.firstName}</td>
							</tr>
							<tr>
								<td><strong>Nachname:</strong></td>
								<td>${member.lastName}</td>
							</tr>
							<tr>
								<td><strong>Nickname:</strong></td>
								<td>${member.username}</td>
							</tr>
							<tr>
								<td><strong>e-Mail:</strong></td>
								<td>${member.email}</td>
							</tr>
							<tr>
								<td><strong>Ist Tutor:</strong></td>
								<td>${member.isTutor}</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<div class="stripe"></div>
			<div class="sidebar">
				<h2>Hallo ${member.username}</h2>
				<br /> <br />
				<a href="profile">Profil ansehen</a> <br />
				<a href="edit.html">Profil bearbeiten</a>
				<form:form>
				<input type="submit" value="becomeTutor" name="werde Tutor"/></form:form>
				<br />
				<a href="logout.html">Logout</a> <br /> <br /> <br />
				<a href="delete.html">Profil löschen</a>
			</div>
			<div class="footer">
				2015 &copy; All rights reserved | <a href="agb.html">AGB</a>
			</div>
		</div>



	</div>
</body>
</html>




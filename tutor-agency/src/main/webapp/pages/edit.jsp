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
					<li class="main"><a
						href="<c:url value="/j_spring_security_logout"></c:url>">
							Logout</a></li>
					<li class="main"><a href="faq.html">FAQs</a></li>
				</ul>
			</div>
		</div>

		<div class="container">

			<form:form action="save" id="editForm" modelAttribute="editForm"
				method="post">
				<h1>Mein Profil</h1>
				<br />
				<br />
				<table class="forms">
					<tr>


						<td class="profil"><img src="img/profil.png" /></td>
						<td>
							<table class="forms">

								<tr>
									<td>Vorname:<sup>*</sup></td>
									<td><form:errors path="firstName"
											cssStyle="color: #ff0000;" element="span" /> <form:input
											path="firstName" type="text"
											name="vorname" value="${signupForm.firstName}" /></td>
								</tr>
								<tr>
									<td>Nachname:<sup>*</sup></td>
									<td><form:errors path="lastName"
											cssStyle="color: #ff0000;" element="span" /> <form:input
											path="lastName"  type="text"
											name="nachname" value="${signupForm.lastName}" /></td>
								</tr>

								<tr>
									<td>Nickname:<sup>*</sup></td>
									<td><form:errors path="username"
											cssStyle="color: #ff0000;" element="span" /> <form:input
											path="username" type="text" name="nickname"
											value="${signupForm.username}" /></td>
								</tr>
								<tr>
									<td>E-mail:<sup>*</sup></td>
									<td><form:errors path="email" cssStyle="color: #ff0000;"
											element="span" /> <form:input path="email"
											onclick="this.value=''" type="text" name="email"
											value="${signupForm.email}" /></td>
								</tr>
								<tr>
									<td>Altes Passwort:<sup>*</sup></td>
									<td><form:errors path="oldPassword"
											cssStyle="color: #ff0000;" element="span" /> <form:input
											path="oldPassword" type="password" name="pw" /></td>
								</tr>
								<tr>
									<td>Neues Passwort:<sup>*</sup></td>
									<td><form:errors path="password"
											cssStyle="color: #ff0000;" element="span" /> <form:input
											path="password" onclick="this.value=''" type="password"
											name="pw" /></td>
								</tr>
								<tr>
									<td>Neues Passwort bestätigen:<sup>*</sup></td>
									<td><form:errors path="passwordConfirm"
											cssStyle="color: #ff0000;" element="span" /> <form:input
											path="passwordConfirm" type="password" name="Passwort2" /></td>
								</tr>
								<tr>

								</tr>
							</table>
						</td>
					</tr>
				</table>
				<input class="submitbutton" type="submit" value="Save"
					style="margin-left: 400px;" />
			</form:form>
			<div class="stripe"></div>
			<div class="sidebar">
				<h2>Hallo ${member.username}</h2>
				<br /> <br /> <a href="profile">Profil ansehen</a> <br /> <a
					href="edit.html">Profil bearbeiten</a>
				<form:form action="becomeTutor">
					<input type="submit" value="werde Tutor" name="werde Tutor" />
				</form:form>
				<br /> <a href="logout.html">Logout</a> <br /> <br /> <br /> <a
					href="delete.html">Profil löschen</a>
			</div>
			<div class="footer">
				2015 &copy; All rights reserved | <a href="agb.html">AGB</a>
			</div>
		</div>

	</div>

</body>
</html>




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
			<h1>Anmelden</h1>
			<br />Alle mit einem <sup>*</sup> gekennzeichneten Felder müssen
			ausgefüllt werden. <br /> <br />
			<form:form modelAttribute="signupForm" action="create"
				id="signupForm" method="post">
				<table class="forms">
					<tr>
						<td>Vorname:<sup>*</sup></td>
						 <form:errors
								path="firstName" cssStyle="color: #ff0000;" element="span" />
						<td><form:input path="firstName" onclick="this.value=''"
								type="text" name="vorname" value="${signupForm.firstName}" /></td>
					</tr>
					<tr>
						<td>Nachname:<sup>*</sup></td>
						<td><form:errors
								path="lastName" cssStyle="color: #ff0000;" element="span" /><form:input path="lastName" onclick="this.value=''"
								type="text" name="nachname" value="${signupForm.lastName}" /> </td>
					</tr>

					<tr>
						<td>Nickname:<sup>*</sup></td>
						<td><form:errors
								path="username" cssStyle="color: #ff0000;" element="span" /><form:input path="username" onclick="this.value=''"
								type="text" name="nickname" value="${signupForm.username}" /> </td>
					</tr>
					<tr>
						<td>E-mail:<sup>*</sup></td>
						<td><form:errors
								path="email" cssStyle="color: #ff0000;" element="span" /><form:input path="email" onclick="this.value=''"
								type="text" name="email" value="${signupForm.email}" /> </td>
					</tr>
					<tr>
						<td>Passwort:<sup>*</sup></td>
						<td><form:errors
								path="password" cssStyle="color: #ff0000;" element="span" /><form:input path="password" onclick="this.value=''"
								type="password" name="pw" value="Passwort" /> </td>
					</tr>
					<tr>
						<td>Passwort bestätigen:<sup>*</sup></td>
						<td><form:errors path="passwordConfirm"
								cssStyle="color: #ff0000;" element="span" /><form:input path="passwordConfirm"
								onclick="this.value=''" type="password" name="Passwort2"
								value="Passwort2" /> </td>
					</tr>
				</table>
				<input type="checkbox" name="agb" style="width: 50px;" /> Ich habe
				die <a href="agb.html">AGB's</a> gelesen und bin damit
				einverstanden.<br />
				<input class="submitbutton" type="submit" value="Anmelden"
					style="margin-left: 400px;" />
				<br />
			</form:form>
			<br /> <br />

			<div class="stripe"></div>
			<div class="sidebar">
				<form action="profil.html" method="post">
					Nickname:<br /> <input onclick="this.value=''" type="text"
						name="login" value="Nickname"><br /> Password:<br /> <input
						onclick="this.value=''" type="password" name="password"
						value="Password" /><br /> <input class="submitbutton"
						type="submit" value="Login" /><br />
				</form>
				<div class="small">
					<a href="forgot.html" style="float: right; margin-right: 33px;">Passwort
						vergessen?</a>
				</div>
			</div>
			<div class="footer">
				2015 &copy; All rights reserved | <a href="agb.html">AGB</a>
			</div>
		</div>



	</div>
</body>
</html>



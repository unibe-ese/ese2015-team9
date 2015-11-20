<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />


		<div class="container">
			<h1>Anmelden</h1>
			<br />Alle mit einem <sup>*</sup> gekennzeichneten Felder müssen
			ausgefüllt werden. <br /> <br />
			<form:form modelAttribute="signupForm" action="create"
				id="signupForm" method="post">
				<table class="forms">
					<tr>
						<td>Vorname:<sup>*</sup></td>

						<td><form:errors path="firstName" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="firstName"
								onclick="this.value=''" type="text" name="vorname"
								value="${signupForm.firstName}" /></td>
					</tr>
					<tr>
						<td>Nachname:<sup>*</sup></td>
						<td><form:errors path="lastName" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="lastName"
								onclick="this.value=''" type="text" name="nachname"
								value="${signupForm.lastName}" /></td>
					</tr>

					<tr>
						<td>Nickname:<sup>*</sup></td>
						<td><form:errors path="username" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="username"
								onclick="this.value=''" type="text" name="nickname"
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
						<td>Passwort:<sup>*</sup></td>
						<td><form:errors path="password" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="password"
								onclick="this.value=''" type="password" name="pw"
								value="Passwort" /></td>
					</tr>
					<tr>
						<td>Passwort bestätigen:<sup>*</sup></td>
						<td><form:errors path="passwordConfirm"
								cssStyle="color: #ff0000;" element="span" /> <form:input
								path="passwordConfirm" onclick="this.value=''" type="password"
								name="Passwort2" value="Passwort2" /></td>
					</tr>
				</table>

				<form:errors path="readAGB" cssStyle="color: #ff0000;"
					element="span" />
				<br />
				<form:checkbox path="readAGB" name="agb" style="width: 50px;" />
					Ich habe die
					<a href="agb">AGB's</a> gelesen und bin damit einverstanden.
				<br />
				<input class="submitbutton" type="submit" value="Anmelden"
					style="margin-left: 400px;" />
				<br />
			</form:form>
			<br /> <br />

			<div class="stripe"></div>
			<div class="sidebar">
				<c:import url="template/sidebar.jsp" /></div>
				<c:import url="template/footer.jsp" />
			</div>
			

		</div>



	</div>
</body>
</html>



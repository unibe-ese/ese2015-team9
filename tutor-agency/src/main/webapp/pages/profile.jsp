<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />


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
						<td><strong>E-Mail:</strong></td>
						<td>${member.email}</td>
					</tr>
					<tr>
						<td><strong>Ist Tutor:</strong></td>
						<td>${member.isTutor}</td>
					</tr>
					<c:if test="${member.isTutor}">
						<tr>
							<td><strong>Preis für Nachhilfe:</strong>
							<td>${member.fee}</td>
						</tr>

						<tr>
							<td><strong>Alle Standorte:</strong></td>
							<td><select><c:forEach items="${member.universityList}" var="unis">
										<option value="${unis.name}"><c:out
												value="${unis.name}" /></option>
									</c:forEach></select></td>

						</tr>
					</c:if>
				</table>
			</td>
		</tr>
	</table>
	<form:form action="edit" method="get">
		<input class="submitbutton" type="submit" value="Bearbeite Profil"
			style="margin-left: 400px;" />
	</form:form>


	<div class="stripe"></div>
	<c:import url="template/sidebar_profile.jsp" />
	<c:import url="template/footer.jsp" />

</div>
</body>
</html>




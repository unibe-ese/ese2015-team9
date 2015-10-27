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
							<tr>
							</tr>
						</table> <form:form action="edit">
							<input class="submitbutton" type="submit" value="editProfile"
								style="margin-left: 400px;" />
						</form:form>
					</td>

				</tr>
			</table>


			<div class="stripe"></div>
			<div class="sidebar">
				<h2>Hallo ${member.username}</h2>
				<br /> <br /> <a href="profile">Profil ansehen</a> <br /> <a
					href="edit.html">Profil bearbeiten</a>
				<form:form action="becomeTutor">
					<input class="submitbutton" type="submit" value="werde Tutor"
						name="werde Tutor" />
				</form:form>
				<br /> <a href="logout.html">Logout</a> <br /> <br /> <br /> <a
					href="delete.html">Profil löschen</a>
			</div>
			<c:import url="template/footer.jsp" />

		</div>



	</div>
</body>
</html>




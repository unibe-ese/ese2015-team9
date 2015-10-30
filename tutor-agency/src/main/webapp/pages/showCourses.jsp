<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>


<div class="container">

	<table class="forms">
		<tr>
			<td>Kurs</td>
			<td>Universität</td>
			<td>Entfernen</td>
		</tr>
		<c:forEach items="${courses}" var="courses">
			<tr>
				<td><c:out value="${courses.name}"></c:out></td>
				<td><c:out value="${courses.university.name}"></c:out></td>
				<td align="center"><form
						onsubmit="return confirm('Willst du den Kurs: ${courses.name} wirklich entfernen?');"
						action="delete_${courses.id}" method="post">
						<input class="none" id="deleteIcon" type="image"
							src="img/delete-icon.png" name="delete-icon"
							style="height: 10px; width: 10px;" onClick="alert(test);">
					</form></td>
			</tr>
		</c:forEach>



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




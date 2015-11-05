<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="sidebar">
	<h2>Hallo ${member.username}</h2>
	<br /> <br /> <a href="profile">Profil ansehen</a> <br /> <a
		href="edit">Profil bearbeiten</a>
	<form:form action="becomeTutor">
		<input class="submitbutton" type="submit" value="werde Tutor"
			name="werde Tutor" />
	</form:form>
	<c:if test="${member.isTutor}">
		<form:form action="addCourse" method="get">
			<input class="submitbutton" type="submit" value="Kurs hinzufügen" />
		</form:form>
		<form:form action="show" method="get">
			<input class="submitbutton" type="submit" value="Zeige Kurse an" />
		</form:form>
	</c:if>
	<br /> <a href="logout.html">Abmelden</a> <br /> <br /> <br /> <a
		href="delete.html">Profil löschen</a>
</div>
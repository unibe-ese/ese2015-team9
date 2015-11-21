<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="sidebar">

	<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
	<c:choose>
		<c:when test="${loggedIn}">
			<h2>Hallo ${member.username}</h2>
			<br />
			<br />
			<form:form action="profile" method="get">
				<input class="submitbutton" type="submit" value="Profil ansehen" />
			</form:form>
			<br />
			<form:form action="edit" method="get">
				<input class="submitbutton" type="submit" value="Profil bearbeiten" />
			</form:form>
			<c:if test="${not member.isTutor }">
				<form:form action="becomeTutor">
					<input class="submitbutton" type="submit" value="werde Tutor"
						name="werde Tutor" />

				</form:form>
			</c:if>
			
			<c:if test="${member.isTutor}">
				<form:form action="addOffer" method="get">
					<input class="submitbutton" type="submit" value="Kurs hinzufügen" />
				</form:form>
				
			</c:if>
			
			<br />
			<a class="submitbutton" href="<c:url value="/j_spring_security_logout"></c:url>">Abmelden</a>
		</c:when>
		<c:otherwise>
			<c:import url="template/loginform.jsp" />
		</c:otherwise>
	</c:choose>


</div>
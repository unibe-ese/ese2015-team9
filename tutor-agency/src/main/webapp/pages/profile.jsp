<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />


<div class="container">
	<h1>Profile</h1>
	
    <%--<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
	<c:choose>
		<c:when test="${loggedIn}">
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
				<form:form action="addCourse" method="get">
					<input class="submitbutton" type="submit" value="Kurs hinzufügen" />
				</form:form>
				<form:form action="showCourses" method="get">
					<input class="submitbutton" type="submit" value="Zeige Kurse an" />
				</form:form>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:import url="template/loginform.jsp" />
		</c:otherwise>
	</c:choose>--%>
    
	<table class="forms" >
		<tr>
			<th style="text-align: left">Account Informations</th>
			<c:if test="${member.isTutor}">
				<th style="text-align: left">Tutoring</th>
			</c:if>
		</tr>
		<tr>
			<td>
				<table class="forms">
					<tr>
						<td colspan="2">
							<hr>
						</td>
					</tr>
					<tr>
						<td style="min-width:35px"><strong>Username:</strong></td>
						<td>${member.username}</td>
					</tr>
					<tr>
						<td><strong>First Name:</strong></td>
						<!-- style="width: 150px" -->
						<td>${member.firstName}</td>
					</tr>
					<tr>
						<td><strong>Last Name:</strong></td>
						<td>${member.lastName}</td>
					</tr>

					<tr>
						<td><strong>E-Mail:</strong></td>
						<td>${member.email}</td>
					</tr>

				</table>
			</td>

			<!-- Settings for Tutoring Informations-->
			<c:if test="${member.isTutor}">
				<td>
					<table class="forms">
						<tr>
							<td colspan="2">
								<hr>
							</td>
						</tr>
						<tr>
							<td><strong>Activated</strong>
							<td>${member.isActivated}</td>
						</tr>
						<c:if test="${member.isTutor}">
							<tr>
								<td><strong>Fee:</strong>
								<td>${member.fee}</td>
							</tr>

							<tr>
								<td><strong>Locations:</strong></td>
								<td>
									<ol>
										<c:forEach items="${member.universityList}" var="unis">
											<li>${unis.name}</li>
										</c:forEach>
									</ol>
							</tr>
						</c:if>
					</table>

				</td>
			</c:if>
		</tr>
	</table>
	<hr>
	<br>
	<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
	<c:choose>
		<c:when test="${loggedIn}">
			<c:import url="fragments/offerTablePrivate.jsp" />
		</c:when>
	</c:choose>

	<br>
	<h3>Subscriptions:</h3>
	<hr>
		<ul><c:forEach items="${subscriptions}" var="subscription">
			<li>course : ${subscription.course.name} , tutor:
				${subscription.tutor.username}</li>
		</c:forEach>
		</ul>
	
    </div>
</div>
</div>
    <c:import url="template/footer.jsp" />
</body>
</html>




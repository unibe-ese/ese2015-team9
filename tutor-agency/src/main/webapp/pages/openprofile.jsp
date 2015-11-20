<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />

<div class="container">
	<h1>
		<c:out value="${member.username}"></c:out>
	</h1>
	<br /> <br />
	<table class="forms">
		<tr>
			<td class="profil"><img src="img/profil.png" /></td>
			<td>
				<table class="forms">
					<tr>
						<td><strong>Nickname:</strong></td>
						<td>${member.username}</td>
					</tr>
					<c:if test="${member.isTutor}">
						<tr>
							<td><strong>Preis für Nachhilfe:</strong>
							<td>${member.fee}</td>
							
						<tr>
						
						<td><strong>Locations:</strong></td>
								<td>
									<ol>
										<c:forEach items="${member.universityList}" var="unis">
											<li>${unis.name}</li>
										</c:forEach>
									</ol>

						
						
						</tr>

						<%-- <tr>
							<td><strong>Alle Standorte:</strong></td>
							<td><select><c:forEach items="${unis}" var="unis">
										<option value="${unis.name}"><c:out
												value="${unis.name}" /></option>
									</c:forEach></select></td>

						</tr> --%>
					</c:if>
				</table>
			</td>
		</tr>
	</table>

	<div class="sidebar">
		
		<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
		
	<c:if test="${loggedIn}">
			<p>Contact</p>
	</c:if>
</div>
		<%-- <c:choose>
			<c:when test="${memberAtHome}">
				<c:choose>
					<c:when test="${member.isTutor}">
						<a href="edit">Edit Profile</a>
						<a href="/show">Courses</a>
						<a href="delete">Delete Profile</a>
						<br />
						<form:form action="show" method="get">
							<input class="submitbutton" type="submit" value="show Courses" />
						</form:form>
						<form:form action="addCourse" method="get">
						<input class="submitbutton" type="submit" value="add a Course" />
					</form:form>
					</c:when>
					<c:otherwise>
						<form:form action="becomeTutor">
							<input class="submitbutton" type="submit" value="become tutor"
								name="become tutor" />
						</form:form>
					</c:otherwise>
				</c:choose>
				
				<a href="<c:url value="/j_spring_security_logout"></c:url>">Abmelden</a>
					
			</c:when>
			<c:otherwise>
				<a>Contact</a>
			</c:otherwise>
		</c:choose> --%>
	
	<c:import url="template/footer.jsp" />



	<hr>
	<table id="courses" >
		<tr class="title">
			<td>Course</td>
			<td>University</td>
			<%-- <c:if test="${!memberAtHome}">
				<td>Contact</td>
			</c:if> --%>
		</tr>
	
		<c:forEach items="${member.offers}" var="offer">
			<c:set var="course" value="${offer.course}"/>
				<tr>
					<td><c:out value="${course.name}"></c:out></td>
					<td><c:out value="${course.university.name}"></c:out></td>
					<%-- <c:if test="${!memberAtHome}">
						<td align="center" ><input class="none" id="emailIcon" type="image"
								src="img/email-icon.png" name="email-icon"
								style="height: 20px; width: 20px;" >
						</td>
					</c:if> --%>
					<%-- <td align="center"><form
							onsubmit="return confirm('Do you want to contact the tutor: ${member.username} ?');"
							action='mailto:<c:out value="${member.email}"></c:out>'
							method="post">
							<input class="none" id="emailIcon" type="image"
								src="img/email-icon.png" name="email-icon"
								style="height: 20px; width: 20px;" onClick="alert(test);">
						</form></td> --%>
				</tr>
			</c:forEach>
	</table>
</div>
</body>
</html>




<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<spring:url value="/css/style.css" var="css" />

<!-- OFFER TABLE -->

<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
<h2 style="font-weight: bold">Tutoring Offers</h2>
<br>
<table class="forms" id=courses>
	<thead>
		<tr>
			<th>Course</th>
			<th>University</th>
			<th>Tutor</th>
			<td>Fee</td>
			<th>Grade</th>
			<c:if test="${loggedIn}">
				<th>Subscribe</th>
			</c:if>
		</tr>
	</thead>
<c:forEach items="${offers}" var="offer">
	<tr>
		<td><c:out value="${offer.course.name}"></c:out></td>
		<td><c:out value="${offer.course.university.name}"></c:out></td>
		<td><a href="profileId=${offer.tutor.id}"> <c:out
					value="${offer.tutor.username}"></c:out></a></td>
		<td>${offer.tutor.fee}</td>
		<td>${offer.grade}</td>


		<c:if test="${loggedIn}">
			<!-- ADD CONTACT ICON -->

			<td align="center">
				<form
					onsubmit="return confirm('Do you want to request tutoring for ${offer.course.name} from ${offer.tutor.username}?');"
					action="subscribe/${offer.id}" 
					method="get">
					
					<input class="none" id="emailIcon" type="image"
						src="img/email-icon.png" name="email-icon"
						style="height: 10px; width: 10px;" onClick="alert(test);">
				</form></td>

		</c:if>
	</tr>
</c:forEach>
</table>


</body>
</html>




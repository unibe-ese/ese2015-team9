<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />
	<h2 style="font-weight:bold">Open Tutoring Offers</h2>
	<br>
	<table class="forms" id=courses>
	<thead>
	<tr>
			<th>Course</th>
			<th>University</th>
			<th>Grade</th>
			<th>Remove</th>
		</tr>
	</thead>
		
		<c:forEach items="${member.offers}" var="offer">
			<tr>
				<td><c:out value="${offer.course.name}"></c:out></td>
				<td><c:out value="${offer.course.university.name}"></c:out></td>
				<td><c:out value="${offer.grade}"></c:out></td>
				<td align="center"><form
						onsubmit="return confirm('Willst du den Kurs: ${offer.course.name} wirklich entfernen?');"
						action="delete_${offer.course.id}" method="post">
						<input class="none" id="deleteIcon" type="image"
							src="img/delete-icon.png" name="delete-icon"
							style="height: 10px; width: 10px;" onClick="alert(test);">
					</form></td>
			</tr>
			<c:forEach items="${offer.subscribers}" var="subscriber">
				<tr>
					<td colspan="3" style="text-align:center" >
						<c:out value="Tutoring Request from ${subscriber.username}"/>
					</td>
					<td> ACCEPT PLACEHOLDER</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>

</body>
</html>




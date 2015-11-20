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
			<td>Note</td>
			<td>Entfernen</td>
		</tr>
		<c:forEach items="${member.offer}" var="offer">
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
		</c:forEach>
	</table>

	<div class="stripe"></div>
	<c:import url="template/sidebar_profile.jsp" />
	<c:import url="template/footer.jsp" />

</div>

</body>
</html>



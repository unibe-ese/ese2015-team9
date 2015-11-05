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
		<tr class="title">
			<td>Tutor</td>
			<td>Universität</td>
			<td>Kurs</td>
			<td>Kontaktieren</td>
		</tr>
		<c:forEach items="${courses}" var="courses">
			<tr>
				<td><c:out value="${member.username}"></c:out></td>
				<td><c:out value="${courses.university.name}"></c:out>
				<td><c:out value="${courses.name}"></c:out></td>
				<td align="center"><form onsubmit="return confirm('Willst du den Tutor: ${member.username} wirklich kontktieren?');"
						action="mailto:<c:out value="${member.email}"></c:out>" method="post">
						<input class="none" id="emailIcon" type="image"
							src="img/email-icon.png" name="email-icon"
							style="height: 20px; width: 20px;" onClick="alert(test);">
					</form>
			</tr>
		</c:forEach>
	</table>

	<div class="stripe"></div>
	<c:import url="template/sidebar_profile.jsp" />
	<c:import url="template/footer.jsp" />

</div>

</body>
</html>



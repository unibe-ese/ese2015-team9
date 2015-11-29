<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  <script type="text/javascript">
	function changeVisibility() {
		var el = document.getElementById("courses");

		if (el.style.display == "block") {
			el.style.display = "none";
		} else {
			el.style.display = "block";
		}
	} 
</script> -->

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
				<td align="center" ><form
						onsubmit="return confirm('Willst du den Kurs: ${offer.course.name} wirklich entfernen?');"
						action="auth/offer/${offer.id}/delete" method="get">
						<input class="none" id="deleteIcon" type="image"
							src="img/delete-icon.png" name="delete-icon">
					</form></td>
			</tr>
			<c:forEach items="${offer.subscriptions}" var="subscription">
				<tr>
					<td colspan="3" style="text-align:center" >
						<c:out value="Tutoring Request from ${subscription.member.username}"/>
					</td>
					<c:if test="${!subscription.accepted}">
					<td> <a href="auth/offer/${offer.id}/accept/${subscription.id}">Accept</a></td></c:if>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>

</body>
</html>




<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	$("#universityList").change(function() {
		if ($(this).data('options') == undefined) {
			/*Taking an array of all options-2 and kind of embedding it on the select1*/
			$(this).data('options', $('#courseList option').clone());
		}
		var id = $(this).val();
		var options = $(this).data('options').filter('[value=' + id + ']');
		$('#courseList').html(options);
	});
</script>

<div class="container">

	<table class="forms">
		<tr>
			<td><form:form modelAttribute="addCourseForm"
					action="updateDropdown" method="post">
					<table class="forms">

						<tr>
							<td style="width: 150px"><strong>Universität:</strong></td>
							<td><form:select path="selectedUniversity"
									name="universityList" id="universityList"
									onchange="this.form.submit()">
									<c:forEach items="${universities}" var="universities">
										<option value="${universities.name}"><c:out
												value="${universities.name}" /></option>
									</c:forEach>
								</form:select></td>
						</tr>

					</table>
				</form:form> <form:form modelAttribute="addCourseForm" action="addCourse"
					id="addCourseForm" method="post">
					<table class="forms">
						<tr>
							<td style="width: 150px"><strong>Kurs:</strong></td>
							<td><form:select path="selectedCourse" name="courseList" id="courseList">
									<c:forEach items="${courses}" var="courses">
										<option value="${courses.name}"><c:out
												value="${courses.name}" /></option>
									</c:forEach>
							</form:select></td>
						</tr>

					</table>
					<br />
					<input class="submitbutton" type="submit" value="Hinzufügen"
						style="margin-left: 400px;" />
				</form:form></td>

		</tr>
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



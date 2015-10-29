<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />
<head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js">
	function test() {
		$("#universities").change(function() {
			if ($(this).data('options') == undefined) {
				/*Taking an array of all options-2 and kind of embedding it on the select1*/
				$(this).data('options', $('#courses option').clone());
			}
			var id = $(this).val();
			var options = $(this).data('options').filter('[value=' + id + ']');
			$('#courses').html(options);
		});
	};
</script>
</head>

<div class="container">
	<form:form modelAttribute="addCourseForm" action="create"
		id="addCourseForm" method="post">
		<table class="forms">
			<tr>
				<td>
					<table class="forms">
						<tr>
							<td style="width: 150px"><strong>Universität:</strong></td>
							<td><select id="universities" name="universities">
									<c:forEach items="${universities}" var="universities">
										<option value="${universities.name}" onClick="console.log('TEST');test();"><c:out
												value="${universities.name}" /></option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td style="width: 150px"><strong>Kurs:</strong></td>
							<td><select path="courses" id="courses" name="courses">
									<c:forEach items="${courses}" var="courses">
										<option value="${courses.university.name}"><c:out
												value="${courses.name}" /></option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<form:button>

							</form:button>
						</tr>

					</table>
				</td>

			</tr>
		</table>
	</form:form>


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




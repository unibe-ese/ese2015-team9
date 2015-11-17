<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />
<div class="container">

	<table class="forms">
		<tr>
			<td>
				<form:form modelAttribute="addCourseForm" action="addCourse"
					id="addCourseForm" method="post">
					<table class="forms">
						<tr>
							<td style="width: 150px"><strong>Kurs:</strong></td>
							<td><form:select path="selectedCourse" name="courseList" id="courseList">
									<c:forEach items="${courses}" var="courses">
										<option value="${courses.id}"><c:out
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
	<c:import url="template/sidebar_profile.jsp"></c:import>	
	<c:import url="template/footer.jsp" />

</div>

</body>
</html>




<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />
<div class="container">
<h2>Create a Tutoring Offer</h2>
	<table class="forms">
		<tr>
			<td><form:form modelAttribute="selectedUniversity"
					action="new" method="get">
					<table class="forms">
						<tr>
							<td style="width: 150px"><strong>Universität:</strong></td>
							
							<td><form:select path="id"
									name="universities" id="universityList"
									onchange="this.form.submit()">
									<c:forEach items="${universities}" var="university">
										<option value="${university.id}"
											<c:if test="${university.id == selectedUniversity.id}" >
											selected = true</c:if>>
											<c:out value="${university.name}" /></option>
									</c:forEach>
								</form:select></td>
						</tr>

					</table>
					
				</form:form> <form:form modelAttribute="offerForm" action="new"
					method="post">
					<tr>
						<td>
							<table class="forms">
								<tr>
									<td style="width: 150px"><strong>Course:</strong></td>
									<td>
									<form:errors path="courseId" cssStyle="color: red;"
								element="span" />
									<form:select path="courseId" name="courseList"
											id="courseList">
											<c:forEach items="${courses}" var="course">
												<option value="${course.id}"><c:out
														value="${course.name}" /></option>
											</c:forEach>
										</form:select></td>
								</tr>
								<tr>
									<td style="width: 150px"><strong>Note:</strong></td>
									<td>
									<form:errors path="grade" cssStyle="color: red;"
								element="span" />
									<form:select path="grade" name="grade"
											id="universityList">
											<c:forEach items="${grades}" var="grade">
												<option value="${grade}"><c:out value="${grade}" /></option>
											</c:forEach>
										</form:select></td>
								</tr>
							</table> <br /> <input class="submitbutton" type="submit"
							value="Hinzufügen" style="margin-left: 400px;" />
						</td>

					</tr>

				</form:form>
	</table>



</div>
</div>
	<c:import url="template/footer.jsp" />

</div>

</body>
</html>




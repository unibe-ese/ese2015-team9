<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

<h1>Suchen</h1>
<br>
<br>
	<table class="forms">
		<tr>
			<td><form:form modelAttribute="searchForm"
					action="updateSearchDropdown" method="post">
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
				</form:form> <form:form modelAttribute="searchForm" action="searchCourse"
					id="searchForm" method="get">
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
					<input class="submitbutton" type="submit" value="Suchen"
						style="margin-left: 400px;" />
				</form:form></td>

		</tr>
	</table>
	
<div class="stripe"></div>
<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
<c:choose>
    <c:when test="${loggedIn}">
		<c:import url="template/sidebar_profile.jsp" />
    </c:when>
    <c:otherwise>
		<c:import url="template/sidebar_profile.jsp" />    
	</c:otherwise>
</c:choose>

    
<c:import url="template/footer.jsp" />
    
</div>

</div>
</body>
</html>
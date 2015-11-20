<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />

<script type="text/javascript">
	function changeVisibilityOfFilters() {
		var el = document.getElementById("filters");

		if (el.style.display == "block") {
			el.style.display = "none";
		} else {
			el.style.display = "block";
		}
	}
</script>

<div class="container">
	<%-- <div>
		<form:form modelAttribute="searchForm" action="showResults"
			method="post" id="searchForm">
			<form:input path="searchText" type="text"></form:input>
			<input class="submitbutton" type="submit" value="Search"
				style="margin-right: 50px;" />
				<br>
		</form:form>

	</div> --%>
	<form:form modelAttribute="form" action="showFilteredResults"
		method="post" id="form">
		<table class="forms">
			<tr>
				<td><form:input path="searchText" type="text" /></td>
			</tr>
			<tr>
				<td><label class="checkboxlabel"> <form:checkbox
							class="checkbox" path="filtered" onclick="changeVisibilityOfFilters()"
							id="filtered" />advanced
				</label></td>

			</tr>
			<tr>
				<td>
					
				</td>
			</tr>

		</table>
		<hr>
					<div id="filters" style="display: none">
						<form:checkboxes class="checkbox" items="${universities}" path="universityNames" />
						<form:input path="minFee" type="number"></form:input>
						<form:input path="maxFee" value="${form.maxFee }" type="number"></form:input>
					</div>

<input class="submitbutton" type="submit" value="Search"
				style="margin-right: 50px;" />

	</form:form>
<form action="search" method = "GET"><input type="text" name="text" />
<input type="submit" value="Submit" /></form>


	<hr>
	<table class="forms">
		<tr class="title">
			<td>Course</td>
			<td>University</td>
			<td>Tutor</td>
			<!-- <td>Contact</td> -->
		</tr>
		<c:forEach items="${searchResults}" var="result">
			<c:forEach items="${result.members}" var="member">
				<tr>
					<td><c:out value="${result.course.name}"></c:out></td>
					<td><c:out value="${result.course.university.name}"></c:out></td>
					<td><a href="profileId=${member.id}"><c:out
								value="${member.username}"></c:out></a></td>
					<%-- <td align="center"><form
							onsubmit="return confirm('Do you want to contact the tutor: ${member.username} ?');"
							action='mailto:<c:out value="${member.email}"></c:out>'
							method="post">
							<input class="none" id="emailIcon" type="image"
								src="img/email-icon.png" name="email-icon"
								style="height: 20px; width: 20px;" onClick="alert(test);">
						</form></td> --%>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>

	<%-- <div class="stripe"></div>

	<div class="sidebar">
		<c:import url="template/sidebar.jsp" />
	</div> --%>
	<c:import url="template/footer.jsp" />

</div>
<body></body>
<html></html>



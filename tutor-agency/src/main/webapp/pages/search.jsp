<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" rel="stylesheet" href="/tutor-agency/css/searchForm.css" />

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
	<form:form modelAttribute="form" action="searchresults" method="post"
		 class="searchForm">
			<p>
			<form:input path="searchText" type="search" onclick="this.value=''" value="enter a course name..."/>
			<input type="submit" value="Search"/>
			</p>
				<fieldset>
					<legend>
						<form:checkbox
							class="checkbox" path="filtered" onclick="changeVisibilityOfFilters()"
							id="filtered" label="filters" labelposition="left"/>
					</legend>
					<div id="filters" style="display: none">
						<table>
							<tr><td style="border-style:none">
							<fieldset class="filter">
							<legend>Universities</legend>
							<div class="scrollList">
							<form:checkboxes class="checkbox" items="${universities}" path="universityNames" labelposition="left"/>
							</div>	
						</fieldset>
							</td>
							<td style="border-style:none">
							<fieldset class="filter">
					<legend>Fee</legend>
						<label>Min: <form:input path="minFee" type="number" value="${form.minFee }"></form:input></label>
						<br>
						<label>Max: <form:input path="maxFee" type="number" value="${form.maxFee }" ></form:input></label>
						</fieldset>
						</td>
						</tr>
						</table>					
					</div>			
				</fieldset>
		</form:form>
		<!-- SEARCH-RESULT-TABLE -->
	<hr>
	<div style="overflow:auto; margin-bottom:20px;">
	<table id=courses>
		<tr class="title">
			<td>Course</td>
			<td>University</td>
			<td>Tutor</td>
			<td>Fee [CHF]</td>
			<!-- <td>Contact</td> -->
		</tr>
		<c:forEach items="${searchResults}" var="result">
			<c:forEach items="${result.members}" var="member">
				<tr>
					<td><c:out value="${result.course.name}"></c:out></td>
					<td><c:out value="${result.course.university.name}"></c:out></td>
					<td><a href="profileId=${member.id}"><c:out
								value="${member.username}"></c:out></a></td>
					<td>${member.fee}</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
</div>
	
<c:import url="template/footer.jsp" />

</div>
<body></body>
<html></html>



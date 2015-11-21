<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" rel="stylesheet"
	href="/tutor-agency/css/searchForm.css" />

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
	<form:form modelAttribute="form" action="search" method="post"
		class="searchForm">
		<p>
			<form:input path="searchText" type="search" onclick="this.value=''"
				value="enter a course name..." />
			<input type="submit" value="Search" />
		</p>
		<fieldset>
			<legend>
				<form:checkbox class="checkbox" path="filtered"
					onclick="changeVisibilityOfFilters()" id="filtered" label="filters"
					labelposition="left" />
			</legend>
			<div id="filters" style="display: none">
				<table>
					<tr>
						<td style="border-style: none">
							<fieldset class="filter">
								<legend>Universities</legend>

								<div class="scrollList">

									<form:checkboxes class="checkbox" items="${universities}"
										path="universityNames" labelposition="left" />
								</div>

							</fieldset>
						</td>
						<td style="border-style: none">
							<fieldset class="filter">
								<legend>Fee</legend>
								<label>Min: <form:input path="minFee" type="number"
										value="${form.minFee }"></form:input></label> <br> <label>Max:
									<form:input path="maxFee" type="number" value="${form.maxFee }"></form:input>
								</label>
							</fieldset>
						</td>
						<td style="border-style: none">
							<fieldset class="filter">
								<legend>Grade</legend>
								<label>Min: <form:select path="minGrade"
										items="${grades}">
									</form:select></label>
							</fieldset>
					</tr>
				</table>
			</div>
		</fieldset>
	</form:form>
	<br>
	<!-- IMPORT TABLE -->
	<c:import url="fragments/offerTablePublic.jsp"/>

	<c:import url="template/footer.jsp" />

</div>
<body></body>
<html></html>



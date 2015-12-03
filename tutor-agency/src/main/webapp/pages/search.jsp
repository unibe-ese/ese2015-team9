<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/search.css" var="search" />

<link type="text/css" rel="stylesheet" href="${search}" />

<!--<script type="text/javascript">
	function changeVisibilityOfFilters() {
		var el = document.getElementById("filters");

		if (el.style.display == "block") {
			el.style.display = "none";
		} else {
			el.style.display = "block";
		}
	}
</script>-->

<div class="container" id="search">
	<form:form modelAttribute="form" action="search#result" method="post"
		class="searchForm">								
		<form:input path="searchText" type="search" onclick="this.value=''"
			value="" placeholder = "enter a course name"/>
		<input class="searchbutton" type="submit" value="Search" />

		<fieldset>
			<form:checkbox class="checkbox" path="filtered" id="filtered" />
			<label class="collapse" for="filtered"><span class="labelblock"></span>
                <p>Show filters</p></label>
			<div id="filters">
				<fieldset class="filter">
					<legend>Universities</legend>

					<div class="uni-flex">
                        <c:forEach items="${universities}" var="current" >
                            <div class="uni-flex-item">
                            <form:checkbox id="${current}" class="uni-checkbox" path="universityNames" value="${current}" />
                            <label for="${current}"><span class="labelblock"></span><p><c:out value="${current}" /></p></label>
                            </div>
                        </c:forEach>
					</div> 
                    <%-- <form:checkboxes class="checkbox" items="${universities}"
                                     path="universityNames"/> --%>

				</fieldset>

				<fieldset class="filter">
					<legend>Fee</legend>
					<label>Min: </label>
					<form:input path="minFee" type="number" value="${form.minFee }"></form:input>
					<br> <label>Max:</label>
					<form:input path="maxFee" type="number" value="${form.maxFee }"></form:input>
				</fieldset>

				<fieldset class="filter">
					<legend>Grade</legend>
					<label>Min: </label>
					<form:select path="minGrade" items="${grades}">
					</form:select>
				</fieldset>
			</div>
		</fieldset>
	</form:form>
	<div id="result">
		<!-- IMPORT TABLE -->
		<c:import url="fragments/offerTablePublic.jsp" />
	</div>

</div>
</div>
<c:import url="template/footer.jsp" />
</body>
</html>



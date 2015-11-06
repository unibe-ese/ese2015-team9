<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />

<div class="container">


	<h1>QuickSearch</h1>
	<br>
	<div>
		<form:form modelAttribute="searchForm" action="quickResults"
			method="post" id="searchForm">
			<form:input path="searchText" type="text"></form:input>
			<input class="submitbutton" type="submit" value="Search"
				style="margin-left: 400px;" />
		</form:form>

	</div>

	<table class="forms">
		<tr class="title">
			<td>Tutor</td>
			<td>Universität</td>
			<td>Kurs</td>
			<td>Kontaktieren</td>
		</tr>
		<c:forEach items="${searchResults}" var="result">
			<c:forEach items="${result.members}" var="member">
				<tr>
					<td><c:out value="${member.username}"></c:out></td>
					<td><c:out value="${result.course.university.name}"></c:out></td>
					<td><c:out value="${result.course.name}"></c:out></td>
					<td align="center"><form
							onsubmit="return confirm('Willst du den Tutor: ${member.username} wirklich kontktieren?');"
							action='mailto:<c:out value="${member.email}"></c:out>'
							method="post">
							<input class="none" id="emailIcon" type="image"
								src="img/email-icon.png" name="email-icon"
								style="height: 20px; width: 20px;" onClick="alert(test);">
						</form></td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>

	<div class="stripe"></div>

	<div class="sidebar">
		<c:import url="template/sidebar.jsp" />
	</div>
	<c:import url="template/footer.jsp" />





</div>
<body></body>
<html></html>



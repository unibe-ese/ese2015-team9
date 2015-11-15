<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />

<div class="container">
	<div>
		<form:form modelAttribute="searchForm" action="showResults"
			method="post" id="searchForm">
			<form:input path="searchText" type="text"></form:input>
			<input class="submitbutton searchbutton" type="submit" value="Search"/>
				<br>
		</form:form>

	</div>

	<table>
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
					<td><a href="profileId=${member.id}"><c:out value="${member.username}"></c:out></a></td>
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
	
</div>
</div>
    <c:import url="template/footer.jsp" />
</body>
</html>



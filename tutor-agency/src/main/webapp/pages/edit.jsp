<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="template/header.jsp" />
<spring:url value="/css/edit.css" var="css" />

<link type="text/css" rel="stylesheet" href="${css}" />


<div class="container">

	<h1>My Profile</h1>

	<form:form action="save" id="editForm" modelAttribute="editForm"
		method="post" cssClass="forms">

		<label><p>First Name</p></label>
		<form:errors path="firstName" cssClass="error" element="p" />
		<form:input path="firstName" type="text" name="vorname"
			value="${editForm.firstName}" />

		<label><p>Last Name</p></label>
		<form:errors path="lastName" cssClass="error" element="p" />
		<form:input path="lastName" type="text" name="nachname"
			value="${editForm.lastName}" />

		<label><p>Username</p></label>
		<form:errors path="username" cssClass="error" element="p" />
		<form:input path="username" type="text" name="nickname"
			value="${editForm.username}" />

		<label><p>Email</p></label>
		<form:errors path="email" cssClass="error" element="p" />
		<form:input path="email" type="text" name="email"
			value="${editForm.email}" />

		<label><p>Description</p></label>
		<form:errors path="description" cssClass="error" element="p" />
        <form:textarea path="description" type="textfield" name="description"
			value="${editForm.description}"/>

		<c:if test="${member.isTutor}">
			<label><p>Fee</p></label>
			<form:errors path="fee" cssClass="error" element="p" />
			<form:input path="fee" type="text" name="fee" value="${editForm.fee}" />

			<input type="checkbox" id="showUnis" />
			<label class="collapse" for="showUnis"><span
				class="labelblock"></span>
				<p>Select Universities</p></label>
			<fieldset>
				<div class="uni-flex">
					<c:forEach items="${universityChoices}" var="current">
						<c:set var="contains" value="false" />
						
						<!-- Check whether the user already has saved these locations -->
						<c:forEach items="${alreadySelectedUniversities}" var="alreadySelected">
							<c:if test="${alreadySelected == current}">
								<c:set var="contains" value="true" />
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${contains}">

								<div class="uni-flex-item">
									<form:checkbox id="${current}" class="uni-checkbox"
										path="universities" value="${current}" checked='true' />
									<label for="${current}"><span class="labelblock"></span>
									<p>
											<c:out value="${current}" />
										</p></label>
								</div>


							</c:when>
							<c:otherwise>
								<div class="uni-flex-item">
									<form:checkbox id="${current}" class="uni-checkbox"
										path="universities" value="${current}" />
									<label for="${current}"><span class="labelblock"></span>
									<p>
											<c:out value="${current}" />
										</p></label>
								</div>

							</c:otherwise>
						</c:choose>

					</c:forEach>
				</div>
			</fieldset>
		</c:if>

		<label><p>Old password</p></label>
		<form:errors path="oldPassword" cssClass="error" element="p" />
		<form:input path="oldPassword" type="password" name="pw" />

		<label><p>New password</p></label>
		<form:errors path="password" cssClass="error" element="p" />
		<form:input path="password" onclick="this.value=''" type="password"
			name="pw" />

		<label><p>Confirm new password</p></label>
		<form:errors path="passwordConfirm" cssClass="error" element="p" />
		<form:input path="passwordConfirm" type="password" name="Passwort2" />

		<input class="submitbutton" type="submit" value="Save" />
	</form:form>


</div>
</div>
<%-- Do not delete this div --%>
<c:import url="template/footer.jsp" />
</body>
</html>




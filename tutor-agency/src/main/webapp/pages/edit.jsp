<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />


<div class="container">

	<script type="text/javascript">
		function changeVisibility() {
			var el = document.getElementById("universitySelection");

			if (el.style.display == "block") {
				el.style.display = "none";
			} else {
				el.style.display = "block";
			}
		}
	</script>

	<form:form action="save" id="editForm" modelAttribute="editForm"
		method="post">
		<h1>Mein Profil</h1>
		<br />
		<br />
		<table class="forms">
			<tr>

				<td>
					<table class="forms">

						<tr>
							<td>Vorname:<sup>*</sup></td>
							<td><form:errors path="firstName" cssStyle="color: #ff0000;"
									element="span" /> <form:input path="firstName" type="text"
									name="vorname" value="${editForm.firstName}" /></td>
						</tr>
						<tr>
							<td>Nachname:<sup>*</sup></td>
							<td><form:errors path="lastName" cssStyle="color: #ff0000;"
									element="span" /> <form:input path="lastName" type="text"
									name="nachname" value="${editForm.lastName}" /></td>
						</tr>

						<tr>
							<td>Nickname:<sup>*</sup></td>
							<td><form:errors path="username" cssStyle="color: #ff0000;"
									element="span" /> <form:input path="username" type="text"
									name="nickname" value="${editForm.username}" /></td>
						</tr>
						<tr>
							<td>E-mail:<sup>*</sup></td>
							<td><form:errors path="email" cssStyle="color: #ff0000;"
									element="span" /> <form:input path="email"
									onclick="this.value=''" type="text" name="email"
									value="${editForm.email}" /></td>
						</tr>
						<tr>
							<td>Beschreibung:<sup>*</sup></td>
							<td><form:errors path="description" cssStyle="color: #ff0000;"
									element="span" /> <form:input path="description"
									onclick="this.value=''" type="text" name="Beschreibung"
									value="${editForm.description}" /></td>
						</tr>
						<c:if test="${member.isTutor}">
							<tr>
								<td>Gebühr pro Kurs:<sup>*</sup></td>
								<td><form:errors path="fee" cssStyle="color: #ff0000;"
										element="span" /> <form:input path="fee"
										onclick="this.value=''" type="text" name="fee"
										value="${editForm.fee}" /></td>
							</tr>
							<tr>
								<td><form:button type="button"
										onclick="changeVisibility();">Wähle Standorte</form:button></td>
								<td>
									<div id="universitySelection" style="display: none">
										<form:checkboxes items="${universityChoices}"
											path="universities" />
									</div>
								</td>
							</tr>
						</c:if>
						<tr>
							<td>Altes Passwort:<sup>*</sup></td>
							<td><form:errors path="oldPassword"
									cssStyle="color: #ff0000;" element="span" /> <form:input
									path="oldPassword" type="password" name="pw" /></td>
						</tr>
						<tr>
							<td>Neues Passwort:<sup>*</sup></td>
							<td><form:errors path="password" cssStyle="color: #ff0000;"
									element="span" /> <form:input path="password"
									onclick="this.value=''" type="password" name="pw" /></td>
						</tr>
						<tr>
							<td>Neues Passwort bestätigen:<sup>*</sup></td>
							<td><form:errors path="passwordConfirm"
									cssStyle="color: #ff0000;" element="span" /> <form:input
									path="passwordConfirm" type="password" name="Passwort2" /></td>
						</tr>
						<tr>

						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input class="submitbutton" type="submit" value="Save"
			style="margin-left: 400px;" />
	</form:form>


</div>
</div> <%-- Do not delete this div --%>
<c:import url="template/footer.jsp" />
</body>
</html>




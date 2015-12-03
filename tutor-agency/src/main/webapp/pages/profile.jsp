<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/profile.css" var="profile" />
<spring:url value="/js/profile.js" var="js" />
<link type="text/css" rel="stylesheet" href="${profile}" />
<script src='<c:url value="/js/jquery-2.1.4.min.js"/>'></script>
<script src='<c:url value="/js/profile.js"/>'></script>

<div class="container">
	<%-- message for positive feedback if the user made an action
		 e.g. created and offer, edited the profile
	--%>
	<c:if test="${not empty message}">
		<h2><c:out value="${message}" /></h2>
	</c:if>

	<h1>Profile</h1>

	<form:form action="/tutor-agency/auth/account/edit" method="get">
		<input class="submitbutton" type="submit" value="Edit Profile" />
	</form:form>
	<c:if test="${not member.isTutor }">
		<form:form action="/tutor-agency/auth/account/becomeTutor">
			<input class="submitbutton" type="submit" value="Become a Tutor"
				name="werde Tutor" />

		</form:form>
	</c:if>
	<c:if test="${member.isTutor}">
		<form:form action="/tutor-agency/auth/offer/new" method="get">
			<input class="submitbutton" type="submit"
				value="Create a Tutoring Offer" />
		</form:form>
	</c:if>
	<div class="clear"></div>
	<input type="checkbox" id="checkbox-info" checked> <label
		class="collapse" for="checkbox-info"> <span class="labelblock"></span>
		<h2>Account Information</h2></label>
	<div id="account-info">
		<c:import url="fragments/accountInformation.jsp" />
	</div>

	<c:if test="${member.isTutor && not empty member.offers}">
		<input type="checkbox" id="checkbox-offer">
		<label class="collapse" for="checkbox-offer"> <span
			class="labelblock"></span>
            <h2 id="offers">Open Tutoring Offers</h2></label>
        <div id="offers-seg">
			<c:import url="fragments/offerTablePrivate.jsp" />
		</div>
	</c:if>


	<input type="checkbox" id="checkbox-subscription"> <label
		class="collapse" for="checkbox-subscription"> <span
		class="labelblock"></span>
		<h2>Subscriptions</h2></label>
    <div id="subscriptions">
		<h6>*Here you can see the tutoring offers, for which you
			requested tutoring.*</h6>
		<c:if test="${empty member.subscriptions }">
			<br>But you have subscribed to no offer yet.
		</c:if>
		<c:if test="${not empty member.subscriptions }">
			<c:import url="fragments/subscriptions.jsp" />
		</c:if>
	</div>


</div>
</div>
<%-- Do not delete this div --%>
<c:import url="template/footer.jsp" />
</body>
</html>




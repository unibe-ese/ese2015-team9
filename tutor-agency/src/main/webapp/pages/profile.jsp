<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/profile.css" var="profile" />
<link type="text/css" rel="stylesheet" href="${profile}" />

<div class="container">
    <h1>Profile</h1>

    <form:form action="account/edit" method="get">
        <input class="submitbutton" type="submit" value="Edit Profile" />
    </form:form>
    <c:if test="${not member.isTutor }">
        <form:form action="account/becomeTutor">
            <input class="submitbutton" type="submit" value="Become a Tutor"
                   name="werde Tutor" />

        </form:form>
    </c:if>
    <c:if test="${member.isTutor}">
        <form:form action="auth/offer/new" method="get">
            <input class="submitbutton" type="submit" value="Add Course" />
        </form:form>
    </c:if>
    <div class="clear"></div>
    <input type="checkbox" id="checkbox-info">
    <label class="collapse" for="checkbox-info">
        <span class="block"></span>
        <h2>Account Information</h2></label>
    <div id="account-info"><c:import url="fragments/accountInformation.jsp" /></div>

    <c:if test="${member.isTutor}" >
        <input type="checkbox" id="checkbox-offer">
        <label class="collapse" for="checkbox-offer">
            <span class="block"></span>
            <h2>Open Tutoring Offers</h2></label>
        <div id="offers"><c:import url="fragments/offerTablePrivate.jsp" /></div>
    </c:if>


    <input type="checkbox" id="checkbox-subscription">
    <label class="collapse" for="checkbox-subscription">
        <span class="block"></span>
        <h2>Subscriptions</h2></label>
    <div id="offers"><c:import url="fragments/subscriptions.jsp" /></div>


</div>
</div> <%-- Do not delete this div --%>
<c:import url="template/footer.jsp" />
</body>
</html>




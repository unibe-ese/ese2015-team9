<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/publicProfile.css" var="profile" />
<link type="text/css" rel="stylesheet" href="${profile}" />

<div class="container">
    <h1><c:out value="${member.username}"></c:out></h1>

    <c:import url="fragments/publicProfileInformation.jsp" />        

    <c:if test="${not empty member.offers}">            
        <h2>Offers</h2>
        <div>
            <sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
            <table id=courses>
                <thead>
                    <tr>
                        <th>Course</th>
                        <th>University</th>
                            <c:if test="${loggedIn}">
                                <th>Subscribe</th>
                            </c:if>
                    </tr>
                </thead>
                <c:forEach items="${member.offers}" var="offer">
                    <c:set var="course" value="${offer.course}"/>
                    <tr>
                        <td><c:out value="${course.name}"></c:out></td>
                        <td><c:out value="${course.university.name}"></c:out></td>

                        <c:if test="${loggedIn}">
                            <!-- ADD CONTACT ICON -->

                            <td align="center">
                                <form
                                    onsubmit="return confirm('Do you want to request tutoring for ${course.name}?');"
                                    action="auth/offer/${offer.id}/subscribe" 
                                    method="get">

                                    <input class="none" id="emailIcon" type="image"
                                           src="img/email-icon.png" name="email-icon">
                                </form></td>

                        </c:if>
                    </tr>
                </c:forEach>
            </table>

        </div>

    </c:if>


</div>
</div> <%-- Do not delete this div --%>
<c:import url="template/footer.jsp" />
</body>
</html>




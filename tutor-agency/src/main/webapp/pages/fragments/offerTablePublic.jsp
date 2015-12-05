<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>


<!-- OFFER TABLE -->

<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
<c:choose>
    <c:when test="${not empty offers}">
        <h2>Tutoring Offers</h2>

        <table id=courses>
            <thead>
                <tr>
                    <th>Course</th>
                    <th>University</th>
                    <th>Tutor</th>
                    <th>Fee</th>
                    <th>Grade</th>
                        <c:if test="${loggedIn}">
                        <th>Request Tutoring</th>
                        </c:if>
                </tr>
            </thead>

            <c:forEach items="${offers}" var="offer">
                <tr>
                    <td><c:out value="${offer.course.name}"></c:out></td>
                    <td><c:out value="${offer.course.university.name}"></c:out></td>
                    <td><a href="profileId=${offer.tutor.id}"> <c:out
                                value="${offer.tutor.username}"></c:out></a></td>
                    <td>${offer.tutor.fee}</td>
                    <td>${offer.grade}</td>

                    <c:if test="${loggedIn}">
                        <!-- ADD CONTACT ICON -->

                        <td align="center">
                            <form
                                onsubmit="return confirm('Do you want to request tutoring for ${offer.course.name} from ${offer.tutor.username}?');"
                                action="auth/offer/${offer.id}/subscribe" 
                                method="get">

                                <input class="none" id="emailIcon" type="image"
                                       src="img/email-icon.png" name="email-icon">
                            </form></td>
                        </c:if>

                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p id="noresult">No results found in database...</p>
    </c:otherwise>
</c:choose>
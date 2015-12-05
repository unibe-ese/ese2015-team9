<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<table id=subscriptions>
    <thead>
        <tr>
            <th>Course</th>
            <th>Tutor</th>
            <th>Status</th>
        </tr>
    </thead>

    <c:forEach items="${member.subscriptions}" var="subscription">
        <tr>
            <td><c:out value="${subscription.offer.course.name}"></c:out></td>
            <td><a href="/tutor-agency/profileId=${subscription.offer.tutor.id}"> <c:out
                        value="${subscription.offer.tutor.username}"></c:out></a></td>
                    <c:choose>
                        <c:when test="${subscription.accepted}">
                        <td>Accepted: <a href="mailto:<c:out value="${subscription.offer.tutor.email}" />">
                            Contact ${subscription.offer.tutor.username}</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>waiting for acceptance...</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
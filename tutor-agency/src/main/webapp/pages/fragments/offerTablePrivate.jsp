<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="flex-upper">
    <c:forEach items="${member.offers}" var="offer">
        <fieldset>
            <legend><c:out value="${offer.course.name}"></c:out></legend>
                <div class="flex-lower">
                    <div class="flex-lower-item" style="display: none">
                        <span class="bold">
                        <c:out value="${offer.course.university.name}"></c:out></span>
                        <br>
                        <span class="bold">
                            Grade: <c:out value="${offer.grade}"></c:out>
                        </span>
                    </div>
                    <div class="flex-lower-item course-info">
                        <p><c:out value="${offer.course.university.name}"></c:out></p>
                        <br>
                        <p>Grade: <c:out value="${offer.grade}"></c:out></p>
                    </div>
                <c:if test="${not empty offer.subscriptions}">

                    <c:forEach items="${offer.subscriptions}" var="subscription">
                        <c:choose>
                            <c:when test="${!subscription.accepted}">
                                <div class="flex-lower-item request open">
                                    <div class="item-text">
                                        <p>Request from</p>
                                        <p class="bold"><a href="<c:url value="/profileId=${subscription.member.id}" />">
                                                <c:out value="${subscription.member.username}"/></a></p>
                                    </div>
                                    <a class="button" href="offer/${offer.id}/accept/${subscription.id}/">
                                        Accept</a>
                                </div>

                            </c:when>
                            <c:otherwise>
                                <div class="flex-lower-item request accepted">
                                    <div class="item-text">
                                        <p>Accepted Request</p>
                                        <p class="bold"><a href="<c:url value="/profileId=${subscription.member.id}" />">
                                                <c:out value="${subscription.member.username}"/></a></p>
                                    </div>
                                    <a class="button" href="mailto:<c:out value="${subscription.member.email}" />">
                                        Contact</a>
                                </div>

                            </c:otherwise>
                        </c:choose>

                    </c:forEach>

                </c:if>
                <div class="flex-lower-item remove">
                    <div class="item-text">
                        <p>Remove this course?</p>
                    </div>
                    <form onsubmit="return confirm('Do you want to remove ${offer.course.name}?');" 
                          action="offer/${offer.id}/delete" method="get">
                        <input type="submit" value="Remove">
                    </form>
                </div>

            </div>
        </fieldset>
    </c:forEach>
</div>




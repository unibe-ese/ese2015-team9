<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="flex-upper">
    <fieldset>
        <legend>
            Basic Information
        </legend>
        <table>
            <tr>
                <td class="bold">Username:</td>
                <td>${member.username}</td>
            </tr>
            <tr>
                <td class="bold">First Name:</td>
                <td>${member.firstName}</td>
            </tr>
            <tr>
                <td class="bold">Last Name:</td>
                <td>${member.lastName}</td>
            </tr>
            <tr>
                <td class="bold">E-Mail:</td>
                <td>${member.email}</td>
            </tr>
        </table>

    </fieldset>
    <c:if test="${member.isTutor}">
        <fieldset><legend>Tutoring</legend>
            <table>

                <c:if test="${member.isTutor}">
                    <tr>
                        <td class="bold">Fee:</td>
                        <td>${member.fee}</td>
                    </tr>

                    <tr>
                        <td class="bold">Locations:</td>
                        <td>
                            <ol>
                                <c:forEach items="${member.universityList}" var="unis">
                                    <li>${unis.name}</li>
                                    </c:forEach>
                            </ol>
                    </tr>
                </c:if>
            </table>
        </fieldset>
    </c:if>

    <fieldset>
        <legend>Description</legend>
        <c:choose>
            <c:when test="${not empty member.description}">
                ${member.description}
            </c:when>
            <c:otherwise>
                Don't forget to write a 
                <a href="<c:url value="/auth/account/edit" />" >description</a>
            </c:otherwise>
        </c:choose>
    </fieldset>        
</div>

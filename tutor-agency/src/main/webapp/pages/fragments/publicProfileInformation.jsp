<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="flex-upper">
    <fieldset>
        <legend>Information</legend>
        <table>
            <tr>
                <td class="bold">Username:</td>
                <td>${member.username}</td>
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

    <c:if test="${not empty member.description}">
        <fieldset>
            <legend>Description</legend>
            ${member.description}
        </fieldset>        
    </c:if>      

</div>

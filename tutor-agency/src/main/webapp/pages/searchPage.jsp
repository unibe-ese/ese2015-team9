<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/search.css" var="search" />
<link type="text/css" rel="stylesheet" href="${search}" />
<script src='<c:url value="/js/jquery-2.1.4.min.js"/>'></script>
<script src='<c:url value="/js/search.js"/>'></script>

<div class="container" id="search">
    <form:form modelAttribute="form" action="search#result" method="post"
               class="searchForm" >								
        <form:input path="searchText" type="search" onclick="this.value=''"
                    value="" placeholder = "enter a course name"/>
        <input class="searchbutton" type="submit" value="Search" />

        <div>
            <form:checkbox class="checkbox" path="filtered" id="filtered" />
            <label class="collapse" for="filtered"><span class="labelblock"></span>
                <p>Show filters</p></label>
            <div id="filters">
                <fieldset class="filter">
                    <legend>Universities</legend>

                    <div class="uni-flex">
                        <c:forEach items="${universities}" var="current" >
                            <div class="uni-flex-item">
                                <form:checkbox id="${current.name}" class="uni-checkbox" path="universityNames" value="${current.name}" />
                                <label for="${current}"><span class="labelblock"></span><p><c:out value="${current}" /></p></label>
                            </div>
                        </c:forEach>
                    </div> 

                </fieldset>

                <fieldset class="filter">
                    <legend>Fee</legend>
                    <label>Min: </label>
                    <br>
                    <form:input path="minFee" type="number" value="${form.minFee }"></form:input>
                    <br> <label>Max:</label> <br>
                    <form:input path="maxFee" type="number" value="${form.maxFee }"></form:input>
                    </fieldset>

                    <fieldset class="filter">
                        <legend>Grade</legend>
                        <label>Min: </label>
                        <div>
                            <label class="select-label">
                                <form:select path="minGrade" items="${grades}" />
                            </label>
                        </div>
                </fieldset>
                        
            </div>
        </div>
    </form:form>
    <div id="result">
        <!-- IMPORT TABLE -->
        <c:import url="fragments/offerTablePublic.jsp" />
    </div>

</div>
</div> <%-- Do not delete this div --%>
<c:import url="template/footer.jsp" />
</body>
</html>



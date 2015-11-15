<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="template/header.jsp" />
<spring:url value="/css/index.css" var="index" />

<link type="text/css" rel="stylesheet" href="${index}" />

<div class="container">
    	
    	<p class="index">Suchst Du einen Nachhilfelehrer oder möchtest Du selbst Nachhilfe unterrichten? Dann bist Du hier genau richtig!
            <a href="<c:url value="/register" />">Melde Dich an leg gleich los.</a></p>
    
</div>
</div>
<c:import url="template/footer.jsp" />
</body>
</html>



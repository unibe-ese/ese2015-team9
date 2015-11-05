<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />



<div class="container">
    	<h1>
 
Access Denied!</h1><p>
 
Only users have access to this page!</p>

  	<div class="stripe"></div>

    <div class="sidebar"><c:import url="template/sidebar.jsp" /></div>
    
    <div class="footer">2015 &copy; All rights reserved | <a href="agb.html">AGB</a></div>
    
</div>

<c:import url="template/footer.jsp" />

</body>
</html>
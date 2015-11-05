<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize var="loggedIn" access="hasRole('ROLE_USER')" />
<c:choose>
    <c:when test="${loggedIn}">
        <c:import url="template/header.jsp" />
    </c:when>
    <c:otherwise>
        You are logged out
    </c:otherwise>
</c:choose>




<div class="container">
    	
	
<div class="stripe"></div>
<div class="sidebar"><c:import url="template/sidebar.jsp" /></div>
    
<c:import url="template/footer.jsp" />
    
</div>

</div>
</body>
</html>
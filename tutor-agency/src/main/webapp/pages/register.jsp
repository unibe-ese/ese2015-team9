<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/register.css" var="register" />

<link type="text/css" rel="stylesheet" href="${register}" />


<div class="container" id="register">
			<h1>Anmelden</h1>
			<c:import url="template/registerform.jsp" />
                


</div>
</div>
<c:import url="template/footer.jsp" />
</body>
</html>



<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/login.css" var="login" />
<link type="text/css" rel="stylesheet" href="${login}" />

<div class="container" id="login">
	<div class = "error">${message}</div>

	<form action="<c:url value="/j_spring_security_check"></c:url>"
		method="post">
		
				<input id="j_username" name="j_username"
                       onclick="this.value=''" value="Username" type="text" placeholder="Username"/>
				<input id="j_password" name="j_password"
					onclick="this.value=''" type="password" placeholder="Passwort"/>
                <br>
                <input class="submitbutton" type="submit" value="Login" />
	</form>


</div>
</div> <%-- Do not delete this div --%>
    <c:import url="template/footer.jsp" />
</body>
</html>
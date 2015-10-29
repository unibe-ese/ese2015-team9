<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/css/style.css" var="css" />

<c:import url="template/header.jsp" />

<div class="container">
    	<h1>Login</h1>
    	<br \>
	<div id="login-error">
	${error}</div>
	<form action="<c:url value="/j_spring_security_check"></c:url>" method="post" >
	<p>
	<label for="j_username">Username</label>
	<input id="j_username" name="j_username" onclick="this.value=''" value="Nickname" type="text" style="max-width:200px;"/>
	</p><p>	 
	<label for="j_password">Password</label>
	<input id="j_password" name="j_password" onclick="this.value=''" type="password" value="password" style="max-width:200px;"/>
	</p><input  class="submitbutton" type="submit" value="Login"/>        
	 
	</form>
	
	<div class="small">
					<a href="forgot" style="float: left; left:500px; ">Passwort
						vergessen?</a>
				</div>
				
  	<div class="stripe"></div>

    <div class="sidebar"><c:import url="template/sidebar.jsp" /></div>
    
<c:import url="template/footer.jsp" />
    
</div>

</div>
</body>
</html>
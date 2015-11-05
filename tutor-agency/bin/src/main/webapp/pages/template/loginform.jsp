<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Login</h1>
		<br>
		<form action="<c:url value="/j_spring_security_check"></c:url>"
			method="post">
			<p>
				<label for="j_username">Username</label> <input id="j_username"
					name="j_username" type="text" />
			</p>
			<p>
				<label for="j_password">Password</label> <input id="j_password"
					name="j_password" type="password" />
			</p>
			<input class="submitbutton" type="submit" value="Login" />
		</form>
		<div class="small">
			<a href="forgot" style="float: right; margin-right: 15px;">Passwort
				vergessen?</a>
		</div>
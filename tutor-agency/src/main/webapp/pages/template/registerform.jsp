<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form:form modelAttribute="signupForm" action="create"
				id="signupForm" method="post">
				
                <form:errors path="firstName" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="firstName"
								onclick="this.value=''" type="text" 
                                placeholder="Vorname" Value="Vorname"/>

						<form:errors path="lastName" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="lastName"
								onclick="this.value=''" type="text" 
                                placeholder="Nachname" value="Nachname"/>


						<form:errors path="username" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="username"
								onclick="this.value=''" type="text" 
                                placeholder="Username" value="Username"/>

						<form:errors path="email" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="email"
								onclick="this.value=''" type="text"
                                placeholder="Email" value="Email"/>

						<form:errors path="password" cssStyle="color: #ff0000;"
								element="span" /> <form:input path="password"
								onclick="this.value=''" type="password" 
                                placeholder="Passwort"/>

						<form:errors path="passwordConfirm"
								cssStyle="color: #ff0000;" element="span" /> <form:input
								path="passwordConfirm" onclick="this.value=''" type="password"
								placeholder="bestätige Passwort"/>
				

				<form:errors path="readAGB" cssStyle="color: #ff0000;"
					element="span" />
				<br />
                <form:checkbox path="readAGB" name="agb" id="agb" /><label class="registerlabel" for="agb"></label>
                <p>Ich habe die
					<a href="agb#agb">AGB's</a> gelesen und bin damit einverstanden.</p>
					
				<br />
				<input class="submitbutton" type="submit" value="Anmelden"/>
				<br />
			</form:form>

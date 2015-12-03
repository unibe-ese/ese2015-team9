<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form:form modelAttribute="signupForm" action="create"
           id="signupForm" method="post" cssClass="forms">

    <form:errors path="firstName" class="error" element="p" />
    <form:input path="firstName"
                onclick="this.value=''" type="text" 
                placeholder="First name" Value="First name"/>

    <form:errors path="lastName" class="error" element="p" />
    <form:input path="lastName"
                onclick="this.value=''" type="text" 
                placeholder="Last name" value="Last name"/>


    <form:errors path="username" class="error" element="p" />
    <form:input path="username"
                onclick="this.value=''" type="text" 
                placeholder="Username" value="Username"/>

    <form:errors path="email" class="error" element="p" />
    <form:input path="email"
                onclick="this.value=''" type="text"
                placeholder="Email" value="Email"/>

    <form:errors path="password" class="error" element="p" />
    <form:input path="password"
                onclick="this.value=''" type="password" 
                placeholder="Password"/>

    <form:errors path="passwordConfirm"
                 class="error" element="p" />
    <form:input
        path="passwordConfirm" onclick="this.value=''" 
        type="password" placeholder="Confirm password"/>


    <form:errors path="readAGB" class="error"
                 element="p" />
    <br />
    <form:checkbox path="readAGB" name="agb" id="agb" /><label class="labelblock" for="agb"></label>
    <p>I have read and agree to the
        <a href="agb#agb">Terms and Conditions</a>.</p>

    <br />
    <input class="submitbutton" type="submit" value="Register"/>
    <br />
</form:form>

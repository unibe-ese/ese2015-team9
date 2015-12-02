<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="template/header.jsp" />
<spring:url value="/css/impressum.css" var="impressum" />

<link type="text/css" rel="stylesheet" href="${impressum}" />


<div class="container" id="impressum">
<h1>Impressum</h1>
<p>Firmierung Muster GmbH
<br>Strasse und Hausnummer Musterstrasse 2
<br>Postleitzahl und Ort 8000 Musterstadt Schweiz
<br>vertreten durch die Geschäftsführerin Melanie Muster
<br>Telefonnummer 8000
<br>Fax wenn gewünscht
<br>E-Mail info@Muster.ch
<br>Eingetragen im Handelsregister Muster HR-Eintrag
<br>Umsatzsteuer-Identifikationsnummer CH ...
</p>
    
</div>
</div>
<c:import url="template/footer.jsp" />
</body>
</html>
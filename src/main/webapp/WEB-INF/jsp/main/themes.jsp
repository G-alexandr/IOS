<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2><spring:message code="tasks"/></h2>

    <div id="theme-content">This is a theme content which should be said by google speech</div>

    <input type="button"  onclick="location.href='/getWav.mp3'" value="Read" >

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
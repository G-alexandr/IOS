<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../fragments/headTag.jsp"/>

<body>
    <div class="container">
        <jsp:include page="../fragments/bodyHeader.jsp"/>

        <h2><spring:message code="themes"/></h2>

        <c:forEach var="themeObject" items="${themeList}">
            <div>
                <div>${themeObject.id}</div>
                <div><a href="${contextPath}/main/themes/${themeObject.id}">${themeObject.name}</a></div>
                <div>${themeObject.description}</div>
            </div>
        </c:forEach>

        <%--<div id="theme-content">This is a theme content which should be said by google speech</div>--%>

        <%--<input type="button"  onclick="location.href='/getWav.mp3'" value="Read" >--%>

        <jsp:include page="../fragments/footer.jsp"/>

    </div>
</body>
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2><spring:message code="theme"/>${theme.name}</h2>

    <c:forEach var="content" items="${contents}">
        <div>
            <div>${content.contentBody}</div>
            <input type="button"  onclick="location.href='content/${content.id}/play.mp3'" value="Listen" >
            <c:if test="${!userContents.contains(content.id)}">
                <input type="button"  onclick="location.href='content/${content.id}/finish'" value="Finish reading" >
            </c:if>

        </div>
    </c:forEach>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
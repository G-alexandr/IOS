<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Tasks</h2>

    <datatables:table id="tasks" data="${tasks.tasksList}" cdn="true" row="task" theme="bootstrap2" cssClass="table table-striped" paginate="false" info="false" rowIdBase="">

        <datatables:column title="Name">
            <c:if test="${task.openScore <= openscore}">
                <spring:url value="tasks/{taskId}" var="taskUrl">
                    <spring:param name="taskId" value="${task.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(taskUrl)}"><c:out value="${task.name}"></c:out></a>
            </c:if>
            <c:if test="${task.openScore > openscore}">
                <c:out value="${task.name}"></c:out>
            </c:if>
        </datatables:column>
        <datatables:column title="Description">
            <c:out value="${task.description}"></c:out>
        </datatables:column>
        <datatables:column title="Lock">
            <c:if test="${task.openScore <= openscore}">
                <img src="<spring:url value="/resources/images/bullet-arrow.png" htmlEscape="true" />"
            </c:if>
            <c:if test="${task.openScore > openscore}">
                <img src="<spring:url value="/resources/images/lock.png" htmlEscape="true" />"
            </c:if>

        </datatables:column>
    </datatables:table>



    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
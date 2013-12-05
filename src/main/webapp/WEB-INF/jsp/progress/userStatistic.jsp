<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2><spring:message code="statistic"/></h2>

    <datatables:table id="tasks" data="${progressList}" cdn="true" row="progressRow" theme="bootstrap2" cssClass="table table-striped" paginate="false" info="false" rowIdBase="">

        <datatables:column title="Theme">
            <div>${progressRow.theme.name}</div>
        </datatables:column>

        <datatables:column title="Score">
            <div>${progressRow.score}</div>
        </datatables:column>

        <datatables:column title="Completed">
            <c:if test="${progressRow.completed}">
                <div>+++</div>
            </c:if>
        </datatables:column>
    </datatables:table>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
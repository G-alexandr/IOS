<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Tasks</h2>

    <datatables:table id="tasks" data="${tasks.vetList}" cdn="true" row="task" theme="bootstrap2" cssClass="table table-striped" paginate="false" info="false">
        <datatables:column title="Name">
            <c:out value="${task.firstName} ${task.lastName}"></c:out>
        </datatables:column>
        <datatables:column title="Description">
            <c:forEach var="specialty" items="${task.specialties}">
                <c:out value="${specialty.name}"/>
            </c:forEach>
            <c:if test="${task.nrOfSpecialties == 0}">Nothing to display</c:if>
        </datatables:column>
        <datatables:column title="Lock">
            <img src="<spring:url value="/resources/images/bullet-arrow.png" htmlEscape="true" />"
        </datatables:column>
    </datatables:table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/vets.atom" htmlEscape="true" />">Subscribe to Atom feed</a>
            </td>
        </tr>
    </table>


    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
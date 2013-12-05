<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../fragments/headTag.jsp"/>

<body>
    <div class="container">
        <jsp:include page="../fragments/bodyHeader.jsp"/>

        <h2><spring:message code="themes"/></h2>

        <datatables:table id="tasks" data="${themeList}" cdn="true" row="themeObject" theme="bootstrap2" cssClass="table table-striped" paginate="false" info="false" rowIdBase="">

            <datatables:column title="Number">
                ${themeObject.id}
            </datatables:column>

            <datatables:column title="Name">
                <a href="${contextPath}/main/themes/${themeObject.id}">${themeObject.name}</a>
            </datatables:column>

            <datatables:column title="Description">
                ${themeObject.description}
            </datatables:column>
        </datatables:table>

        <jsp:include page="../fragments/footer.jsp"/>

    </div>
</body>
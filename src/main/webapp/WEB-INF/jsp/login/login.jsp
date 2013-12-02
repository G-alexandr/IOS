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

    <form:form action="/login/createUser" method="get" class="form-horizontal" modelAttribute="username">
        <fieldset>
            <div class="control-group" id="lastName">
                <label class="control-label">Enter user name</label>
                <form:input path="name" size="30" maxlength="80"/>
                <span class="help-inline"><form:errors path="*"/></span>
            </div>
            <div class="control-group">
                <button type="submit" style="width: 80px">Ok</button>
            </div>
        </fieldset>
    </form:form>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
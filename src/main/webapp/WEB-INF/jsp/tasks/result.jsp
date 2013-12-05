<%@ page import="org.springframework.samples.petclinic.web.Constanns" %>
<%@ page import="org.springframework.samples.petclinic.model.TaskTask" %>
<%@ page import="org.springframework.samples.petclinic.model.TaskContent" %>
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


    <h1>TASK SUCCESSFUL!</h1>
    <br>

    <p>Your answer is: ${answer}<p>
    <br>
    <h2>___________________________________</h2>
    <br>
    <h2>${solution}</h2>
    <h2>You've got ${points} points</h2>

    <br>

    <div style="width: 140px;"><a href="<spring:url value="/main/tasks" htmlEscape="true" />">Return back</a></div>
    <br>
    <br>


    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
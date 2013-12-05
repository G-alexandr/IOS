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
<script type="text/javascript">

    function submitAnswer(){
        var variables = '';
       $('input.variable').each(function(){
            variables = variables + $( this).val() + ','
        })
        $('input#vars').val(variables);
        var formulas = '';
        $('input.formula').each(function(){
            formulas = formulas + $( this).val() + ','
        })
        $('input#fml').val(formulas)
//        $('#form').submit();
        document.taskform.submit()
    }
    function addValueField(){
        $('tbody td input.variable').last().after('<br><input class="variable"  type="text" value="" size="30" maxlength="80"><br>' )
    }
    function addFormulasField(){
        $('tbody td input.formula').last().after('<br><input class="formula"  type="text" value="" size="30" maxlength="80"><br>' )
    }
</script>
<div class="container">


    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <c:if test="${taskmain.type == task}">
        <h1>TASK</h1>
    </c:if>
    <h2>${taskmain.name}</h2>

    <form:form id="form" name="taskform" action="/task/makeResolve" method="post" class="form-horizontal" modelAttribute="formbean">
        <form:hidden id="vars" path="variables"/>
        <form:hidden id="fml" path="formulas"/>
        <form:hidden path="taskId" />

        <div class="task_wrap">
        <h3>${taskcontent.content}</h3>
        <br>
        <br>
            <table border="0">
                <thead>
                    <tr>
                        <td><p>Variables<p><input type="button" style="width: 80px" value="Add" onclick="addValueField()"></td>
                        <td><p>Formula<p><input type="button" style="width: 80px" value="Add" onclick="addFormulasField()"></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <input class="variable"  type="text" value="" size="30" maxlength="80"><br>
                        </td>
                        <td>
                            <input class="formula"  type="text" value="" size="30" maxlength="80"><br>
                        </td>
                    </tr>
                </tbody>
            </table>
        <br>
        <p>Answer:<p>
        <h5>Type an answer variable</h5>
        <form:input cssClass="answer" path="answer" size="30" maxlength="80"/>

        <br>
        <br>
        <input type="button" style="width: 80px" value="OK" onclick="submitAnswer()">
    </div>
    </form:form>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
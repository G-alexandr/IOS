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

        <div class="task_wrap">
        <h3>${taskcontent.content}</h3>
        <br>
        <br>
            <table border="1">
                <tr>
                    <td><p>Variables<p></td>
                    <td><p>Formulas<p></td>
                </tr>
                <tr>
                    <td><input class="variable" id="value_1" type="text" value="" size="30" maxlength="80">
                        <input type="button" style="width: 80px" value="Add"></input>
                    </td>
                    <td> <input class="formula" id="value_1" type="text" value="" size="30" maxlength="80">
                        <input type="button" style="width: 80px" value="Add"></input>
                    </td>
                </tr>
            </table>
        <br>
        <p>Answer:<p>
        <h5>Type an answer variable</h5>
        <form:input cssClass="answer" path="answer" size="30" maxlength="80"/>

        <br>
        <br>
        <input type="button" style="width: 80px" value="OK" onclick="submitAnswer()"></input>
    </div>
    </form:form>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>
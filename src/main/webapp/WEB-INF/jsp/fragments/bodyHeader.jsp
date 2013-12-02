<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:url value="/resources/images/banner-graphic.png" var="banner"/>
<%--<img src="${banner}"/>--%>

<div class="navbar" style="width: 601px;">
    <div class="navbar-inner">
        <ul class="nav">
            <li style="width: 100px;"><a href="<spring:url value="/" htmlEscape="true" />"><i
                    class="icon-home"></i>Home</a></li>
            <li style="width: 130px;"><a href="<spring:url value="/main/themes" htmlEscape="true" />"><i
                    class="icon-th-list"></i>Themes</a></li>
            <li style="width: 140px;"><a href="<spring:url value="/main/tasks" htmlEscape="true" />"><i
                    class="icon-th-list"></i>Tasks</a></li>
            <li style="width: 140px;"><a href="<spring:url value="/main/statistic" htmlEscape="true" />"><i
                class="icon-th-list"></i>Statistic</a></li>

        </ul>
    </div>
</div>
	

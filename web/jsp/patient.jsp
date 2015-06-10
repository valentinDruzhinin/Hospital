<%-- 
    The patient page.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="man" uri="/WEB-INF/tld/manipulations.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>

    <fmt:bundle basename="resources.i18n.staff">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="titlePatient"/></title>
        </head>
        <body>

            <form method="POST" action="./Controller" align="right">
                <input type="hidden" name="pageName" value="patient" >
                <input type="hidden" name="join" value="language">
                <button type="submit" value="en_US" name="lan">English</button>
                <button type="submit" value="ru_RU" name="lan">Русский</button>
            </form>

            <h1><fmt:message key="welcomePatient"/>  ${login}</h1>
        </fmt:bundle>       
        <hr/>
        
        <%------------------------- All today manipulations -----------------------%>

        <fmt:bundle basename="resources.i18n.manipulations">
            <%@include file="showManipulations.jsp" %>
        </fmt:bundle>    
        <fmt:bundle basename="resources.i18n.main">
            <a href="Controller?join=logout"><fmt:message key="logOut"/></a>
        </fmt:bundle>
    </body>
</html>

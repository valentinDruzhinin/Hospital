<%--
    The welcome page.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"prefix="fmt"%>

<jsp:useBean scope="session" id="language" class="java.lang.String"/>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="resources.i18n.index" >
    <head>
        <title><fmt:message key="hospital"/></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <form method="POST" action="./Controller" align="right">
            <input type="hidden" name="pageName" value="index">
            <input type="hidden" name="join" value="language">
            <button type="submit" value="en_US" name="lan">English</button>
            <button type="submit" value="ru_RU" name="lan">Русский</button>
        </form>
        
        <h1><fmt:message key="welcome"/></h1>
        
        <a href="main.jsp"><fmt:message key="join"/></a>
    </body>
    </fmt:bundle>
</html>
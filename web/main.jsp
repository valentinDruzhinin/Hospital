<%-- 
    The main page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="resources.i18n.main" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="aut"/></title>
    </head>
    <body>
        
        <form method="POST" action="./Controller" align="right">
            <input type="hidden" name="pageName" value="main">
            <input type="hidden" name="join" value="language">
            <button type="submit" value="en_US" name="lan">English</button>
            <button type="submit" value="ru_RU" name="lan">Русский</button>
        </form>        
        
        
        <form method="POST" action="./Controller">
            <input type="hidden" name="join" value="login">
            <fmt:message key="login"/><br/>
            <input type="text" name="login" value=""> <br/>
            <fmt:message key="pas"/><br/>
            <input type="password" name="password" value="">
            <br/>
            <c:if test="${nullPage}">
                <fmt:message key="nullPage"/>
            </c:if>
            <br/>
            <c:if test="${wrongLogin}">
                <fmt:message key="wrongLogin"/>
            </c:if>
            <br/>
            <input type="submit" value="<fmt:message key="submit"/>"><br/>
            <br/>
            <a href="jsp/registration.jsp"><fmt:message key="reg"/><a/>
        </form>
        
    </body>
    </fmt:bundle>
</html>

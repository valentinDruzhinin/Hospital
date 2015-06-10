<%-- 
    The registration page.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="resources.i18n.loginRegistration" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="welcome"/></title>
    </head>
    <body align="center">
        
        <form method="POST" action="../Controller" align="right">
            <input type="hidden" name="pageName" value="regist">
            <input type="hidden" name="join" value="language">
            <button type="submit" value="en_US" name="lan">English</button>
            <button type="submit" value="ru_RU" name="lan">Русский</button>
        </form>
        
        <form method="POST" action="../Controller">
            <input type="hidden" name="regist" value="registration">
            <fmt:message key="login"/>
             <br/>
            <input type="text" name="regLogin" autocomplete="off" required> <br/>
            <fmt:message key="pas"/>
             <br/>
            <input type="password" name="regPassword" autocomplete="off" required> <br/>
            <fmt:message key="firstName"/>
             <br/>
            <input type="text" name="firstName" required> <br/>
            <fmt:message key="secondName"/>
             <br/>
            <input type="text" name="secondName" required> <br/>
            <fmt:message key="dateBirth"/>
             <br/>
            <input type="date" name="dateBirth" required> <br/><br/>
            <fmt:message key="typeStaff"/>
             <br/>
            <select name="typeStaff" required>
                <option value="Nurse"><fmt:message key="nurse"/></option>
                <option value="Doctor"><fmt:message key="doctor"/></option>
            </select>
            <br/>
            <c:if  test="${errorRegistration}">
                <fmt:message key="errorRegistration"/>
            </c:if>
            <c:if test="${error}" >
                <fmt:message key="error"/>
            </c:if>
            <br/>
            <input type="submit" value="OK">
        <form/>
            <br/>
            <a href="main.jsp"><fmt:message key="return"/><a/>
            
    </body>
    </fmt:bundle>
</html>

<%-- 
    The page of patient registration form.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="resources.i18n.patientRegistration">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <caption><fmt:message key="capt"/></caption>
        <form method="POST" action="./Controller">
            <input type="hidden" name="regist" value="patient_registration" required>
            <fmt:message key="login"/> <br/>
            <input autocomplete="off" type="text" name="regLogin" required> <br/>
            <fmt:message key="pas"/> <br/>
            <input type="password" name="regPassword" required> <br/>
            <fmt:message key="firstName"/> <br/>
            <input autocomplete="off" type="text" name="firstName" required> <br/>
            <fmt:message key="secondName"/> <br/>
            <input autocomplete="off" type="text" name="secondName" required> <br/>
            <fmt:message key="dateBirth"/> <br/>
            <input type="date" name="dateBirth" required> <br/>
            <fmt:message key="diagnose"/> <br/>
            <input type="text" name="diagnose" required> <br/>
            <br/>
            <c:if test="${errorRegistration}">
                <fmt:message key="errorRegistration"/>
            </c:if>
            <c:if test="${error}">
                <fmt:message key="error"/>
            </c:if>
            <br/>
            <input type="submit" value="<fmt:message key="confirm"/>">
        </form>
    </body>
    </fmt:bundle> 
</html>

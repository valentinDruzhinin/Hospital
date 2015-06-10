<%--
    The change password page.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <fmt:bundle basename="resources.i18n.password">
        <hr/>
        <caption><fmt:message key="caption"/></caption>
        <form method="POST" action="./Controller">
            <input type="hidden" name="join" value="change_password">
            <fmt:message key="oldPas"/><br/>
            <input type="text" autocomplete="off" name="oldPassword" required><br/>
            <fmt:message key="newPas"/><br/>
            <input type="text" autocomplete="off" name="newPassword" required><br/>
            <input type="submit" value="<fmt:message key="confirm"/>">
        </form>
    </fmt:bundle>
    </body>
</html>

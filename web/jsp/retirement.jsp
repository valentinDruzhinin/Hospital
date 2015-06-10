<%-- 
   The retirement page.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <hr/>
        <fmt:bundle basename="resources.i18n.retirement">
        <form method="POST" action="./Controller">
            <input type="hidden" name="join" value="retirement">
            <input type="submit" value="<fmt:message key="go"/>">
        </form>
        </fmt:bundle>
    </body>
</html>

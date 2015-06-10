<%-- 
    The page which show all today's user manipulations.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="man" uri="/WEB-INF/tld/manipulations.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h2><fmt:message key="nowdaysManip"/></h2>
            <man:show-manip/>

            <p><fmt:message key="medications"/></p>
            <table border="5px">
                <th><fmt:message key="id"/></th> 
                <th><fmt:message key="date"/></th> 
                <th><fmt:message key="performer"/></th>
                <th><fmt:message key="patient"/></th>
                <th><fmt:message key="nameManipulation"/></th>
                <th><fmt:message key="description"/></th>
                <th><fmt:message key="appointer"/></th>
                    <c:forEach var="medicIter" items="${medications}" >
                    <tr>
                        <td><c:out value="${ medicIter.id }" /></td>
                        <td><c:out value="${ medicIter.dateManipulation }" /></td>
                        <td><c:out value="${ medicIter.performerId }" /></td>
                        <td><c:out value="${ medicIter.patientId }" /></td>
                        <td><c:out value="${ medicIter.nameManipulation }" /></td>
                        <td><c:out value="${ medicIter.description }" /></td>
                        <td><c:out value="${ medicIter.appointerId }" /></td>
                    </tr>  
                </c:forEach>    
            </table>    


            <p><fmt:message key="operations"/></p>
            <table border="5px">
                <th><fmt:message key="id"/></th> 
                <th><fmt:message key="date"/></th> 
                <th><fmt:message key="performer"/></th>
                <th><fmt:message key="patient"/></th>
                <th><fmt:message key="nameManipulation"/></th>
                <th><fmt:message key="description"/></th>
                    <c:forEach var="oper" items="${operations}" >
                    <tr>
                        <td><c:out value="${ oper.id }" /></td>
                        <td><c:out value="${ oper.dateManipulation }" /></td>
                        <td><c:out value="${ oper.performerId }" /></td>
                        <td><c:out value="${ oper.patientId }" /></td>
                        <td><c:out value="${ oper.nameManipulation }" /></td>
                        <td><c:out value="${ oper.description }" /></td>
                    </tr>  
                </c:forEach>    
            </table>    


            <p><fmt:message key="procedures"/></p>
            <table border="5px">
                <th><fmt:message key="id"/></th> 
                <th><fmt:message key="date"/></th> 
                <th><fmt:message key="performer"/></th>
                <th><fmt:message key="patient"/></th>
                <th><fmt:message key="nameManipulation"/></th>
                <th><fmt:message key="description"/></th>
                <th><fmt:message key="appointer"/></th>
                    <c:forEach var="proc" items="${procedures}" >
                    <tr>
                        <td><c:out value="${ proc.id }" /></td>
                        <td><c:out value="${ proc.dateManipulation }" /></td>
                        <td><c:out value="${ proc.performerId }" /></td>
                        <td><c:out value="${ proc.patientId }" /></td>
                        <td><c:out value="${ proc.nameManipulation }" /></td>
                        <td><c:out value="${ proc.description }" /></td>
                        <td><c:out value="${ proc.appointerId }" /></td>
                    </tr>  
                </c:forEach>    
            </table>
    </body>
</html>

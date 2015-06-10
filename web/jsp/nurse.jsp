<%-- 
    The nurse page.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="man" uri="/WEB-INF/tld/manipulations.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="resources.i18n.staff">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="titleNurse"/></title>
        </head>
        <body>

            <form method="POST" action="./Controller" align="right">
                <input type="hidden" name="pageName" value="nurse" >
                <input type="hidden" name="join" value="language">
                <button type="submit" value="en_US" name="lan">English</button>
                <button type="submit" value="ru_RU" name="lan">Русский</button>
            </form>

            <h1> <fmt:message key="welcomeNurse"/> ${login}</h1>

            <hr/>
        </fmt:bundle>

        <%--------------------- All today nurse manipulations --------------------------------%>

        <fmt:bundle basename="resources.i18n.manipulations">
            <h2><fmt:message key="nowdaysManip"/></h2>
            <man:show-manip/>
            <form method="POST" action="./Controller">    

                <c:if test="${not empty medications}">
                    <p><fmt:message key="medications"/></p>
                    <input type="hidden" name="manipType" value="medication">
                    <input type="hidden" name="join" value="Modify">
                    <table border="5px">
                        <th><fmt:message key="id"/></th> 
                        <th><fmt:message key="date"/></th> 
                        <th><fmt:message key="performer"/></th>
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="appointer"/></th>
                        <th><fmt:message key="select"/></th>
                            <c:forEach var="medicIter" items="${medications}" >
                            <tr>
                                <td><c:out value="${ medicIter.id }" /></td>
                                <td><c:out value="${ medicIter.dateManipulation }" /></td>
                                <td><c:out value="${ medicIter.performerId }" /></td>
                                <td><c:out value="${ medicIter.patientId }" /></td>
                                <td><c:out value="${ medicIter.nameManipulation }" /></td>
                                <td><c:out value="${ medicIter.description }" /></td>
                                <td><c:out value="${ medicIter.appointerId }" /></td>
                                <td><input type="radio" name="manipId" value="${medicIter.id}"></td>
                            </tr>  
                        </c:forEach>
                    </table>
                    <input type="submit" value="<fmt:message key="select"/>" >
                </c:if>

                <c:if test="${not empty procedures}">
                    <p><fmt:message key="procedures"/></p>
                    <input type="hidden" name="manipType" value="procedure">
                    <input type="hidden" name="join" value="Modify">
                    <table border="5px">
                        <th><fmt:message key="id"/></th> 
                        <th><fmt:message key="date"/></th> 
                        <th><fmt:message key="performer"/></th>
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="appointer"/></th>
                        <th><fmt:message key="select"/></th>
                            <c:forEach var="proc" items="${procedures}" >
                            <tr>
                                <td><c:out value="${ proc.id }" /></td>
                                <td><c:out value="${ proc.dateManipulation }" /></td>
                                <td><c:out value="${ proc.performerId }" /></td>
                                <td><c:out value="${ proc.patientId }" /></td>
                                <td><c:out value="${ proc.nameManipulation }" /></td>
                                <td><c:out value="${ proc.description }" /></td>
                                <td><c:out value="${ proc.appointerId }" /></td>
                                <td><input type="radio" name="manipId" value="${proc.id}"></td>
                            </tr>  
                        </c:forEach>    
                    </table>
                    <input type="submit" value="<fmt:message key="select"/>" >
                </c:if>    
            </form>
        </fmt:bundle>            
        <hr/>
        <%@include file="changePassword.jsp" %>
        <%@include file="retirement.jsp" %>
        <fmt:bundle basename="resources.i18n.main">
            <a href="Controller?join=logout"><fmt:message key="logOut"/></a>
        </fmt:bundle>
    </body>
</html>

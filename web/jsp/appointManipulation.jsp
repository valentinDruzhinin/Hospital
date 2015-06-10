<%-- 
    The appoint manipulations page.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="man" uri="/WEB-INF/tld/manipulations.tld" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="resources.i18n.appointManipulation">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <man:PatientsTag/>
        <man:NursesTag/>
        <man:show-manip/>
        <caption><fmt:message key="caption"/></caption>
        <br/>
        <form method="POST" action="./Controller">
                <fmt:message key="date"/> <br/>
                <input type="date" name="dateAppoint" required> <br/>
                <fmt:message key="type"/> <br/>
                <select name="typeManip" required>
                    <option value="PROC"><fmt:message key="procedure"/></option>
                    <option value="OPER"><fmt:message key="operation"/></option>
                    <option value="MEDI"><fmt:message key="medicine"/></option>
                </select>
                <br/>
                <fmt:message key="performer"/> <br/>
                <select name="performer" >
                    <option value="${null}"><fmt:message key="you"/></option>
                    <c:forEach var="nurse" items="${allNurses}">
                        <option value="${nurse.id}">
                            <c:out value="${nurse.login}"/>
                        </option>
                    </c:forEach>
                </select>
                <br/>
                <fmt:message key="patient"/> <br/>
                <select name="patient" required>
                    <c:forEach var="patient" items="${allPatients}">
                        <option value="${patient.id}">
                            <c:out value="${patient.login}"/>
                        </option>
                    </c:forEach>
                </select>
                <br/>
                <fmt:message key="name"/> <br/>
                <input type="text" name="manipulation" autocomplete="off" required> <br/>
                <fmt:message key="description"/> <br/>
                <input type="text" name="description" autocomplete="off" maxlength="200" required> <br/>
                
                <br/>
                <c:if test="${errorRegistration}">
                    <fmt:message key="error"/>
                </c:if>
                <br/>
                <input type="submit" name="join" value="Appoint">
        </form>     
    </body>
    </fmt:bundle>
</html>

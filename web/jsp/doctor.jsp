<%-- 
    The doctor page.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="man" uri="/WEB-INF/tld/manipulations.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>

    <fmt:bundle basename="resources.i18n.staff">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="titleDoctor"/></title>
        </head>
        <body>

            <form method="POST" action="./Controller" align="right">
                <input type="hidden" name="pageName" value="doctor">
                <input type="hidden" name="join" value="language">
                <button type="submit" value="en_US" name="lan">English</button>
                <button type="submit" value="ru_RU" name="lan">Русский</button>
            </form>

            <h1><fmt:message key="welcomeDoctor"/> ${login}</h1>
        </fmt:bundle>

        <hr/>
        <%--------------------------MANIPULATIONS-------------------------------%>
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
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="select"/></th>
                            <c:forEach var="medicIter" items="${medications}" >
                            <tr>
                                <td><c:out value="${ medicIter.id }" /></td>
                                <td><c:out value="${ medicIter.dateManipulation }" /></td>
                                <td><c:out value="${ medicIter.patientId }" /></td>
                                <td><c:out value="${ medicIter.nameManipulation }" /></td>
                                <td><c:out value="${ medicIter.description }" /></td>
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
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="select"/></th>
                            <c:forEach var="proc" items="${procedures}" >
                            <tr>
                                <td><c:out value="${ proc.id }" /></td>
                                <td><c:out value="${ proc.dateManipulation }" /></td>
                                <td><c:out value="${ proc.patientId }" /></td>
                                <td><c:out value="${ proc.nameManipulation }" /></td>
                                <td><c:out value="${ proc.description }" /></td>
                                <td><input type="radio" name="manipId" value="${proc.id}"></td>
                            </tr>  
                        </c:forEach>    
                    </table>
                    <input type="submit" value="<fmt:message key="select"/>" >
                </c:if>    

                <c:if test="${not empty operations}">
                    <p><fmt:message key="operations"/></p>
                    <input type="hidden" name="manipType" value="operation">
                    <input type="hidden" name="join" value="Modify">
                    <table border="5px">
                        <th><fmt:message key="id"/></th> 
                        <th><fmt:message key="date"/></th> 
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="select"/></th>
                            <c:forEach var="oper" items="${operations}" >
                            <tr>
                                <td><c:out value="${ oper.id }" /></td>
                                <td><c:out value="${ oper.dateManipulation }" /></td>
                                <td><c:out value="${ oper.patientId }" /></td>
                                <td><c:out value="${ oper.nameManipulation }" /></td>
                                <td><c:out value="${ oper.description }" /></td>
                                <td><input type="radio" name="manipId" value="${oper.id}"></td>
                            </tr>  
                        </c:forEach>    
                    </table>  
                    <input type="submit" value="<fmt:message key="select"/>" >
                </c:if>   

            </form>

            <hr/>
            <%-----------------------------Patient registration-------------------------%>    
            <form method="POST" action="./Controller" >
                <input type="hidden" name="join" value="jump">
                <input type="hidden" name="jumpTo" value="patientReg">
                <input type="submit" value="<fmt:message key="join"/>">
            </form>
        </fmt:bundle>     

        <hr/>

        <%-------------------------------- Nurses list -----------------------------------%>
        <fmt:bundle basename="resources.i18n.nurses">
        <caption><fmt:message key="caption"/></caption>
        <man:NursesTag/>
        <table border="5px">
            <th><fmt:message key="id"/></th> 
            <th><fmt:message key="login"/></th> 
            <th><fmt:message key="firstName"/></th> 
            <th><fmt:message key="secondName"/></th>
            <th><fmt:message key="dateBirth"/></th>
            <th><fmt:message key="dateEstablish"/></th>
                <c:forEach var="nurse" items="${allNurses}" >
                <tr>
                    <td><c:out value="${ nurse.id }" /></td>
                    <td><c:out value="${ nurse.login }" /></td>
                    <td><c:out value="${ nurse.firstName }" /></td>
                    <td><c:out value="${ nurse.secondName }" /></td>
                    <td><c:out value="${ nurse.dateBirth }" /></td>
                    <td><c:out value="${ nurse.dateEstablished }" /></td>
                </tr>
            </c:forEach>
        </table>
    </fmt:bundle>    

    <%---------------------Patients and its functions ---------------------------%>

    <fmt:bundle basename="resources.i18n.patients">
        <man:PatientsTag/>
        <caption><fmt:message key="caption"/></caption>
        <form method="POST" action="./Controller">
            <table border="5px">
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="login"/></th> 
                <th><fmt:message key="firstName"/></th> 
                <th><fmt:message key="secondName"/></th>
                <th><fmt:message key="dateBirth"/></th>
                <th><fmt:message key="diagnoseStart"/></th>
                <th><fmt:message key="dateAppoint"/></th>
                <th><fmt:message key="diagnoseFinish"/></th>
                <th><fmt:message key="dateDischarge"/></th>
                <th><fmt:message key="select"/></th>
                    <c:forEach var="patients" items="${allPatients}" >
                    <tr>
                        <td><c:out value="${ patients.id }" /></td>
                        <td><c:out value="${ patients.login }" /></td>
                        <td><c:out value="${ patients.firstName }" /></td>
                        <td><c:out value="${ patients.secondName }" /></td>
                        <td><c:out value="${ patients.dateBirth }" /></td>
                        <td><c:out value="${ patients.diagnoseOnStart }" /></td>
                        <td><c:out value="${ patients.dateAppointment }" /></td>
                        <td><c:out value="${ patients.diagnoseOnFinish }" /></td>
                        <td><c:out value="${ patients.dateDischarge }" /></td>
                        <td><input type="radio" name="patient" value="${ patients.id }"></td>
                    </tr>  
                </c:forEach>    
            </table>
            <br/>
            <select name="join" required>
                <option value="Discharge"><fmt:message key="discharge"/></option>
                <option value="History"><fmt:message key="history"/></option>
            </select>
            <br/>
            <input type="text" name="discharge" maxlength="100">
            <br/>
            <input type="submit" value="<fmt:message key="confirm"/>" >
        </form>
    </fmt:bundle>            
    <br/>  

    <%------------------------------Appoint manipulation---------------------%>    
    <hr/>
    <fmt:bundle basename="resources.i18n.manipulations">
        <form method="POST" action="./Controller" >
            <input type="hidden" name="join" value="jump">
            <input type="hidden" name="jumpTo" value="appointManipulation">
            <input type="submit" value="<fmt:message key="add"/>">
        </form>
    </fmt:bundle> 

    <%------------------------------------------------------------------------------%>
    <%@include file="manipulations.jsp" %>   
    <%@include file="changePassword.jsp" %>
    <%@include file="retirement.jsp" %>
    <br/>
    <fmt:bundle basename="resources.i18n.main">
        <a href="Controller?join=logout"><fmt:message key="logOut"/></a>
    </fmt:bundle>
</body>
</html>

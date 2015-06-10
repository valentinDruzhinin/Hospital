<%-- 
    The page which show all patient manipulations.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <fmt:bundle basename="resources.i18n.manipulations"> 
            <br/>
<%------------------------ The input of patient manipulations ---------------------%>
            <form method="POST" action="./Controller" >

                <c:if test="${not empty patientMed }" >
                    <p><fmt:message key="medicationsShedule"/></p>
                    <input type="hidden" name="manipType" value="medication">
                    <table border="5px">
                        <th><fmt:message key="id"/></th> 
                        <th><fmt:message key="date"/></th> 
                        <th><fmt:message key="performer"/></th>
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="appointer"/></th>
                            <c:forEach var="medicIter" items="${patientMed}" >
                            <tr>
                                <td><c:out value="${ medicIter.id }" /></td>
                                <td><c:out value="${ medicIter.dateManipulation }" /></td>
                                <td><c:out value="${ medicIter.performerId }" /></td>
                                <td><c:out value="${ medicIter.patientId }" /></td>
                                <td><c:out value="${ medicIter.nameManipulation }" /></td>
                                <td><c:out value="${ medicIter.description }" /></td>
                                <td><c:out value="${ medicIter.appointerId }" /></td>
                                <td><input type="radio" name="manipId" value="${medicIter.id}"</td>
                            </tr>  
                        </c:forEach>    
                    </table>
                    <input type="submit" name="join" value="Delete" >
                    <br/>
                    <input type="date" name="modifyDate" >
                    <br/>
                    <input type="textarea" name="modifyDesc" maxlength="200" >
                    <br/>
                    <input type="submit" name="join" value="Modify" >
                </c:if>

                <%----------------------------------------------------------------------------------%>


                <c:if test="${not empty patientOper}">
                    <br/>
                    <caption><fmt:message key="operationsShedule"/></caption>
                    <input type="hidden" name="manipType" value="operation">
                    <table border="5px">
                        <th><fmt:message key="id"/></th> 
                        <th><fmt:message key="date"/></th> 
                        <th><fmt:message key="performer"/></th>
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                            <c:forEach var="oper" items="${patientOper}" >
                            <tr>
                                <td><c:out value="${ oper.id }" /></td>
                                <td><c:out value="${ oper.dateManipulation }" /></td>
                                <td><c:out value="${ oper.performerId }" /></td>
                                <td><c:out value="${ oper.patientId }" /></td>
                                <td><c:out value="${ oper.nameManipulation }" /></td>
                                <td><c:out value="${ oper.description }" /></td>
                                <td><input type="radio" name="manipId" value="${oper.id}"</td>
                            </tr>  
                        </c:forEach>    
                    </table>
                    <input type="submit" name="join" value="Delete" >
                    <br/>
                    <input type="date" name="modifyDate" >
                    <br/>
                    <input type="textarea" name="modifyDesc" maxlength="200" >
                    <br/>
                    <input type="submit" name="join" value="Modify" >

                </c:if>

                <%-------------------------------------------------------------%>            
                <c:if test="${ not empty patientProc }">
                    <br/>
                    <caption><fmt:message key="proceduresShedule"/></caption>
                    <input type="hidden" name="manipType" value="procedure">
                    <table border="5px">
                        <th><fmt:message key="id"/></th> 
                        <th><fmt:message key="date"/></th> 
                        <th><fmt:message key="performer"/></th>
                        <th><fmt:message key="patient"/></th>
                        <th><fmt:message key="nameManipulation"/></th>
                        <th><fmt:message key="description"/></th>
                        <th><fmt:message key="appointer"/></th>
                            <c:forEach var="proc" items="${patientProc}" >
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
                    <input type="submit" name="join" value="Delete" >
                    <br/>
                    <input type="date" name="modifyDate" >
                    <br/>
                    <input type="textarea" name="modifyDesc" maxlength="200" >
                    <br/>
                    <input type="submit" name="join" value="Modify" >
                </c:if>
                <c:if test="${notSetId}">
                    <fmt:message key="notId"/>
                </c:if>
            </form>  
            <br/>
        </fmt:bundle>
    </body>
</html>

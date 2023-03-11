<%-- 
    Document   : home
    Created on : Feb 24, 2023, 7:11:08 PM
    Author     : axyres
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Quiz Online</title>
    </head>
    <body>

        <a href="Logout" >Logout</a>
        <a href="History">History</a>
        <h1>Welcome ${sessionScope.LOGIN_USER.fullname}</h1>  
        <form action="MakeQuiz" >
            <h3>Select subject to do quiz : <select name="cmbSubject" >
                    <c:forEach var="subjectDTO" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <option value="${subjectDTO.subjectID}">${subjectDTO.subjectName}</option>
                    </c:forEach>
                </select>
            </h3>
            <input type="submit" name="btnAction" value="Take Quiz" />
        </form>


    </body>
</html>

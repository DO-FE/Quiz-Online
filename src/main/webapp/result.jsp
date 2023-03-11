<%-- 
    Document   : result
    Created on : Mar 2, 2023, 2:52:16 PM
    Author     : axyres
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h1>RESULT</h1>
        <h1>Welcome ${sessionScope.LOGIN_USER.fullname}</h1>  
        <table border="1">
            <thead>
                <tr>
                    <th>Total statement Correct</th>
                    <th>Total Score</th>
                    <th>Date submit</th>
                    <th>Subject</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${requestScope.ANSWER_CORRECT}</td>
                    <td>${requestScope.SCORE}</td>
                    <td>${requestScope.DATE_SUBMIT}</td>
                    <td>${requestScope.SUBJECT}</td>
                </tr>
            </tbody>
        </table>
        <a href="Home" >Take Quiz</a>
    </body>
</html>

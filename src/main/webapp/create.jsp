<%-- 
    Document   : create
    Created on : Mar 2, 2023, 3:11:27 PM
    Author     : axyres
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question Page</title>
    </head>
    <body>
        <c:if test="${requestScope.QUESTION_ERROR ==null}">
            <c:set var="requestScope.QUESTION_ERROR.questionErrorID" value=""/>
            <c:set var="requestScope.QUESTION_ERROR.question_contentError" value=""/>
            <c:set var="requestScope.QUESTION_ERROR.answer_contentError" value=""/>
        </c:if>
        
        <a href="Logout" class="logout">Logout</a><br>
        <a href="Admin" class="quiz">Roll back</a>
        <h2>Hello: ${sessionScope.LOGIN_USER.fullname}</h2>
        <div>     
            <h1>Create Question PAGE</h1>
            <c:if test="${sessionScope.LIST_ALL_SUBJECT !=null}">

                <form action="CreateQuestion" method="POST" >
                    Question ID<input type="text" name="txtQuestionID" value="${param.txtQuestionID==null?"":param.txtQuestionID}">                
                    ${requestScope.QUESTION_ERROR.questionErrorID}</br>
     
                    Question Content<input type="text" name="txtQuestionContent1" value="${param.txtQuestionContent1==null?"":param.txtQuestionContent1}">                   
                    ${requestScope.QUESTION_ERROR.question_contentError}</br>
                    
                    ${requestScope.QUESTION_ERROR.answer_contentError}</br>
                    1: <input type="text" name="txtAnswer1" ></br>
                    2: <input type="text" name="txtAnswer2" ></br>
                    3: <input type="text" name="txtAnswer3" ></br>
                    4: <input type="text" name="txtAnswer4" ></br>                    
                    
                    Correct <select name="cmbAnswerCorrect" class="answer">
                        <c:forEach var="count" begin="1" end="4">
                            <option value="${count}">${count}</option>
                        </c:forEach>   
                    </select></br>

                    Subject <select name="cmbSubject" >
                    <c:forEach var="subjectDTO" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <option value="${subjectDTO.subjectID}">${subjectDTO.subjectName}</option>
                    </c:forEach>
                </select></br>                                
                <input type="submit" name="btnAction" value="Create">
            </form>
        </c:if>
    </div>
</body>
</html>

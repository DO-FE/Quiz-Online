<%-- 
    Document   : update
    Created on : Mar 1, 2023, 1:33:10 PM
    Author     : axyres
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:set var="requestScope.QUESTION_ERROR.question_contentError" value=""/>
        <c:set var="requestScope.QUESTION_ERROR.answer_contentError" value=""/>
        <a href="Logout">Logout</a> <a href="Admin">RollBack</a><br>
        <h2>Hello Admin: ${sessionScope.LOGIN_USER.fullname}</h2>
        <div>     
            <h1>Update Question </h1>
            <c:if test="${sessionScope.LIST_SUBJECT != null}">
                <c:set var="answerCount" value="1"/>
                <form action="Update" method="POST">

                    Question ID : ${sessionScope.QUESTION_UPDATE.questionID}<br>
                    Create date : ${sessionScope.QUESTION_UPDATE.createDate}<br>

                    <input type="hidden" name="txtQuestionID" value="${sessionScope.QUESTION_UPDATE.questionID}" >
                    </br>

                    Question Content  <input type="text" name="txtQuestionContentt" value="${sessionScope.QUESTION_UPDATE.question_content}" width="200px" size="70px">
                    ${requestScope.QUESTION_ERROR.question_contentError}
                    </br>

                    <c:forEach var="answerDTO" varStatus="counter" items="${sessionScope.LIST_ANSWER_UPDATE}">
                        <input type="hidden" name="txtAnswerID${answerCount}" value="${answerDTO.answerID}">
                        Answer ${answerCount}: <input type="text" name="txtAnswerContent${answerCount}" value="${answerDTO.answer_content}"></br> 
                        <c:set var="answerCount" value="${answerCount+1}"/>
                    </c:forEach>                                          
                    ${requestScope.QUESTION_ERROR.answer_contentError}
                    </br>
                    Answer correct <select name="cmbAnswerCorrect">
                        <c:forEach var="answerDTO" varStatus="counter" items="${sessionScope.LIST_ANSWER_UPDATE}">
                            <c:if test="${answerDTO.answer_correct ==true}">
                                <option value="${answerDTO.answerID}" selected="true">Answer ${counter.count}</option>
                            </c:if>
                            <c:if test="${answerDTO.answer_correct !=true}">
                                <option value="${answerDTO.answerID}" >Answer ${counter.count}</option>
                            </c:if>
                        </c:forEach>    
                    </select></br>

                    <input type="hidden" name="txtAnswercount" value="${answerCount}">

                    Subject <select name="cmbSubject" >
                        <c:forEach var="subjectDTO" varStatus="counter" items="${sessionScope.LIST_SUBJECT}">
                            <c:if test="${sessionScope.QUESTION_UPDATE.subjectID ==subjectDTO.subjectID}">
                                <option value="${subjectDTO.subjectID}" selected="true">${subjectDTO.subjectName}</option>
                            </c:if>
                            <c:if test="${sessionScope.QUESTION_UPDATE.subjectID !=subjectDTO.subjectID}">
                                <option value="${subjectDTO.subjectID}">${subjectDTO.subjectName}</option>
                            </c:if>
                        </c:forEach>                                
                    </select></br>


                    Status<select name="cmbStatus" >
                        <c:if test="${sessionScope.QUESTION_UPDATE.status ==true}">
                            <option value="false">Inactive</option>
                            <option value="true" selected="true">Active</option>
                        </c:if>
                        <c:if test="${sessionScope.QUESTION_UPDATE.status !=true}">
                            <option value="false" selected="true">Inactive</option>
                            <option value="true">Active</option>
                        </c:if>
                    </select>         
                    </br>

                    <input type="submit" name="btnAction" value="Update Question" >

                </form>
                <c:if test="${requestScope.UPDATE_MESSAGE_ERROR !=null}">
                    <h3>${requestScope.UPDATE_MESSAGE_ERROR}</h3>
                </c:if>    

            </c:if>
        </div>
    </body>
</html>

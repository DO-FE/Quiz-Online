<%-- 
    Document   : admin
    Created on : Feb 28, 2023, 11:14:26 AM
    Author     : axyres
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>

        <a href="Logout" >Logout</a>
        <a href="PageCreateQuestion" >Create Question</a>
        <h1>Hello admin: ${sessionScope.LOGIN_USER.fullname}</h1>      
        <form action="Search" method="POST" > 
            Questions:
            <c:if test="${param.txtQuestionContent ==null}">
                <input type="text" name="txtQuestionContent" value="">
            </c:if>
            <c:if test="${param.txtQuestionContent !=null}">
                <input type="text" name="txtQuestionContent" value="${param.txtQuestionContent}">
            </c:if>   

            Subject:  
            <select name="cmbSubject" >
                <c:if test="${param.cmbSubject==null}">
                    <c:forEach var="subject" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <option value="${subject.subjectID}">${subject.subjectName}</option>                                   
                    </c:forEach>            
                        <option value="">All</option>
                </c:if>
                <c:if test="${param.cmbSubject==all}">    
                    <c:forEach var="subject" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <option value="${subject.subjectID}">${subject.subjectName}</option>                                   
                    </c:forEach>   
                        <option value="" selected="true">All</option>
                </c:if>    
                <c:if test="${param.cmbSubject!=null && param.cmbSubject!=all}">
                    <c:forEach var="subject" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <c:if test="${param.cmbSubject==subject.subjectID}">
                            <option value="${subject.subjectID}" selected="true">${subject.subjectName}</option>   
                        </c:if>                                   
                        <c:if test="${param.cmbSubject!=subject.subjectID}">
                            <option value="${subject.subjectID}">${subject.subjectName}</option>   
                        </c:if> 
                    </c:forEach>
                    <option value="">All</option>
                </c:if>
            </select>                 

            <input type="submit" name="btnAction" value="Search"/>
        </form>

        <c:if test="${requestScope.MESSAGE !=null}">
            <h3>${requestScope.MESSAGE}</h3>
        </c:if>


        <c:if test="${requestScope.LIST_QUESTION!=null}">
            <c:forEach var="subjectdto" varStatus="counter1" items="${requestScope.LIST_SUBJECT}">
                <c:forEach var="questiondto" varStatus="counter2" items="${requestScope.LIST_QUESTION}">

                    <c:if test="${subjectdto.subjectID==questiondto.subjectID}">
                        <form action="Update" method="POST" class="question-layout">
                            <div>
                                <h2>${subjectdto.subjectName}</h2><br>
                                <h4>${questiondto.question_content}</h4> 
                                <c:set var="count" value="1"/>
                                <c:forEach var="answerDTO" varStatus="counter3" items="${requestScope.LIST_ANSWER}">
                                    <c:if test="${questiondto.questionID==answerDTO.questionID}">
                                        <c:if test="${answerDTO.answer_correct==true}">
                                            <li id="dapan">${count}: ${answerDTO.answer_content}</li>
                                        </c:if>  
                                        <c:if test="${answerDTO.answer_correct==false}">
                                            <li>${count}: ${answerDTO.answer_content}</li>
                                        </c:if>
                                        <c:set var="count" value="${count+1}"/>
                                    </c:if>
                                </c:forEach>
                                <br> 
                                <a href="UpdatePage?txtQuestionID=${questiondto.questionID}" class="update">Update</a>
                                <c:if test="${questiondto.status==true}">
                                    <a href="Delete?txtQuestionID=${questiondto.questionID}&txtQuestionContent=${param.txtQuestionContent}&cmbStatus=${param.cmbStatus}&cmbSubject=${param.cmbSubject}" class="delete" onclick="return confirm('Do you want to delete?')">Delete</a>
                                </c:if>
                            </div>                                   
                        </form><br> <br> <br> 
                    </c:if>   
                </c:forEach>
            </c:forEach>            

                        <footer >
                            <div class="footer">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${requestScope.TOTAL_PAGE!=null && requestScope.TOTAL_PAGE>1}">
                                        <c:forEach var="count" begin="1" end="${requestScope.TOTAL_PAGE}">
                                            <li class="page-item">
                                                <a class="page-link" href="Search?txtCurrentPage=${count}&txtQuestionContent=${param.txtQuestionContent}&cmbStatus=${param.cmbStatus}&cmbSubject=${param.cmbSubject}">${count}</a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>   
                        </footer>
        </c:if>

    </body>
</html>

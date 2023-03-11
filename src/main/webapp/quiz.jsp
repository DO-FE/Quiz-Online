<%-- 
    Document   : quiz
    Created on : Mar 2, 2023, 2:45:14 PM
    Author     : axyres
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <title>Quiz Page</title>
    </head>
    <body>


        <c:if test="${sessionScope.QUIZ !=null && sessionScope.SUBJECT !=null && sessionScope.CURRENT_QUESTION !=null && sessionScope.LIST_CURRENT_ANSWER !=null}">
            <c:set var="index" value="-1"/>
            <c:forEach var="questionDTO" varStatus="counter" items="${sessionScope.QUIZ.listQuestion}">
                <c:if test="${questionDTO.questionID == sessionScope.CURRENT_QUESTION.questionID}">
                    <c:set var="index" value="${counter.count-1}"/>
                </c:if>
            </c:forEach>


            <h2>Subject : ${sessionScope.SUBJECT.subjectName}</h2>  
            <h2>Welcome : ${sessionScope.LOGIN_USER.fullname}</h2>  
            <c:if test="${requestScope.MESSAGE !=null && not empty requestScope.MESSAGE}">
                <h3 id="war">${requestScope.MESSAGE} ${sessionScope.SUBJECT.subjectName}</h5>
            </c:if>
            <form action="MakeQuiz"  >
                <input name="btnAction" type="submit" id="finish" value="FinishQuiz" >
                
                <div>                      
                    <h4>Question ${index+1} :<br>${sessionScope.CURRENT_QUESTION.question_content}</h4> 
                    <c:forEach var="answerDTO" varStatus="counter" items="${sessionScope.LIST_CURRENT_ANSWER}">
                        <c:if test="${sessionScope.QUIZ.listAnswerSelected[index] == answerDTO.answerID}">
                            <input name="radioAnswerSelected" type="radio" checked="true" value="${answerDTO.answerID}"/>${answerDTO.answer_content}<br>
                        </c:if>
                        <c:if test="${sessionScope.QUIZ.listAnswerSelected[index] != answerDTO.answerID}">
                            <input name="radioAnswerSelected" type="radio" value="${answerDTO.answerID}"/>${answerDTO.answer_content}<br>
                        </c:if>
                    </c:forEach>

                    <input name="needToSaveQuestion" type="hidden" value="${sessionScope.CURRENT_QUESTION.questionID}"/>
                    <br> 
                </div> 

                <nav>
                    <ul class="pagination justify-content-center">
                        <c:set var="count" value="1"/>
                        <label for="per" class="btn">Pervious</label>
                        <c:if test="${param.txtCurrentQuestion==null}">
                            <input id="per" name="txtCurrentQuestion" type="submit" value="1" style="visibility:hidden;">
                        </c:if>
                        <c:if test="${param.txtCurrentQuestion>1}">
                            <input id="per" name="txtCurrentQuestion" type="submit" value="${param.txtCurrentQuestion-1}" style="visibility:hidden;">
                        </c:if>
                        <c:if test="${param.txtCurrentQuestion<1}">
                            <input id="per" name="txtCurrentQuestion" type="submit" value="1" style="visibility:hidden;">
                        </c:if>    
                        <c:forEach  begin="1" end="${sessionScope.SUBJECT.numberQuestion}">
                            <li class="page-item">
                                <input class="page-link" name="txtCurrentQuestion" type="submit" value="${count}">
                                <c:set var="count" value="${count+1}"/>
                            </li>
                        </c:forEach>
                        <label for="next" class="btn">Next</label>
                        <c:if test="${param.txtCurrentQuestion==null}">
                            <input id="next" name="txtCurrentQuestion" type="submit" value="2" style="visibility:hidden;">
                        </c:if>
                        <c:if test="${param.txtCurrentQuestion<sessionScope.SUBJECT.numberQuestion}">
                            <input id="next"  name="txtCurrentQuestion" type="submit" value="${param.txtCurrentQuestion+1}" style="visibility:hidden;">
                        </c:if>
                        <c:if test="${param.txtCurrentQuestion>=sessionScope.SUBJECT.numberQuestion}">
                            <input id="next"  name="txtCurrentQuestion" type="submit" value="${sessionScope.SUBJECT.numberQuestion}" style="visibility:hidden;">
                        </c:if>
                           
                        
                    </ul>
                </nav>
                <div class="container">
                    <p>Count down:</p>
                    <p id="timeQuiz"></p>
                </div>
            </form>        
        </c:if>

        <c:if test="${requestScope.RESULT_MESSAGE !=null}">
            <h3>${requestScope.RESULT_MESSAGE}</h3>
            <a href="Home" >Take Quiz</a>
        </c:if>


       
        <script>
            var ngaystring= "${sessionScope.QUIZ.finishedQuizTime}";
            var countDownDate = new Date(ngaystring).getTime();  
            var x = setInterval(function() {
              var now = new Date().getTime();

              var distance = countDownDate - now;

              var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
              var seconds = Math.floor((distance % (1000 * 60)) / 1000);
              if (distance > 0) {
                document.getElementById("timeQuiz").innerHTML = minutes + ":" + seconds ;
              }
              if (distance < 0) {
                clearInterval(x);
                document.getElementById("timeQuiz").innerHTML = "Time out !" ;
                document.getElementById("finish").setAttribute('onclick','true');
                document.getElementById("finish").click();
              }
            }, 1000);
        </script>
    </body>
</html>

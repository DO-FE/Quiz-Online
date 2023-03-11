<%-- 
    Document   : login
    Created on : Feb 24, 2023, 7:11:17 PM
    Author     : axyres
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" dir="ltr">
    <head>
        <title>Login QuizOnline</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="css/all.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="container">
            <form action="Login" method="post">
                <div class="title">Login Quiz Online</div>
                <c:if test="${sessionScope.LOGIN_INVALID !=null && not empty sessionScope.LOGIN_INVALID}">
                    <h5>${sessionScope.LOGIN_INVALID}</h5>
                </c:if>
                <div class="input-box underline">
                    <input type="text" placeholder="Enter Your Email" name="txtEmail" required>
                    <div class="underline"></div>
                </div>
                <div class="input-box">
                    <input type="password" placeholder="Enter Your Password" name="txtPassword" required>
                    <div class="underline"></div>
                </div>
                <div class="input-box button">
                    <input type="submit" name="" value="Login">
                </div>
                <a href="#">Forgot your password?</a>
                <div class="option">Not have account ?
                    <a href="SignUp" class="create">Register</a>  
                </div>
                <div class="option">or Connect With Social Media</div>
                <div class="twitter">
                    <a href="#"><i class="fab fa-twitter"></i>Sign in With Twitter</a>
                </div>
                <div class="facebook">
                    <a href="#"><i class="fab fa-facebook-f"></i>Sign in With Facebook</a>
                </div>
            </form>
        </div>
    </body>
</html>



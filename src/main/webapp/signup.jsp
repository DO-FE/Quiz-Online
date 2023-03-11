   <%-- 
    Document   : signup
    Created on : Feb 24, 2023, 7:11:29 PM
    Author     : axyres
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/all.css"/>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Quiz Online</title>
    </head>
    <body>
        <div class="container">
            <form action="SignUp" method="post">
                <c:if test="${requestScope.SIGNUP_ERROR ==null}">
                    <c:set var="requestScope.SIGNUP_ERROR.emailError" value=""/>
                    <c:set var="requestScope.SIGNUP_ERROR.fullNameError" value=""/>
                    <c:set var="requestScope.SIGNUP_ERROR.passwordError" value=""/>
                    <c:set var="requestScope.SIGNUP_ERROR.confirmError" value=""/>
                    <c:set var="requestScope.SIGNUP_ERROR.phoneError" value=""/>
                </c:if>
                <div class="title">Sign Up</div>
                <div class="input-box underline">
                    <input type="text" placeholder="Enter Your Email" name="txtEmail" required>
                    <div class="underline"></div>
                </div>
                <div class="input-box">
                    <input type="password" placeholder="Enter Your Password" name="txtPassword" required>
                    <div class="underline"></div>
                </div>
                <div class="input-box">
                    <input type="password" placeholder="Enter Again to confirm Password" name="txtConfirm" required>
                    <div class="underline"></div>
                </div>
                <div class="input-box">
                    <input type="text" placeholder="Enter Your Full Name" name="txtFullname" required>
                    <div class="underline"></div>
                </div>
                <div class="input-box">
                    <input type="text" placeholder="Enter Your Phone" name="txtPhone" required>
                    <div class="underline"></div>
                </div>
                <div class="input-box button">
                    <input type="submit" name="" value="Sign Up">
                </div>
                <a class="option" href="login.jsp">Have already account?</a>
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

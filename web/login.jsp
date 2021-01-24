<%-- 
    Document   : login
    Created on : Jan 21, 2021, 7:12:53 PM
    Author     : Banh Bao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="resources/css/login.css">
        <title>Login</title>
    </head>
    <body class="bg-gradient-primary">
        <div class="container-fluid">
            <div class="row no-gutter">
                <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                <div class="col-md-8 col-lg-6">
                    <div class="login d-flex align-items-center py-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-9 col-lg-8 mx-auto">
                                    <h3 class="login-heading mb-4"><stronng>Welcome!</stronng></h3>

                                    <form action="Login" method="POST">
                                        <div class="form-label-group">
                                            <input type="text" id="inputEmail" class="form-control"
                                                   placeholder="Email" name="txtEmail" value="${param.txtEmail}" 
                                                   maxlength="50" required autofocus>
                                            <label for="inputEmail">Email</label>
                                        </div>
                                        <br>
                                        <div class="form-label-group">
                                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="txtPassword" maxlength="50" required>
                                            <label for="inputPassword">Password</label>
                                        </div>
                                        <c:if test="${not empty requestScope.LOGIN_ERROR}">
                                            <font style="color: red">${requestScope.LOGIN_ERROR}</font>
                                        </c:if>
                                        <br>
                                        <input class="btn btn-lg btn-success btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit" value="Login"/>
                                        <hr class="my-4">
                                        <div class="d-flex justify-content-between">
                                            <div id="my-signin2" class="mr-auto ml-auto"></div>
                                        </div>

                                    </form>
                                   
                                    <a class="btn btn-lg btn-danger btn-block btn-login text-uppercase font-weight-bold mb-2" href="RegistrationPage">Registration</a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>

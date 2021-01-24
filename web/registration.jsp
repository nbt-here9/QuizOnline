<%-- 
    Document   : registration
    Created on : Jan 23, 2021, 3:38:14 PM
    Author     : Banh Bao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="resources/css/registration.css">
        <link rel="stylesheet" type="text/css" href="resources/css/login.css">
        <title>Create New Account</title>
    </head>
    <body>
       <jsp:include page="WEB-INF/header/header.jsp" flush="true"/>
            <div class="container-fluid">
                <div class="row no-gutter">
                    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                    <div class="col-md-8 col-lg-6">
                        <div class="login d-flex align-items-center py-5">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-9 col-lg-8 mx-auto">
                                    
                                    <h3 class="login-heading mb-4">Create New Account</h3>
                                    <form action="Registration" method="POST">
                                        <div class="form-label-group">
                                            <input type="text" id="inputEmail" class="form-control" placeholder="Email" name="txtEmail" value="${param.txtEmail}" maxlength="50" required autofocus>
                                            <label for="inputEmail">Username</label>
                                        </div>
                                        <c:if test="${not empty requestScope.ERROR.emailExistedErr}">
                                            <font style="color: red">${requestScope.ERROR.emailExistedErr}</font>
                                        </c:if>
                                        <c:if test="${not empty requestScope.ERROR.emailLengthErr}">
                                            <font style="color: red">${requestScope.ERROR.emailLengthErr}</font>
                                        </c:if>
                                        <div class="form-label-group">
                                            <input type="text" id="inputName" class="form-control" placeholder="Name" name="txtName"
                                                   <c:if test="${not empty param.txtName}">value="${param.txtName}"</c:if>
                                                       maxlength="100" required>
                                                   <label for="inputName">Name</label>
                                            </div>
                                        <c:if test="${not empty requestScope.ERROR.nameLengthErr}">
                                            <font style="color: red">${requestScope.ERROR.nameLengthErr}</font>
                                        </c:if>
                                        
                                        <div class="form-label-group">
                                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="txtPassword" maxlength="50" required>
                                            <label for="inputPassword">Password</label>
                                        </div>
                                        <c:if test="${not empty requestScope.ERROR.passwordLengthErr}">
                                            <font style="color: red">${requestScope.ERROR.passwordLengthErr}</font>
                                        </c:if>
                                        <div class="form-label-group">
                                            <input type="password" id="inputConfirm" class="form-control" placeholder="Confirm" name="txtConfirm" maxlength="64" required>
                                            <label for="inputConfirm">Confirm</label>
                                        </div>
                                        <c:if test="${not empty requestScope.ERROR.confirmNotMatchedErr}">
                                            <font style="color: red">${requestScope.ERROR.confirmNotMatchedErr}</font>
                                        </c:if>
                                        <button class="btn btn-lg btn-warning btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Registration</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

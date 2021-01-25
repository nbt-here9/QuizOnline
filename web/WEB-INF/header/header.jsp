<%-- 
    Document   : header
    Created on : Jan 5, 2021, 5:54:52 PM
    Author     : Banh Bao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #28a745">
        <a class="navbar-brand" style="color: #ffffff"><strong>Quiz Online</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form style="width: 1100px;">
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item" style="padding-right: 5px">
                        <a class="nav-link">
                            <c:if test="${sessionScope.LOGIN_USER.role eq 0}">
                                <a href="DashBoard" class="btn btn-outline-light">
                                    <font style="color: #ffffff;">Home</font>
                                </a>
                            </c:if>
                            <c:if test="${sessionScope.LOGIN_USER.role eq 1}">
                                <a href="AdminPage" class="btn btn-outline-light">
                                    <font style="color: #ffffff;">Home</font>
                                </a>
                            </c:if>
                        </a>
                    </li>
                    
                    <c:if test="${sessionScope.LOGIN_USER.role eq 1}">
                        <li class="nav-item">
                            <a class="nav-link">
                                <a href="CreatePage" class="btn btn-outline-light">
                                    <font style="color: #ffffff;">Create</font>
                                </a>
                            </a>
                        </li>
                    </c:if>
                </ul>
                <span class="navbar-text">
                    <ul class="navbar-nav mr-auto">


                        <c:if test="${empty sessionScope.LOGIN_USER}">

                            <li class="nav-item" style="padding-right: 5px">
                                <a class="nav-link">
                                    <a href="RegistrationPage" class="btn btn-outline-light">
                                        <font style="color: #ffffff;">Registration</font>
                                    </a>
                                </a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link">
                                    <a href="LoginPage" class="btn btn-outline-light">
                                        <font style="color: #ffffff;">Login</font>
                                    </a>

                                </a>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.LOGIN_USER}">

                            <span class="nav-item" style="padding-right: 10px;">
                                <font style="color: #ffffff;"> Welcome, ${sessionScope.LOGIN_USER.name}</font>
                            </span>

                            <li class="nav-item">
                                <a href="LogOut" class="btn btn-outline-light">
                                    <font style="color: #ffffff;">Log Out</font>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </span>
            </div>
        </form>
    </nav>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" crossorigin="anonymous"></script>

</header>
</html>


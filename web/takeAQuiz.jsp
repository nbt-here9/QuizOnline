<%-- 
    Document   : takeAQuiz
    Created on : Jan 27, 2021, 7:42:58 AM
    Author     : Banh Bao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="resources/css/admin.css">
        <link rel="stylesheet" type="text/css" href="resources/css/simplePagination.css">


        <script src="resources/js/jquery-3.5.1.min.js"></script>
        <script src="resources/js/jquery.simplePagination.js"></script>
        <script src="resources/js/pagination.js"></script>


        <title>Quiz Now</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/header/header.jsp" flush="true"/>
        <h2 class="text-center text-muted">
            <c:if test="${not empty sessionScope.SUBJECT_NAME}">${sessionScope.SUBJECT_NAME}</c:if> Quiz
            </h2>
        <c:if test="${not empty sessionScope.LIST_QUES_FOR_QUIZ}">

            <h2 class="counter-time" id="showtime"></h2>

            <div class="container">
                <div class="quiz-form pt-2">

                    <c:forEach var="question" items="${sessionScope.LIST_QUES_FOR_QUIZ.ques}" varStatus="counter">
                        <div class="paginate">
                            <table class="table table-hover">
                                <thead class="thead-light">
                                    <tr>
                                        <th colspan="2"> ${question.key} </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="answer" items="${question.value}" varStatus="counterAns">

                                        <tr>
                                            <td width="8%">
                                                <input type="radio" name="rdAnsCorrect${counterAns.count}" value="${counterAns.count}" />
                                                <c:if test="${answer.isTrue}">

                                                </c:if>

                                                <c:if test="${not answer.isTrue}">

                                                </c:if>
                                            </td>
                                            <td>${answer.ansContent}</td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>

                    </c:forEach>

                </div>

            </div>
            <div id="page-nav"></div>

        </c:if>

    </body>
</html>

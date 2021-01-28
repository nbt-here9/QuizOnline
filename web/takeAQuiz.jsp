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

            <h3 class="counter-time" id="quiz-time-left"></h3>

            <form action="SubmitQuiz" name="quiz" method="POST">
                <div class="container">
                    <div class="quiz-form pt-2">

                        <c:forEach var="question" items="${sessionScope.LIST_QUES_FOR_QUIZ.ques}" varStatus="counter">
                            <div class="paginate">
                                <table class="table table-hover">
                                    <thead class="thead-light">
                                        <tr>
                                            <th colspan="2">Q${counter.count}: ${question.key} </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="answer" items="${question.value}" varStatus="counterAns">

                                            <tr>
                                                <td width="8%">
                                                    <input type="radio" name="rdAnsCorrect${counter.count}" value="${counterAns.count}" />
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
                <div class="text-center text-muted">
                    <input class="btn btn-success" style="padding:5px 40px; margin-bottom: 30px"type="submit" name="" value="Submit"/>
                </div>
            </form>
        </c:if>

        <script>
            var total_seconds = 60 *${sessionScope.TIME};
            var c_minutes = parseInt(total_seconds / 60);
            var c_seconds = parseInt(total_seconds % 60);
            function CheckTime() {
                document.getElementById("quiz-time-left").innerHTML
                        = 'Time Left: ' + c_minutes + "m:" + c_seconds + "s";
                if (total_seconds <= 0) {
                    setTimeout('document.quiz.submit()', 1);
                } else {
                    total_seconds = total_seconds - 1;
                    c_minutes = parseInt(total_seconds / 60);
                    c_seconds = parseInt(total_seconds % 60);
                    setTimeout("CheckTime()", 1000);
                }
            }
            setTimeout("CheckTime()", 1000);
        </script>
    </body>
</html>

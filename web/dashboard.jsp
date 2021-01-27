<%-- 
    Document   : dashboard
    Created on : Jan 23, 2021, 5:30:42 PM
    Author     : Banh Bao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="resources/css/admin.css">
        <title>Dashboard</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/header/header.jsp" flush="true"/>
        <h1 class="text-center text-muted">Dashboard</h1>
        <h3 class="text-center text-muted">Your Courses</h3>

        <div style="width: 700px; margin: 10px auto">
            <table class="table table-hover">
                <thead class="thead-light">
                    <tr>
                        <th>Subject Code</th>
                        <th>Subject Name</th>
                        <th class="text-center">Number of question</th>
                        <th class="text-center">Time</th>
                        <th class="text-center">Take a quiz</th>
                    </tr>
                </thead>
                <tbody>


                    <c:forEach var="subject" items="${requestScope.SUBJECT_LIST}">
                    <form action="TakeAQuiz">
                        <tr>
                            <td>${subject.subjectID}
                                <input type="hidden" name="txtSubjectID" value="${subject.subjectID}" />
                            </td>
                            <td>${subject.subjectName}
                            <input type="hidden" name="txtSubjectName" value="${subject.subjectName}" />
                            </td>
                            <td class="text-center">${subject.numOfQues}
                            <input type="hidden" name="txtNumOfQues" value="${subject.numOfQues}" />
                            </td>
                            <td class="text-center">${subject.timeOfQues} (min)
                            <input type="hidden" name="txtTimeOfQues" value="${subject.timeOfQues}" />
                            </td>
                            <td class="text-center">
                                <button type="submit" class="btn btn-danger btn-just-icon btn-sm" 
                                        data-original-title="Take a quiz"  title="Take a quiz">
                                    <i class="material-icons">edit</i>
                                </button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </body>
</html>

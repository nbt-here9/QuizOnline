<%-- 
    Document   : update
    Created on : Jan 26, 2021, 8:18:30 AM
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
        <title>Update Question</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/header/header.jsp" flush="true"/>
        <h1 class="text-center text-muted" style="padding-bottom: 20px">Update Question</h1>


        <c:if test="${not empty requestScope.CREATE_ERR}">
            <c:if test="${not empty requestScope.CREATE_ERR.noSubject}">
                <div class="alert alert-danger alert-dismissible mt-3 mb-3 ml-3 mr-3">${requestScope.CREATE_ERR.noSubject}</div>
            </c:if>

            <c:if test="${not empty requestScope.CREATE_ERR.noQuestion}">
                <div class="alert alert-danger alert-dismissible mt-3 mb-3 ml-3 mr-3">${requestScope.CREATE_ERR.noQuestion}</div>
            </c:if>

            <c:if test="${not empty requestScope.CREATE_ERR.notEnoughFourAnwsers}">
                <div class="alert alert-danger alert-dismissible mt-3 mb-3 ml-3 mr-3">${requestScope.CREATE_ERR.notEnoughFourAnwsers}</div>
            </c:if>

            <c:if test="${not empty requestScope.CREATE_ERR.notSetCorrectAnswer}">
                <div class="alert alert-danger alert-dismissible mt-3 mb-3 ml-3 mr-3">${requestScope.CREATE_ERR.notSetCorrectAnswer}</div>
            </c:if>

        </c:if>

        <c:if test="${not empty requestScope.CREATE_FAIL}">
            <div class="alert alert-danger alert-dismissible mt-3 mb-3 ml-3 mr-3">Create Failed!</div>
        </c:if>



        <form action="Update" method="POST" style="width: 700px; margin: 0 auto">
            <div class="text-center text-muted">
                <div class="row mb-4">
                    <div class="col-md-6 form-group mb-0">
                        Subject: <select style="width: 150px" name="txtSubject">
                            <option></option>
                            <c:forEach var="subject" items="${requestScope.SUBJECT_LIST}">
                                <option 
                                    <c:if test="${requestScope.SUBJECT_NAME eq subject.subjectName}">
                                        selected="true"
                                    </c:if>>
                                    ${subject.subjectName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>                            
                    <div class="col-md-6 form-group mb-0">
                        Status: <select style="width: 150px" name="txtStatus">
                            <option></option>
                            <c:forEach var="status" items="${requestScope.STATUS_LIST}">
                                <option 
                                    <c:if test="${requestScope.STATUS eq status}">
                                        selected="true"
                                    </c:if>>
                                    ${status}
                                </option>
                            </c:forEach>
                        </select>
                        <br/>

                    </div>
                </div>  
            </div>

            <div class="text-center text-muted">
                <strong>Question: </strong><br>
                <textarea name="txtQuestion" rows="4" cols="90">${requestScope.QUES.quesContent}</textarea>
                <input type="hidden" name="txtQuestionID" value="${requestScope.QUES_ID}" />
            </div>
            <br>

            <div style="margin-top: 10px">
                <table class="table table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th>Answer <br>
                                Correct</th>
                            <th class="text-center">Answer Content</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="answer" items="${requestScope.ANS_LIST}" varStatus="counter">
                            <tr>
                                <td width="8%">
                                    <c:if test="${answer.isTrue}">
                                        <i class="material-icons">check</i>
                                    </c:if>

                                    <c:if test="${not answer.isTrue}">
                                        <i class="material-icons">clear</i>
                                    </c:if>
                                </td>
                                <td><input type="text" name="txtAns${counter.count}" value="${answer.ansContent}" style="width: 550px"/></td>
                                <td width="8%">
                                    <input type="radio" name="rdAnsCorrect" value="${counter.count}" />
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>

            <div class="text-center text-muted">
                <input class="btn btn-success" style="padding:5px 40px; margin-bottom: 30px"type="submit" name="btAction" value="Update"/>
            </div>
            
        </form>
    </body>
</html>

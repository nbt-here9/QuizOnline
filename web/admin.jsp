<%-- 
    Document   : admin
    Created on : Jan 23, 2021, 5:30:18 PM
    Author     : Banh Bao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="resources/css/admin.css">
        <title>Admin Page</title>
    </head>
    <body>

        <jsp:include page="WEB-INF/header/header.jsp" flush="true"/>
        <h1 class="text-center text-muted">Admin Page</h1>

        <c:if test="${not empty requestScope.CREATE_SUCCESS}">
            <div class="alert alert-success alert-dismissible mt-3 mb-3 ml-3 mr-3">Create Question Successfully!</div>
        </c:if>

        <c:if test="${not empty requestScope.UPDATE_SUCCESS}">
            <div class="alert alert-success alert-dismissible mt-3 mb-3 ml-3 mr-3">Update Question Successfully!</div>
        </c:if>

        <c:if test="${not empty requestScope.REMOVE_SUCCESS}">
            <div class="alert alert-success alert-dismissible mt-3 mb-3 ml-3 mr-3">${requestScope.REMOVE_SUCCESS}</div>
        </c:if>



        <c:if test="${not empty requestScope.NOTI}">
            <div class="alert alert-danger alert-dismissible mt-3 mb-3 ml-3 mr-3">${requestScope.NOTI}</div>
        </c:if>



        <form style="width: 500px; margin: 0 auto" action="Search" method="POST">
            <div class="text-center text-muted">
                <div class="row mb-4">
                    <div class="col-md-6 form-group mb-0">
                        Subject: <select style="width: 150px" name="txtSubject">
                            <option></option>
                            <c:forEach var="subject" items="${requestScope.SUBJECT_LIST}">
                                <option 
                                    <c:if test="${param.txtSubject eq subject.subjectName}">
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
                                    <c:if test="${param.txtStatus eq status}">
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
                Question:  <input style="width: 400px" type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
            </div>
            <br>

            <div class="text-center text-muted">
                <input class="btn btn-success" type="submit" name="" value="Search"/>
            </div>

        </form>



        <c:if test="${not empty requestScope.LIST_QUESTION}">

            <form action="UpdatePage" method="GET">

                <c:forEach var="question" items="${requestScope.LIST_QUESTION.ques}" varStatus="counter">
                    <div style="width: 700px; margin: 10px auto">

                        <table class="table table-hover">
                            <thead class="thead-light">
                                <tr>
                                    <th>


                                        <button type="submit" class="btn btn-success btn-just-icon btn-sm" 
                                                data-original-title="Update"  title="Update" value="Edit">
                                            <i class="material-icons">edit</i>
                                        </button>

                                    </th>
                                    <th>
                                        ${question.key}
                                    </th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="answer" items="${question.value}" varStatus="counterAns">

                                <form action="Delete" id="DeleteQuestion" method="POST"></form>

                                <c:if test="${(counter.count eq 1) and (counterAns.count eq 1)}">
                                    <input type="hidden" name="txtQuestionID" value="${answer.quesID}" />

                                    <input type="hidden" name="txtQuestionID" value="${answer.quesID}" form="DeleteQuestion"/>
<!--                                    <input type="hidden" name="txtSubject" value="${subject.subjectName}" form="DeleteQuestion"/>
                                    <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" form="DeleteQuestion"/>-->
                                </c:if>
                                <tr>
                                    <td width="8%">
                                        <c:if test="${answer.isTrue}">
                                            <i class="material-icons">check</i>
                                        </c:if>

                                        <c:if test="${not answer.isTrue}">
                                            <i class="material-icons">clear</i>
                                        </c:if>
                                    </td>
                                    <td>${answer.ansContent}</td>
                                </tr>
                            </c:forEach>

                            <button type="submit" onclick="return confirm('Are you sure to delete this question?')" 
                                    form="DeleteQuestion"
                                    class="btn btn-danger btn-just-icon btn-sm" data-original-title="Remove" title="Remove" 
                                    name="Action" value="Remove">
                                <i class="material-icons">close</i>
                            </button>

                            </tbody>
                        </table>
                    </div>
                </c:forEach>

            </form>
        </c:if>




    </body>
</html>

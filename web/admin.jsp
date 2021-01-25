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

        <c:if test="${not empty requestScope.NOTI}">
            <div class="alert alert-success alert-dismissible mt-3 mb-3 ml-3 mr-3">${requestScope.NOTI}</div>
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
            
            <c:forEach var="question" items="${requestScope.LIST_QUESTION.ques}">
                <div STYLE="width: 600px; margin: 10px auto">

                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th colspan="2">
                                    ${question.key}
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="8%">
                                    <input type="radio" name="rdAnsCorrect" value="0" />
                                </td>
                                <td><input type="text" name="txtAnsA" value="" style="width: 380px"/></td>
                            </tr>
                            <tr>
                                <td width="8%">
                                    <input type="radio" name="rdAnsCorrect" value="1" />
                                </td>
                                <td><input type="text" name="txtAnsB" value="" style="width: 380px"/></td>
                            </tr>
                            <tr>
                                <td width="8%">
                                    <input type="radio" name="rdAnsCorrect" value="2" />
                                </td>
                                <td><input type="text" name="txtAnsC" value="" style="width: 380px"/></td>
                            </tr>
                            <tr>
                                <td width="8%">
                                    <input type="radio" name="rdAnsCorrect" value="3" />
                                </td>
                                <td><input type="text" name="txtAnsD" value="" style="width: 380px"/></td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </c:if>




    </body>
</html>

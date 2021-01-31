<%-- 
    Document   : history
    Created on : Jan 28, 2021, 10:13:53 AM
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


        <title>History</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/header/header.jsp" flush="true"/>
        <h1 class="text-center text-muted" style="padding-bottom: 20px">History Page</h1>

        <form style="width: 500px; margin: 0 auto" action="SearchHistory" method="GET">
            <div class="text-center text-muted">


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

                <input class="btn btn-success" type="submit" name="" value="Search"/>

            </div>

        </form>

        <c:if test="${not empty requestScope.LIST_HISTORY}">
            <c:forEach var="history" items="${requestScope.LIST_HISTORY}">

                <div class="alert alert-success alert-dismissible mt-3 mb-3 ml-3 mr-3 text-center" style="margin: 1rem 15rem 1rem 15rem !important">
                    <strong>Score:${history.score} <br></strong> 
                    <strong>Date:</strong> ${history.startTime} - ${history.endTime}
                </div>

            </c:forEach>

        </c:if>


        <c:if test="${empty requestScope.LIST_HISTORY && not empty param.txtSubject}">
            <div class="alert alert-success alert-dismissible mt-3 mb-3 ml-3 mr-3 text-center" style="margin: 1rem 15rem 1rem 15rem !important">
                YOU DID NOT HAVE ANY QUIZ OF THIS SUBJECT!
            </div>
        </c:if>

    </body>
</html>

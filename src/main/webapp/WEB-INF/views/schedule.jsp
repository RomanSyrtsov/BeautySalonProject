<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 25.05.2021
  Time: 5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>
    <fmt:setBundle basename="app" var="lang"/>
</head>
<div>
<jsp:include page="_header.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="d-flex justify-content-center bd-highlight mb-3">
            <table class="table table-bordered" >
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <c:forEach items="${schedules.getDates()}" var="date">
                        <th scope="col">${date}</th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${schedules.getTimes()}" var="time">
                    <tr>
                        <th scope="row">${time}</th>
                        <c:forEach items="${schedules.getDates()}" var="date2">
                            <c:if test="${schedules.getIdByDateAndTime(time,date2) != 0}">
                                <c:if test="${schedules.getStatusByDateAndTime(time,date2) == 2}">
                                <form action="controller?command=changeStatus" method="post">
                                    <input type="hidden" name="recordIdField" value="${schedules.getIdByDateAndTime(time,date2)}">
                                    <td name="${schedules.getIdByDateAndTime(time,date2)}" style="background-color: #c6cfcf"><input type="submit" value="Complete"/></td>
                                </form>
                                </c:if>
                                <c:if test="${schedules.getStatusByDateAndTime(time,date2) == 1}">
                                <td name="${schedules.getIdByDateAndTime(time,date2)}" style="background-color: #b9ffc8"></td>
                                </c:if>
                                <c:if test="${schedules.getStatusByDateAndTime(time,date2) == 3}">
                                <td name="${schedules.getIdByDateAndTime(time,date2)}" style="background-color: #ff6d81"></td>
                                </c:if>
                            </c:if>
                            <c:if test="${schedules.getIdByDateAndTime(time,date2) == 0}">
                                    <td name="${schedules.getIdByDateAndTime(time,date2)}"></td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


    </div>
     <div class="row">
    <div><div style="width: 15px; height: 15px;
    display: inline-block;
    margin-right: 5px; background-color: #c6cfcf"></div>In process</div>
    <div><div style="width: 15px; height: 15px;
    display: inline-block;
    margin-right: 5px; background-color: #b9ffc8"></div>Confirmed</div>
    </div>
    </div>
</div>
</body>
</html>

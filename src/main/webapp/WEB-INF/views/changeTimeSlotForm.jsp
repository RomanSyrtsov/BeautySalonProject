<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 26.05.2021
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change time slot</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>
    <fmt:setBundle basename="app" var="lang"/>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="d-flex justify-content-center bd-highlight mb-3">
            <form action="controller?command=changeTimeSlotFormProcess" method="post">
                <table border="0">
                    <tr>
                        <td><label for="date">Select date</label></td>
                <td><select id="date" class="form-select" name="SelectDate">
                    <c:forEach items="${schedules1.getFreeDates()}" var="date">
                        <c:if test="${date == sessionScope.curDate}">

                            <option name="${date}" value="${date}" selected = "selected"onclick="this.form.submit()">${date}</option>
                        </c:if>
                        <c:if test="${date != sessionScope.curDate}">
                            <option name="${date}" value="${date}" onclick="this.form.submit()"/>${date}</option>
                        </c:if>
                    </c:forEach>
                </select></td>
                    </tr>
                <c:if test="${curDate != null}">
                    <tr>
                        <td><label for="time">Select time</label></td>
                    <td><select id="time" class="form-select" name="SelectTime">
                        <c:forEach items="${schedules1.getFreeTimeByDate(curDate)}" var="time">
                            <option name="${time}" value="${time}">${time}</option>
                        </c:forEach>
                    </select></td>
                    </tr>
                </c:if>
                    <tr>
                        <td>
                <input type="submit"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>

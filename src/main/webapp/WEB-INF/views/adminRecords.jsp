<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 26.05.2021
  Time: 6:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Records</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>
    <fmt:setBundle basename="app" var="lang"/>

    <style>
        table.table-bordered{
            border:1px solid black;
            margin-top:20px;
        }
        table.table-bordered > thead > tr > th{
            border:1px solid black;
        }
        table.table-bordered > tbody > tr > td{
            border:1px solid black;
        }
    </style>

</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="container mt-5">
    <form action="controller?command=selectmaster" method="POST" >

        <label for="SelectId">Select master: </label>
        <select  id="SelectId" class="Select_By_Masters_Form" name="SelectId">
            <c:forEach items="${masters}"  var="list1">
                <c:if test="${list1.getId() == sessionScope.masterId}">
                    <option selected="selected" name="${list1.getId()}" value="${list1.getId()}" onclick="this.form.submit()">${list1.getFirstname()} ${list1.getLastname()}</option>
                </c:if>
                <c:if test="${list1.getId() != sessionScope.masterId}">
                    <option name="${list1.getId()}" value="${list1.getId()}" onclick="this.form.submit()">${list1.getFirstname()} ${list1.getLastname()}</option>
                </c:if>
            </c:forEach>
        </select>
    </form>

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
                                    <td name="${schedules.getIdByDateAndTime(time,date2)}" style="background-color: #c6cfcf">
                                        <form>
                                            <input type="hidden" name="recordIdField" value="${schedules.getIdByDateAndTime(time,date2)}">
                                            <input type="hidden" name="dateField" value="${date2}">
                                            <input type="hidden" name="timeField" value="${time}">
                                            <input formaction="controller?command=cancelStatus" formmethod="post" type="submit" value="Cancel" />
                                            <input formaction="controller?command=changeTimeSlot" formmethod="post" type="submit" value="Change slot" />
                                        </form>
                                    </td>
                            </c:if>
                            <c:if test="${schedules.getStatusByDateAndTime(time,date2) == 1}">
                                <c:if test="${schedules.getPaymentInfoById(schedules.getIdByDateAndTime(time,date2)) == 0}">
                                    <td name="${schedules.getIdByDateAndTime(time,date2)}" style="background-color: #b9ffc8">
                                        Price: ${schedules.getPriceById(schedules.getIdByDateAndTime(time,date2))}
                                        <form>
                                            <input type="hidden" name="recordIdField" value="${schedules.getIdByDateAndTime(time,date2)}">
                                            <input formaction="controller?command=submitPayment" formmethod="post" type="submit" value="submit payment">
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${schedules.getPaymentInfoById(schedules.getIdByDateAndTime(time,date2)) == 1}">
                                    <td name="${schedules.getIdByDateAndTime(time,date2)}" style="background-color: #b9ffc8">
                                        Price: ${schedules.getPriceById(schedules.getIdByDateAndTime(time,date2))}

                                    </td>
                                </c:if>
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

</body>
</html>

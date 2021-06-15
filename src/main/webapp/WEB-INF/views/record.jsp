<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 20.05.2021
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
      <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>
  <fmt:setBundle basename="app" var="lang"/>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="container mt-5">

    <h3>${serviceContainer.getServiceNameById(service_id)}</h3>
    <h3><fmt:message key="services_jsp.price"
                     bundle="${lang}"/>: ${serviceContainer.getServicePriceById(service_id)}</h3>
    <form action="controller?command=selectmaster" method="POST" >

        <label for="SelectId"><fmt:message key="services_jsp.filter_by_master" bundle="${lang}"/></label>
        <select  id="SelectId" class="Select_By_Masters_Form" name="SelectId">
            <option></option>
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
                        <td name="${schedules.getIdByDateAndTime(time,date2)}" style="background-color: crimson"></td>
                    </c:if>
                    <c:if test="${schedules.getIdByDateAndTime(time,date2) == 0}">
                        <form id="newRecord${time}${date2}" action="controller?command=makeRecord" method="post">
                            <input type="hidden" name="dateField" value="${date2}">
                            <input type="hidden" name="timeField" value="${time}">
                            <td style="cursor: pointer" onclick="document.getElementById('newRecord${time}${date2}').submit()" name="${schedules.getIdByDateAndTime(time,date2)}"></td>
                        </form>
                    </c:if>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</div>
</body>
</html>

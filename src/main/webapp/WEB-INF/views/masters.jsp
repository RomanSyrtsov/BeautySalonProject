<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 10.05.2021
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Masters</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="app" var="lang"/>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>

<div class="container mt-5">
    <form action="controller?command=processmasters" method="POST" >
        <select class="Sorting_Form" name="Sorting">
            <option></option>
            <option action = "1" onclick="this.form.submit()" value="Sort by names"><fmt:message key="masters_jsp.sort_by_names" bundle="${lang}"/></option>
            <option action = "1" onclick="this.form.submit()" value="Sort by rate"><fmt:message key="masters_jsp.sort_by_rate" bundle="${lang}"/></option>
        </select>
    </form>
    <form action="controller?command=processmasters" method="POST" >
        <select class="Filter_By_Services_Form" name="Filter">
            <option value="all" onclick="this.form.submit()"></option>
            <c:forEach items="${services}"  var="list1">
                <c:if test="${list1.getId() == sessionScope.serviceId}">
                <option selected="selected" name="${list1.getId()}" value="${list1.getId()}" onclick="this.form.submit()">${list1. getServiceName()}</option>
                </c:if>
                <c:if test="${list1.getId() != sessionScope.serviceId}">
                    <option name="${list1.getId()}" value="${list1.getId()}" onclick="this.form.submit()">${list1. getServiceName()}</option>
                </c:if>
            </c:forEach>
        </select>
    </form>
    <div class="row">
        <c:forEach items="${masters}"  var="list1">
            <div class="col-sm">
                <div class="card text-white bg-dark mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${list1.getFirstname()} ${list1.getLastname()}</h5>
                        <p class="card-text"><fmt:message key="masters_jsp.phone_number" bundle="${lang}"/>: ${list1.getPhone_number()}</p>
                        <p class="card-text"><fmt:message key="masters_jsp.master_rate" bundle="${lang}"/>: ${list1.getMaster_rate()}</p>
                        <p class="card-text"><fmt:message key="masters_jsp.master_speciality" bundle="${lang}"/>: ${list1.getSpeciality_name()}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

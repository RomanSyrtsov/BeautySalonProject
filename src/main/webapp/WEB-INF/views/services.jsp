<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="ua.kharkiv.rsyrtsov.db.model.Service" %>
<%@ page import="ua.kharkiv.rsyrtsov.db.dao.ServiceDao" %><%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 07.05.2021
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Services</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
</head>
<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <div class="container mt-5">
        <form action="controller?command=processservices" method="POST" >
            <label for="Filter_By_Masters_Form">Filter by master</label>
            <select id="Filter_By_Masters_Form" name="Sorting">
                <option value="1L" onclick="this.form.submit()"></option>
            <c:forEach items="${masters}"  var="list1">
                <c:if test="${list1.getId() == sessionScope.master_id}">
                <option selected="selected" name="${list1.getId()}" value="${list1.getId()}" onclick="this.form.submit()">${list1.getFirstname()} ${list1.getLastname()}</option>
                </c:if>
                <c:if test="${list1.getId() != sessionScope.master_id}">
                    <option name="${list1.getId()}" value="${list1.getId()}" onclick="this.form.submit()">${list1.getFirstname()} ${list1.getLastname()}</option>
                </c:if>
            </c:forEach>
            </select>
        </form>
        <div class="row">
        <c:forEach items="${services}"  var="list1">
            <div class="col-sm">
                    <div class="card text-white bg-dark mb-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${list1.getServiceName()}</h5>
                        <p class="card-text">Price: ${list1.getServicePrice()}</p>
                        <p class="card-text">Time(minutes): ${list1.getServiceTime()}</p>
                        <c:if test="${loginedUser != null && loginedUser.getRoleId() == 1}">
                        <form action="controller?command=record_service" method="post">
                            <input name="Record" type="hidden" value="${list1.getId()}">
                            <input type="submit" value="record"/>
                        </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</body>
</html>

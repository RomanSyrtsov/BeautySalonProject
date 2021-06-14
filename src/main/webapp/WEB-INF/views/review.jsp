<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 31.05.2021
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Review</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
  <c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
  </c:if>
  <fmt:setBundle basename="app" var="lang"/>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="container">
  <div class="d-flex justify-content-center bd-highlight mb-3">

    <form action="controller?command=reviewProcess" method="post">
      <input name="recordIdField" type="hidden" value="${recordId}">
      <div class="form-group">
        <label for="reviewText">Leave a review</label>
        <input id = "reviewText" class="form-control" type="text" name="reviewText" value= "${user.login}" />
      </div>
      <input class="btn btn-primary" type="submit" value= "Submit" />
    </form>

  </div>
</div>
</body>
</html>

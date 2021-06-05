<%@ include file="/WEB-INF/views/taglib.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 06.05.2021
  Time: 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="app" var="lang"/>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
    <div align="center">
        <h1><fmt:message key="register_jsp.registerform" bundle="${lang}"/></h1>
        <form action="controller?command=registerprocess" method="post">
            <table style="width: 80%">
                <tr>
                    <td><fmt:message key="login_jsp.username" bundle="${lang}"/></td>
                    <td><input type="text" name = "login"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="login_jsp.password" bundle="${lang}"/></td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="register_jsp.email" bundle="${lang}"/></td>
                    <td><input type="text" name="email"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="register_jsp.firstname" bundle="${lang}"/></td>
                    <td><input type="text" name="firstname"></td>
                </tr>
                <tr>
                    <td><fmt:message key="register_jsp.lastname" bundle="${lang}"/></td>
                    <td><input type="text" name="lastname"></td>
                </tr>
                <tr>
                    <td><fmt:message key="masters_jsp.phone_number" bundle="${lang}"/></td>
                    <td><input type="text" name="phone_number"></td>
                </tr>
            </table>
            <input type="submit" value="<fmt:message key="submitButton" bundle="${lang}"/>"/>
        </form>
    </div>
</body>
</html>

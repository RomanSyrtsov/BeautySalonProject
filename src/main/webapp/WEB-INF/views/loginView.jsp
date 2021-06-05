<%@ include file="/WEB-INF/views/taglib.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 07.05.2021
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="app" var="lang"/>

</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>



<p style="color: red;">${errorString}</p>



    <div class="container">
        <div class="d-flex justify-content-center bd-highlight mb-3">
        <form method="POST" action="controller?command=processlogin">
        <h3><fmt:message key="login_jsp.loginpage" bundle="${lang}"/></h3>
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td><label for="userName"><fmt:message key="login_jsp.username" bundle="${lang}"/></label></td>
            <td><input id = "userName" class="form-control" type="text" name="userName" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td><label for="password"><fmt:message key="login_jsp.password" bundle="${lang}"/></label></td>
            <td><input id ="password" class="form-control" type="password" name="password" value= "${user.password}" /> </td>
        </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "<fmt:message key="submitButton" bundle="${lang}"/>" />
                <a href="${pageContext.request.contextPath}/controller?command=/"><fmt:message key="login_jsp.cancel" bundle="${lang}"/></a>
            </td>
        </tr>
    </table>

</form>
        </div>
    </div>
</body>
</html>

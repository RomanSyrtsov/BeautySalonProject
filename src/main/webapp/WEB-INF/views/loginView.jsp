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
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>



<p style="color: red;">${errorString}</p>



    <div class="container">
        <div class="d-flex justify-content-center bd-highlight mb-3">
        <form method="POST" action="controller?command=processlogin">
        <h3>Login Page</h3>
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td><label for="userName">User name</label></td>
            <td><input id = "userName" class="form-control" type="text" name="userName" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td><label for="password">Password</label></td>
            <td><input id ="password" class="form-control" type="password" name="password" value= "${user.password}" /> </td>
        </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/controller?command=/">Cancel</a>
            </td>
        </tr>
    </table>

</form>
        </div>
    </div>
</body>
</html>

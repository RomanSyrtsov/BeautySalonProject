<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 07.05.2021
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/" class="nav-link px-2 text-secondary">Home</a></li>
                <li><a href="/services" class="nav-link px-2 text-white">Services</a></li>
                <li><a href="/schedule" class="nav-link px-2 text-white">Schedule</a></li>
            </ul>

            <div class="text-end">
                <a href="/login" class="btn btn-outline-light me-2">Login</a>

                <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light me-2">Logout</a>

                <a href="/register" class="btn btn-warning">Sign-up</a>
                <span style="color:red">[ ${loginedUser.login} ]</span>
                <span style="color:red">${loginedUser.roleId}</span>
            </div>
        </div>

    </div>
</header>
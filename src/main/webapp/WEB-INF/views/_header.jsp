<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.kharkiv.rsyrtsov.db.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: farad
  Date: 07.05.2021
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="app" var="lang"/>
</head>

<header class="p-3 bg-dark text-white">

    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="controller?command=/" class="nav-link px-2 text-secondary"><fmt:message key="header_jsp.home" bundle="${lang}"/></a></li>

                <li><a href="controller?command=services" class="nav-link px-2 text-white"><fmt:message key="header_jsp.services" bundle="${lang}"/></a></li>

                <li><a href="controller?command=masters" class="nav-link px-2 text-white"><fmt:message key="header_jsp.masters" bundle="${lang}"/></a></li>

                <c:if test="${loginedUser.getRoleId() == 2}">
                <li><a href="controller?command=schedule" class="nav-link px-2 text-white"><fmt:message key="header_jsp.schedule" bundle="${lang}"/></a></li>
                </c:if>

                <c:if test="${loginedUser.getRoleId() == 3}">
                    <li><a href="controller?command=adminRecords" class="nav-link px-2 text-white"><fmt:message key="header_jsp.records" bundle="${lang}"/></a></li>
                </c:if>

                <li><form action="controller?command=app_localization" method="post">
                    <button class="btn btn-outline-light me-2" type="submit" name="locale" value="ru"><fmt:message key="header_jsp.button_ru" bundle="${lang}"/></button>
                    <button class="btn btn-outline-light me-2" type="submit" name="locale" value="en"><fmt:message key="header_jsp.button_en" bundle="${lang}"/></button>
                </form></li>

            </ul>

            <div class="text-end">

                <c:if test="${loginedUser != null}">
                    <form action="controller?command=logout" method="post">
                    <button class="btn btn-outline-light me-2"><fmt:message key="header_jsp.logout" bundle="${lang}"/></button>
                    </form>
                </c:if>

                <c:if test="${loginedUser == null}">

                <a href="controller?command=login" class="btn btn-outline-light me-2"><fmt:message key="header_jsp.login" bundle="${lang}"/></a>
                <a href="controller?command=register" class="btn btn-outline-light me-2"><fmt:message key="header_jsp.register" bundle="${lang}"/></a>

                </c:if>

                <span style="color:red">[ ${loginedUser.login} ]</span>
                <span style="color:red">${loginedUser.roleId}</span>
            </div>
        </div>

    </div>
</header>

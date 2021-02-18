<%--
  Created by IntelliJ IDEA.
  User: Vlados
  Date: 13.02.2021
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <style>
        <%@include file="/css/style.css"%>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <title><fmt:message key="registration"/></title>
</head>
<nav>
    <div class="lang">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <fmt:message key="lang"/>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="?lang=en"><fmt:message key="lang.en"/></a>
            <a class="dropdown-item" href="?lang=ua"><fmt:message key="lang.ua"/></a>
        </div>
    </div>
</nav>
<body class="d-flex flex-column min-vh-100">
<div class="sidenav">
    <div class="login-main-text">
        <h2><fmt:message key="registration"/></h2>
        <p><fmt:message key="login_or_register"/></p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">

        <div class="login-form">
            <form method="post" action="/registration">
                <div class="form-group">
                    <label><fmt:message key="fullName"/></label>
                    <input type="text" name="full_name" id="fullName" class="form-control"
                           placeholder=<fmt:message key="fullName"/>>
                </div>
                <div class="form-group">
                    <label><fmt:message key="username"/></label>
                    <input type="text" name="username" id="username" class="form-control"
                           placeholder=<fmt:message key="username"/>>
                </div>
                <div class="form-group">
                    <label><fmt:message key="email"/></label>
                    <input type="email" name="email" id="email" class="form-control"
                           placeholder=<fmt:message key="email"/>>

                </div>
                <div class="form-group">
                    <label><fmt:message key="phoneNumber"/></label>
                    <input type="tel" name="phone_number" id="phoneNumber" class="form-control"
                           placeholder=<fmt:message key="phoneNumber"/>>
                </div>
                <div class="form-group">
                    <label><fmt:message key="password"/></label>
                    <input type="password" name="password" id="password" class="form-control" placeholder=<fmt:message key="password"/>>
                </div>
                <button type="submit" class="btn btn-pink"><fmt:message key="registration"/></button>
                <a href="/login" class="btn btn-pink"><fmt:message key="login"/></a>
            </form>
        </div>
    </div>
</div>
<%@include file="/partials/footer.jspf" %>
</body>
</html>

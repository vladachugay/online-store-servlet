<%--
  Created by IntelliJ IDEA.
  User: Vlados
  Date: 11.02.2021
  Time: 14:51
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
    <title>Title</title>
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
        <h2><fmt:message key="login"/></h2>
        <p><fmt:message key="login_or_register"/></p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form method="post" action="/login">
                <div class="form-group">
                    <label><fmt:message key="username"/></label>
                    <input type="text" required class="form-control" placeholder=<fmt:message key="username"/>
                            id="username" name="username" value="${requestScope.username}">
                </div>
                <div class="form-group">

                    <label><fmt:message key="password"/></label>
                    <input type="password" required class="form-control" id="password" name="password" placeholder=<fmt:message key="password"/>>
                </div>
                <c:if test="${requestScope.error_message!=null}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="${requestScope.error_message}"/>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-pink"><fmt:message key="login"/></button>
                <a href="/registration" class="btn btn-pink"><fmt:message key="registration"/></a>
            </form>
        </div>
    </div>
</div>
<%@include file="/partials/footer.jspf" %>
</body>
</html>

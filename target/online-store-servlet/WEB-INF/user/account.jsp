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
    <title><c:out value="${requestScope.user.username}"/></title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/partials/userNav.jspf" %>

<div class="container">
    <div class="col">
        <ul class="list-group list-group-flush">
            <li class="list-group-item">
                <label for="fullname" class="font-weight-light"><fmt:message key="fullName"/></label>
                <p id="fullname">
                    <c:out value="${requestScope.user.fullName}"/>
                </p>
            </li>
            <li class="list-group-item">
                <label for="username" class="font-weight-light"><fmt:message key="username"/></label>
                <p id="username">
                    <c:out value="${requestScope.user.username}"/>
                </p>
            </li>
            <li class="list-group-item">
                <label for="email" class="font-weight-light"><fmt:message key="email"/></label>
                <p id="email">
                    <c:out value="${requestScope.user.email}"/>
                </p>
            </li>
            <li class="list-group-item">
                <label for="phonenumber" class="font-weight-light"><fmt:message key="phoneNumber"/></label>
                <p id="phonenumber">
                    <c:out value="${requestScope.user.phoneNumber}"/>
                </p>
            </li>
        </ul>
    </div>
    <c:if test="${requestScope.orders.size()==0}">
        <p class="text-center font-weight-bold my-3"><fmt:message key="no_orders"/></p>
    </c:if>
    <c:if test="${requestScope.orders.size()>0}">
        <p class="text-center font-weight-bold">
            <fmt:message key="orders"/>
        </p>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="order.date"/></th>
                <th scope="col"><fmt:message key="order.total_price"/></th>
                <th scope="col"><fmt:message key="order.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.orders}">
                <tr>
                    <th scope="row"><c:out value="${order.id}"/></th>
                    <td><c:out value="${order.creationDate}"/></td>
                    <td><c:out value="${order.totalPrice}"/></td>
                    <td><fmt:message key="${order.status.name()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<%@include file="/partials/footer.jspf" %>
</body>
</html>
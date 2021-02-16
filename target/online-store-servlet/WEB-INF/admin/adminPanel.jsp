<%--
  Created by IntelliJ IDEA.
  User: Vlados
  Date: 15.02.2021
  Time: 20:08
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
    <title><fmt:message key="products"/></title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/partials/adminNav.jspf" %>


<div class="container">
    <a class="btn btn-outline-secondary my-3" href="/admin/users" role="button">
        <fmt:message key="lock_user"/>
    </a>
    <a class="btn btn-outline-secondary my-3" href="/admin/products/add" role="button">
        <fmt:message key="product.add"/>
    </a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="order.user"/></th>
            <th scope="col"><fmt:message key="order.date"/></th>
            <th scope="col"><fmt:message key="order.total_price"/></th>
            <th scope="col"><fmt:message key="order.status"/></th>
            <th scope="col"><fmt:message key="change_status"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${requestScope.orders}">
        <tr>
            <th scope="row" >
                <a href="#"><c:out value="${order.id}"/></a>
<%--                <a th:href="@{/orders/} + ${order.id}" th:text="${order.id}"></a>--%>
            </th>
            <td><c:out value="${order.user.email}"/></td>
            <td><c:out value="${order.creationDate}"/></td>
            <td><c:out value="${order.totalPrice}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td>
                <form
<%--                        method="post" th:action="@{/orders/changestatus/} + ${order.id}">--%>
                    <select class="custom-select form-select-sm" name="status" id="status">
                        <c:forEach var="status" items="${requestScope.statuses}">
                            <option value="${status.name()}" ${status.name()==order.status ? 'selected' : ''}>
                                <fmt:message key="${status.name()}"/>
                            </option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-outline-success btn-sm my-2">
                        <fmt:message key="change_status"/>
                    </button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/partials/footer.jspf" %>
</body>
</html>

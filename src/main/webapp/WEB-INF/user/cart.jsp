<%--
  Created by IntelliJ IDEA.
  User: Vlados
  Date: 17.02.2021
  Time: 13:21
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
    <title><fmt:message key="products"/></title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/partials/userNav.jspf" %>

<div class="container">
    <c:if test="${sessionScope.cart.cartProducts.size() > 0}">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" ><fmt:message key="product.name"/></th>
                <th scope="col"><fmt:message key="product.category"/></th>
                <th scope="col"><fmt:message key="product.material"/></th>
                <th scope="col"><fmt:message key="product.quantity"/></th>
                <th scope="col"><fmt:message key="product.price"/></th>
                <th scope="col"><fmt:message key="product.delete_from_cart"/></th>
            </tr>
            </thead>
        <tbody>
        <c:forEach var="p" items="${sessionScope.cart.cartProducts}">
            <tr>
                <td><c:out value="${p.key.name}"/></td>
                <td><fmt:message key="${p.key.category.name()}"/></td>
                <td><fmt:message key="${p.key.material.name()}"/></td>
                <td><c:out value="${p.value}"/></td>
                <td><c:out value="${p.key.price}"/></td>
                <td>
                    <form method="post" action="/user/cart/delete/<c:out value="${p.key.id}"/>">
                        <button type="submit" class="btn btn-outline-danger">
                            <fmt:message key="product.delete_from_cart"/>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
        <p class="text-center font-weight-bold">
            <fmt:message key="cart.total_price"/><c:out value="${sessionScope.cart.totalPrice}"/>
        </p>
        <c:if test="${requestScope.error_message!=null}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="${requestScope.error_message}"/>
            </div>
        </c:if>
        <div align="center">
            <form method="post" action="/user/orders/create">
                <button class="btn btn-outline-success" type="submit">
                    <fmt:message key="create_order"/>
                </button>
            </form>
        </div>
    </c:if>
    <c:if test="${sessionScope.cart.cartProducts.size()==0}">
        <p class="text-center font-weight-bold my-3"><fmt:message key="cart_empty"/></p>
    </c:if>
    <div class="my-2" align="center">
        <a href="/user/products" class="btn btn-light"><fmt:message key="back_to_products"/> </a>
    </div>
</div>
<%@include file="/partials/footer.jspf" %>
</body>
</html>


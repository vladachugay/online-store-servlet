<%--
  Created by IntelliJ IDEA.
  User: Vlados
  Date: 17.02.2021
  Time: 15:17
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
        <%@include file="/css/input.css"%>
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
    <title><c:out value="${requestScope.product.name}"/></title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/partials/userNav.jspf" %>

<div class="container mt-3">
    <div class="row justify-content-center">
        <div class="col">
            <ul>
                <li class="list-group-item">
                    <label for="name" class="font-weight-light"><fmt:message key="product.name"/></label>
                    <p id="name"><c:out value="${requestScope.product.name}"/></p>
                </li>
                <li class="list-group-item">
                    <label for="category" class="font-weight-light"><fmt:message key="product.category"/></label>
                    <p id="category"><fmt:message key="${requestScope.product.category}"/></p>
                </li>
                <li class="list-group-item">
                    <label for="material" class="font-weight-light"><fmt:message key="product.material"/></label>
                    <p id="material"><fmt:message key="${requestScope.product.material}"/></p>
                </li>
                <li class="list-group-item">
                    <label for="description" class="font-weight-light"><fmt:message key="product.description"/></label>
                    <p id="description"><c:out value="${requestScope.product.description}"/></p>
                </li>
                <li class="list-group-item">
                    <label for="price" class="font-weight-light"><fmt:message key="product.price"/></label>
                    <p id="price"><c:out value="${requestScope.product.price}"/></p>
                </li>
            </ul>
            <div class="row justify-content-center">
                <fmt:message key="product.available"/><c:out value="${requestScope.product.amount}"/>
            </div>
            <form method="post" action="/user/cart/add/<c:out value="${requestScope.product.id}"/>">
                <div class="row justify-content-center">
                    <div class="input-group">
                        <input type="button" value="-" class="button-minus" data-field="quantity"
                               onclick="document.getElementById('product-quantity').value--">
                        <input type="number" min="1" required id="product-quantity" step="1" value="1"
                               name="quantity" class="quantity-field">
                        <input type="button" value="+" class="button-plus" data-field="quantity"
                               onclick="document.getElementById('product-quantity').value++">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <button class="btn btn-pink" type="submit"
                    ${requestScope.product.amount <= 0 ? 'disabled' : ''}>
                        <fmt:message key="product.add_to_cart"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/partials/footer.jspf" %>
</body>
</html>

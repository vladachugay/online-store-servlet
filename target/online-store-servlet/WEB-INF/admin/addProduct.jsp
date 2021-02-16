<%--
  Created by IntelliJ IDEA.
  User: Vlados
  Date: 16.02.2021
  Time: 21:21
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


<div class="container mt-2">
<%--    <form method="post" action="/admin/products/add" enctype="multipart/form-data">--%>
    <form method="post" action="/admin/products/add">
        <div class="form-group">
            <label><fmt:message key="product.name"/> </label>
            <input type="text" name="name" id="name" class="form-control" placeholder="<fmt:message key="product.name"/>">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="category"><fmt:message key="product.category"/></label>
            </div>
            <select class="custom-select" name="category" id="category">
                <c:forEach var="cat" items="${requestScope.categories}">
                    <option value="${cat.name()}">
                        <fmt:message key="${cat.name()}"/>
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="material"><fmt:message key="product.material"/></label>
            </div>
            <select class="custom-select" name="material" id="material">
                <c:forEach var="mat" items="${requestScope.materials}">
                    <option value="${mat.name()}">
                        <fmt:message key="${mat.name()}"/>
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="custom-file">
            <label class="custom-file-label" for="file"><fmt:message key="product.choose_file"/></label>
            <input type="file" class="custom-file-input" id="file" name="file">
        </div>

        <div class="form-group mt-3">
            <label for="description"><fmt:message key="product.description"/></label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>

        <div class="form-group">
            <label><fmt:message key="product.price"/></label>
            <input type="text" name="price" id="price" class="form-control" placeholder="<fmt:message key="product.price"/>">
        </div>

        <div class="form-group">
            <label><fmt:message key="product.amount"/></label>
            <input type="number" name="amount" id="amount" class="form-control" placeholder="<fmt:message key="product.amount"/>">
        </div>

        <button type="submit" class="btn btn-pink"><fmt:message key="product.add"/></button>
    </form>
</div>

<%@include file="/partials/footer.jspf" %>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Vlados
  Date: 15.02.2021
  Time: 15:58
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
<%@include file="/partials/adminNav.jspf" %>


<div class="container">
    <a class="btn btn-outline-secondary my-3" data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="false" aria-controls="collapseExample">
        <fmt:message key="sort_filter"/>
    </a>
    <div class="collapse my-3" id="collapseExample">
        <div class="form-group mt-3">
            <form class="row g-4" method="get" action="/admin/products">
                <div class="col-md-3">
                    <label class="form-label" for="sorting"><fmt:message key="sort_by"/> </label>
                    <select class="custom-select form-select-sm" name="sortcriteria" id="sorting">
                        <c:forEach var="sort" items="${requestScope.sorting}">
                            <option value="${sort.name()}" ${sort.name()==requestScope.chosen_sortcriteria ? 'selected' : ''}>
                                <fmt:message key="${sort.name()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    <label class="form-label" for="category"><fmt:message key="product.category"/></label>
                    <select class="custom-select form-select-sm" name="category" id="category">
                        <option value="ALL"><fmt:message key="all"/></option>
                        <c:forEach var="cat" items="${requestScope.categories}">
                            <option value="${cat.name()}" ${cat.name()==requestScope.chosen_category ? 'selected' : ''}>
                                <fmt:message key="${cat.name()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    <label class="form-label" for="material"><fmt:message key="product.material"/></label>
                    <select class="custom-select form-select-sm" name="material" id="material">
                        <option value="ALL"><fmt:message key="all"/></option>
                        <c:forEach var="mat" items="${requestScope.materials}">
                            <option value="${mat.name()}" ${mat.name()==requestScope.chosen_material ? 'selected' : ''}>
                                <fmt:message key="${mat.name()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <fmt:message key="price_between"/>
                    <div class="form-group input-group-sm">
                        <label for="price_from"><fmt:message key="price_from"/></label>
                        <input type="number" min="0" max="100000" required name="price_from" id="price_from" class="form-control"
                               value="${requestScope.chosen_price_from}">
                        <label for="price_to"><fmt:message key="price_to"/></label>
                        <input type="number" min="1" max="100000" required name="price_to" id="price_to" class="form-control"
                               value="${requestScope.chosen_price_to}">
                    </div>
                </div>
                <div class="col-md-2">
                    <button class="btn btn-outline-secondary" type="submit"><fmt:message key="apply"/></button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <c:forEach var="product" items="${requestScope.products}">
            <div class="col-md-4 my-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="/admin/products/<c:out value="${product.id}"/>">
                                <c:out value="${product.name}"/>
                            </a>
                        </h5>
                        <p class="card-text"><fmt:message key="${product.category}"/></p>
                        <p class="card-text"><fmt:message key="${product.material}"/></p>
                        <p class="card-text"><c:out value="${product.price}"/></p>
                        <a href="/admin/products/edit/<c:out value="${product.id}"/>"
                           class="btn btn-outline-secondary mr-1">
                            <fmt:message key="product.edit"/>
                        </a>
                        <form action="/admin/products/delete/<c:out value="${product.id}"/>" method="post">
                            <button type="submit" class="btn btn-outline-danger my-2"><fmt:message
                                    key="product.delete"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <nav>
        <ul class="pagination d-flex justify-content-center">
            <li class="page-item <c:out value="${currentPage <= 1 ? 'disabled' : ''}"/>">
                <a class="page-link"
                   href="/admin/products?size=${size}&page=${currentPage - 1}&sortcriteria=${chosen_sortcriteria}&material=${chosen_material}&category=${chosen_category}&price_from=${chosen_price_from}&price_to=${requestScope.chosen_price_to}">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:if test="${requestScope.pageNumbers.size() > 0}">
                <c:forEach var="pageNumber" items="${pageNumbers}">
                    <li class="page-item <c:out value="${currentPage == pageNumber ? 'active' : ''}"/>">
                        <a class="page-link"
                           href="/admin/products?size=${size}&page=${pageNumber}&sortcriteria=${chosen_sortcriteria}&material=${chosen_material}&category=${chosen_category}&price_from=${chosen_price_from}&price_to=${chosen_price_to}">
                            <c:out value="${pageNumber}"/>
                        </a>
                    </li>
                </c:forEach>
            </c:if>
            <li class="page-item <c:out value="${currentPage >= products.size() ? 'disabled' : ''}"/>">
                <a class="page-link"
                   href="/admin/products?size=${size}&page=${currentPage + 1}&sortcriteria=${chosen_sortcriteria}&material=${chosen_material}&category=${chosen_category}&price_from=${chosen_price_from}&price_to=${chosen_price_to}">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<%@include file="/partials/footer.jspf" %>
<c:if test="${param.error_message!=null}">
    <script type="text/javascript">
        $(document).ready(function() {
            alert('<fmt:message key="${param.error_message}"/>');
        });
    </script>
</c:if>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>
<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="main"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <style>
        <%@include file="/css/style.css"%>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/partials/guestNav.jspf" %>
<div class="container text-center content-form mt-5">
    <h5>Магазин має каталог Товарів, для якого необхідно реалізувати можливість:</h5>
    <ul class="list-unstyled">
        <li>сортування за назвою товару (az, za);</li>
        <li>сортування товарів за ціною (від дешевих до дорогих, від дорогих до дешевих);</li>
        <li>сортування товарів за новизною;</li>
        <li>вибірки товарів за параметрами (категорія, проміжок ціни, колір, розмір, тощо).</li>
    </ul>
    <h6>
        Користувач переглядає каталог і може додавати товари до свого кошика.
        Після додавання товарів у кошик, зареєстрований користувач може зробити Замовлення.
        Для незареєстрованого користувача ця опція недоступна.
        Після розміщення замовлення, йому (замовленню) присвоюється статус 'зареєстрований'.
    </h6>
    <h5>
        Користувач має особистий кабінет, в якому може переглянути свої замовлення.
    </h5>
    <h5>Адміністратор системи володіє правами:</h5>
    <ul class="list-unstyled">
        <li>додавання/видалення товарів, зміни інформації про товар;</li>
        <li>блокування/розблокування користувача;</li>
        <li>переведення замовлення зі статусу 'зареєстрований' до 'оплачений' або 'скасований'.</li>
    </ul>
</div>

<div align="center" class="my-3">
    <a href="/guest/products" class="btn btn-secondary"><fmt:message key="start_shopping"/></a>
</div>

<%@include file="/partials/footer.jspf" %>
</body>
</html>
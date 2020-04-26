<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="css/park.css">
    <link rel="stylesheet" href="css/buttons.css">
    <script src="js/main_menu.js" type="text/javascript"></script>
</head>
<body>
<div id="main">
    <div id="content">
  <span class="slide">
    <a href="#" onClick="openSlideMenu()">
        <img src="img/main_menu.png" height="30" width="30">
    </a>
  </span>
        <div id="menu" class="nav">
            <a href="${pageContext.request.contextPath}/logout">Out</a>
            <br><br><br>
            <a href="${pageContext.request.contextPath}/main">Menu</a>
            <a href="${pageContext.request.contextPath}/park">Cars</a>
            <a href="${pageContext.request.contextPath}/order">Order</a>
            <a href="#">Payment</a>
            <a href="#">Return</a>
            <a href="#">History</a>
            <a href="#">Contacts</a>
        </div>
        <h3 align="center">Check your order:</h3>
        <c:if test="${orders != null}">
            <style type="text/css" sc>
                TABLE {
                    width: 70%; /* Ширина таблицы */
                    border-collapse: collapse; /* Убираем двойные линии между ячейками */
                    margin: auto;
                }
                TD, TH {
                    padding: 4px; /* Поля вокруг содержимого таблицы */
                    border: 2px solid #b9b9b9; /* Параметры рамки */
                    text-align: center; /* Выравнивание по левому краю */
                }
            </style>
            <table align="center">
                <tr>
                    <th>Photo</th>
                    <th>Specification</th>
                    <th>Location</th>
                    <th>Price for day, EUR</th>
                    <th>Availability</th>
                    <th>Pick-up date</th>
                    <th>Drop-off date</th>
                </tr>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td> <img src="${order.photo}" height="202" width="300"></td>
                        <td>${order.brand} ${order.model} <br><br> ${order.year} <br> ${order.engine}</td>
                        <td>${order.location}</td>
                        <td>${order.price}</td>
                        <td>${order.availability}</td>
                        <form action="${pageContext.request.contextPath}/order" method="post">
                        <td><input type="date" value="${timeNow}" min="${timeNow}" name="pickup"/></td>
                        <td><input type="date" value="${timeTomorrow}" min="${timeTomorrow}" name="dropoff"/></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </c:if>
    </div>
    <div class="buttons">
        <div class="container">
            <form action="${pageContext.request.contextPath}/payment"  method="post">
                <button class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg" id="btnPayment" type="submit" name="payment" value="${car.id}">Payment</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
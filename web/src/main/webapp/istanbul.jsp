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
        <h3 align="center">Choose your rental car:</h3>
        <c:if test="${cars != null}">
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
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Engine</th>
                    <th>Price, $</th>
                    <th>Availability</th>

                </tr>
                <c:forEach items="${cars}" var="car">
                    <tr>
                        <td> <img src="${car.photo}" height="101" width="150"></td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.year}</td>
                        <td>${car.engine}</td>
                        <td>${car.price}</td>
                        <td>${car.availability}</td>
                        <div class="buttons">
                            <div class="container">
                                <form action="${pageContext.request.contextPath}/order"  method="post">
                                    <td><button id="btnRent" type="submit" name="id" value="${car.id}">Rent!</button></td>
                                </form>
                            </div>
                        </div>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
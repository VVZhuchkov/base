<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="css/main_menu.css">
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
        <h2 align="center">Choose the city, where you want to rent a car:</h2>
            <style type="text/css" sc>
                TABLE {
                    width: 300px; /* Ширина таблицы */
                    border-collapse: collapse; /* Убираем двойные линии между ячейками */
                    margin: auto;
                }
                TD, TH {
                    padding: 4px; /* Поля вокруг содержимого таблицы */
                    border: 2px solid #b9b9b9; /* Параметры рамки */
                    text-align: center; /* Выравнивание по левому краю */
                }
            </style>
            <table>
                <tr>
                    <th align="center"><a href=${pageContext.request.contextPath}/paris><img src="img/paris.jpg" height="300" width="300"><br>Paris</a></th>
                    <th align="center"><a href=${pageContext.request.contextPath}/tokio><img src="img/tokio.jpeg" height="300" width="300"><br>Tokio</a></th>
                    <th align="center"><a href=${pageContext.request.contextPath}/istanbul><img src="img/istanbul.jpg" height="300" width="300"><br>Istanbul</a></th>
                </tr>
            </table>
    </div>
</div>
</body>
</html>

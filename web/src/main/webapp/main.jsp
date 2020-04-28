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
        <jsp:include page="main_menu.jsp" />
        <h2 align="center">Choose the city, where you want to rent a car:</h2>
        <style type="text/css" sc>
            TABLE {
                width: 90%; /* Ширина таблицы */
                border-collapse: collapse; /* Убираем двойные линии между ячейками */
                margin: auto;
            }
            TD, TH {
                padding: 4px; /* Поля вокруг содержимого таблицы */
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

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
        <img src="img/main_menu.png " height="30" width="30">
    </a>
  </span>
        <div id="menu" class="nav">
            <a href="${pageContext.request.contextPath}/logout">Out</a>
            <br><br><br>
            <a href="#">Chat</a>
            <a href="#">Mail</a>
            <a href="#">Tasks</a>
            <a href="#">Reports</a>
            <br><br><br>
            <a href="#">Contacts</a>
        </div>
        <h2 align="center">Contacts</h2>
        <c:if test="${employees != null&&authUser.position=='HEAD'}">
            <style type="text/css">
                TABLE {
                    width: 300px; /* Ширина таблицы */
                    border-collapse: collapse; /* Убираем двойные линии между ячейками */
                    margin: auto;
                }
                TD, TH {
                    padding: 4px; /* Поля вокруг содержимого таблицы */
                    border: 2px solid #555555; /* Параметры рамки */
                    text-align: center; /* Выравнивание по левому краю */
                }
            </style>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Department</th>
                    <th>Position</th>
                    <th>Sphere</th>
                </tr>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.password}</td>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td>${employee.department}</td>
                        <td>${employee.position}</td>
                        <td>${employee.sphere}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${employees != null&&authUser.position=='ENGINEER'}">
            <style type="text/css">
                TABLE {
                    width: 300px; /* Ширина таблицы */
                    border-collapse: collapse; /* Убираем двойные линии между ячейками */
                    margin: auto;
                }
                TD, TH {
                    padding: 4px; /* Поля вокруг содержимого таблицы */
                    border: 2px solid #555555; /* Параметры рамки */
                    text-align: center; /* Выравнивание по левому краю */
                }
            </style>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Department</th>
                    <th>Position</th>
                    <th>Sphere</th>
                </tr>
                <tr>
                    <td>${authUser.id}</td>
                    <td>${authUser.password}</td>
                    <td>${authUser.name}</td>
                    <td>${authUser.surname}</td>
                    <td>${authUser.department}</td>
                    <td>${authUser.position}</td>
                    <td>${authUser.sphere}</td>
                </tr>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>

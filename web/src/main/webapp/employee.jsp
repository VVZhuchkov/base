<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<a href="${pageContext.request.contextPath}/logout">logout</a>
<h3>Employees</h3>
<c:if test="${employees != null&&authUser.position=='HEAD'}">
    <style type="text/css">
        BODY {
            background: bisque; /* Цвет фона веб-страницы */
        }
        TABLE {
            width: 300px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 4px; /* Поля вокруг содержимого таблицы */
            border: 2px solid maroon; /* Параметры рамки */
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
        BODY {
            background: mintcream; /* Цвет фона веб-страницы */
        }
        TABLE {
            width: 300px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 4px; /* Поля вокруг содержимого таблицы */
            border: 2px solid maroon; /* Параметры рамки */
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
<%= request.getAttribute("employees")%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Login</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
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
            text-align: left; /* Выравнивание по левому краю */
        }
    </style>
    <table>
        <tbody>
        <tr>
            <td>Id:</td>
            <td>
                <input type="text", name="id">
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type="password", name="password">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Sign In">
            </td>
        </tr>
        </tbody>
    </table>
</form>

<p style="color: red">${error}</p>
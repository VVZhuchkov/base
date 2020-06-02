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
        <jsp:include page="main_menu.jsp">
            <jsp:param name="role" value="${role}"/>
        </jsp:include>
        <c:choose>
            <c:when test="${deal.size()!=0}">
                <h3 align="center">History:</h3>
                <jsp:include page="main_tabstl.jsp"/>
                <table align="center">
                    <tr>
                        <th>Surname<br>Name</th>
                        <th>Order ID</th>
                        <th>Car<br>ID</th>
                        <th>Comment</th>
                    </tr>
                    <c:forEach items="${histories}" var="history">
                        <c:forEach items="${history.dealList}" var="deal">
                            <tr>
                                <td>${history.surname}<br>${history.name}</td>
                                <td>${deal.number}</td>
                                <td>${deal.id}</td>
                                <td>${deal.comment}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p style="color: #ff0000" align="center">${historyError}</p>
                <div style="color: #ff0000; font-size: 25px" align="center" id="counter">3</div>
                <script>
                    setInterval(function () {
                        var div = document.querySelector("#counter");
                        var count = div.textContent * 1 - 1;
                        div.textContent = count;
                        if (count <= 0) {
                            window.location.replace("${pageContext.request.contextPath}/request");
                        }
                    }, 1000);
                </script>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
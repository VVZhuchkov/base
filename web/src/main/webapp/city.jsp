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
    <c:when test="${cars.size()!=0}">
        <h3 align="center">Choose your rental car:</h3>
            <jsp:include page="main_tabstl.jsp" />
            <table align="center">
                <tr>
                    <th>Car<br>ID</th>
                    <th>Photo</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Engine</th>
                    <th>Price<br>per day,<br>€</th>
                </tr>
                <c:forEach items="${cars}" var="car">
                    <tr>
                        <td>${car.id}</td>
                        <td> <img src="${car.photo}" height="101" width="150"></td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.year}</td>
                        <td>${car.engine}</td>
                        <td>${car.price}</td>
                        <div class="buttons">
                            <div class="container">
                                <form action="${pageContext.request.contextPath}/order"  method="post">
                                    <td><button id="btnRent" type="submit" name="id" value="${car.id}">Rent</button></td>
                                </form>
                            </div>
                        </div>
                    </tr>
                </c:forEach>
            </table>
    </c:when>
    <c:otherwise>
        <p style="color: #ff0000" align="center">${cityError}</p>
        <div style="color: #ff0000; font-size: 25px" align="center" id="counter">3</div>
        <script>
            setInterval(function() {
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

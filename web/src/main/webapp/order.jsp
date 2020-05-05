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
        <jsp:include page="main_menu.jsp" />
        <c:choose>
        <c:when test="${orders.size()!=0}">
        <h3 align="center">Check your order:</h3>
            <jsp:include page="main_tabstl.jsp" />
            <table align="center">
                <tr>
                    <th>Order ID</th>
                    <th>Photo</th>
                    <th>Specification</th>
                    <th>Location</th>
                    <th>Price for day, EUR</th>
                    <th>Availability</th>
                    <th>Pick-up date</th>
                    <th>Drop-off date</th>
                </tr>
                <c:forEach items="${orders}" var="order">
                <form action="${pageContext.request.contextPath}/order"  method="get">
                    <tr>
                        <td>${order.number}</td>
                        <td> <img src="${order.photo}" height="202" width="300"></td>
                        <td>${order.brand} ${order.model} <br><br> ${order.year} <br> ${order.engine}</td>
                        <td>${order.location}</td>
                        <td>${order.price}</td>
                        <td>${order.availability}</td>
                        <td>${order.pickup}</td>
                        <td>${order.dropoff}</td>
                        <div class="buttons">
                            <div class="container">
                                    <td><button id="btnRent" type="submit" name="delNumber" value="${order.number}">Delete!</button></td>
                            </div>
                        </div>
                    </tr>
                </form>
                </c:forEach>
            </table>
            <br>
            <form action="${pageContext.request.contextPath}/payment" method="get">
            <div style="text-align: center">
                <input type="text" name="name" placeholder="Name" style="text-transform: uppercase" pattern="[A-Za-z]{2,30}" required>
                <input type="text" name="surname" placeholder="Surname" style="text-transform: uppercase" pattern="[A-Za-z]{2,30}" required>
                <input type="text" name="passport" placeholder="Passport ID" required>
            </div>
                <br><br>
                <div class="buttons">
            <div class="container">
                    <button id="btnPayment" type="submit">Confirm</button>
            </div>
        </div>
            </form>
        </c:when>
        <c:otherwise>
        <p style="color: #ff0000" align="center">${orderError}</p>
<div style="color: #ff0000; font-size: 25px" align="center" id="counter">3</div>
        <script>
            setInterval(function() {
                var div = document.querySelector("#counter");
                var count = div.textContent * 1 - 1;
                div.textContent = count;
                if (count <= 0) {
                    window.location.replace("${pageContext.request.contextPath}/main");
                }
            }, 1000);
        </script>
        </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
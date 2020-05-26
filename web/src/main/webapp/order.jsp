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
        <c:when test="${bookings.size()!=0}">
        <h3 align="center">Check your order:</h3>
            <jsp:include page="main_tabstl.jsp" />
            <table align="center">
                <tr>
                    <th>Order<br>ID</th>
                    <th>Photo</th>
                    <th>Specification</th>
                    <th>Location</th>
                    <th>Price<br>per day,<br>€</th>
                    <th>Pick-up<br>date</th>
                    <th>Drop-off<br>date</th>
                </tr>
                <c:forEach items="${bookings}" var="booking">
                <form action="${pageContext.request.contextPath}/order"  method="get">
                    <tr>
                        <td>${booking.number}</td>
                        <td> <img src="${booking.car.photo}" height="202" width="300"></td>
                        <td>${booking.car.brand} ${booking.car.model}<br><br>${booking.car.engine}<br>${booking.car.year}</td>
                        <td>${booking.car.location}</td>
                        <td>${booking.car.price}</td>
                        <td>${booking.pickup}</td>
                        <td>${booking.dropoff}</td>
                        <div class="buttons">
                            <div class="container">
                                    <td><button id="btnRent" type="submit" name="delNumber" value="${booking.number}">Delete!</button></td>
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
                            window.location.replace("${pageContext.request.contextPath}/request");
                        }
                    }, 1000);
                </script>
                   </c:otherwise>
        </c:choose>
        <c:if test="${totalPages > 1}">
        <div class="page-navigator">
            <c:forEach items="${navigationPages}" var = "page">
                <c:if test="${page != -1 }">
                    <a href="order?page=${page}" class="nav-item">${page}</a>
                </c:if>
                <c:if test="${page == -1 }">
                    <span class="nav-item"> ... </span>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
    </div>
</div>
</body>
</html>
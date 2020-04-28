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
                    <th>Photo</th>
                    <th>Specification</th>
                    <th>Location</th>
                    <th>Price for day, EUR</th>
                    <th>Availability</th>
                    <th>Pick-up date</th>
                    <th>Drop-off date</th>
                </tr>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td> <img src="${order.photo}" height="202" width="300"></td>
                        <td>${order.brand} ${order.model} <br><br> ${order.year} <br> ${order.engine}</td>
                        <td>${order.location}</td>
                        <td>${order.price}</td>
                        <td>${order.availability}</td>
                        <td><input type="date" value="${timeNow}" min="${timeNow}" name="pickup"/></td>
                        <td><input type="date" value="${timeTomorrow}" min="${timeTomorrow}" name="dropoff"/></td>
                        <div class="buttons">
                            <div class="container">
                                <form action="${pageContext.request.contextPath}/order"  method="post">
                                    <td><button id="btnRent" type="submit" name="delId" value="${order.id}">Delete!</button></td>
                                </form>
                            </div>
                        </div>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <div class="buttons">
            <div class="container">
                <form action="${pageContext.request.contextPath}/payment"  method="post">
                    <button class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg" id="btnPayment" type="submit" name="payment" value="${car.id}">Agree</button>
                </form>
            </div>
        </div>
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
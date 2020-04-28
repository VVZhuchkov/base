<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="css/park.css">
    <script src="js/main_menu.js" type="text/javascript"></script>
</head>
<body>
<div id="main">
    <div id="content" >
        <jsp:include page="main_menu.jsp" />
        <h3 align="center">Watch our autopark:</h3>
        <c:if test="${allCars != null}">
            <jsp:include page="main_tabstl.jsp" />
            <table align="center">
                <tr>
                    <th>Photo</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Engine</th>
                    <th>Price, $</th>
                    <th>Location</th>
                    <th>Availability</th>
                </tr>
                <c:forEach items="${allCars}" var="car">
                    <tr>
                        <td> <img src="${car.photo}" height="152" width="225"></td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.year}</td>
                        <td>${car.engine}</td>
                        <td>${car.price}</td>
                        <td>${car.location}</td>
                        <td>${car.availability}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
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
            <c:when test="${deals.size()!=0}">
                <h3 align="center">Check your deals:</h3>
                <jsp:include page="main_tabstl.jsp"/>
                <table align="center">
                    <tr>
                        <th>Order<br>ID</th>
                        <th>Surname<br>Name<br>Passport ID</th>
                        <th>Car<br>ID</th>
                        <th>Pick-up<br>date</th>
                        <th>Drop-off<br>date</th>
                        <th>Total,<br>€</th>
                        <th>Admin's<br>approval</th>
                        <th>Comment</th>
                    </tr>
                    <c:set var="sumTotal" value="${0}"/>
                    <c:forEach items="${deals}" var="deal">
                        <tr>
                            <td><a name="${deal.number}"
                                   href="${pageContext.request.contextPath}/return#${deal.number}">${deal.number}</a></td>
                            <td>${deal.surname}<br>${deal.name}<br>${deal.passport}</td>
                            <td><a name="${deal.id}"
                                   href="${pageContext.request.contextPath}/park#${deal.id}">${deal.id}</a></td>
                            <td>${deal.pickup}</td>
                            <td>${deal.dropoff}</td>
                            <td>${deal.total}</td>
                            <td>${deal.approval}</td>
                            <td>${deal.comment}</td>
                        </tr>
                        <c:set var="sumTotal" value="${sumTotal + deal.total}"/>
                    </c:forEach>
                    <td colspan="5" style="text-align: left">Total cost, €:</td>
                    <td>${sumTotal}</td>
                </table>
            </c:when>
            <c:otherwise>
                <p style="color: #ff0000" align="center">${dealError}</p>
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
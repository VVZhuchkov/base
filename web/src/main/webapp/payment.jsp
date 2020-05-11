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
            <c:when test="${payments.size()!=0}">
                <h3 align="center">Check your order:</h3>
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
                    <c:forEach items="${payments}" var="payment">
                        <tr>
                            <td>${payment.number}</td>
                            <td>${payment.surname}<br>${payment.name}<br>${payment.passport}</td>
                            <td><a name="${payment.id}"
                                   href="${pageContext.request.contextPath}/park#${payment.id}">${payment.id}</a></td>
                            <td>${payment.pickup}</td>
                            <td>${payment.dropoff}</td>
                            <td>${payment.total}</td>
                            <td>${payment.approval}</td>
                            <td>${payment.comment}</td>
                            <div class="buttons">
                                <div class="container">
                                    <td>
                                        <form action="${pageContext.request.contextPath}/payment"
                                              style="margin-block-end: 0px" method="post">
                                            <c:if test="${role=='user'}">
                                                <c:if test="${payment.approval=='Approved!'}">
                                                    <button id="btnPayment" type="submit" name="payNumber"
                                                            value="${payment.number}">Payment!
                                                    </button>
                                                </c:if>
                                                <button id="btnDelete" type="submit" name="delNumber"
                                                        value="${payment.number}">Delete!
                                                </button>
                                            </c:if>
                                            <c:if test="${role=='admin'}">
                                                <c:if test="${payment.approval=='-'}">
                                                    <button id="btnPayment" type="submit" name="accept"
                                                            value="${payment.number}">Accept!
                                                    </button>
                                                    <button id="btnDelete" type="submit" name="decline"
                                                            value="${payment.number}">Decline!
                                                    </button>
                                                    <br>
                                                    <textarea style="height: 75px;width: 100px" id="commentAccept"
                                                              name="commentAccept"></textarea>
                                                    <textarea style="height: 75px;width: 100px" id="commentDecline"
                                                              name="commentDecline"></textarea>
                                                </c:if>
                                                <c:if test="${payment.approval=='Approved!'}">
                                                    <button id="btnPayment" type="submit" name="payNumber"
                                                            value="${payment.number}">Payment!
                                                    </button>
                                                    <button id="btnDelete" type="submit" name="delNumber"
                                                            value="${payment.number}">Delete!
                                                    </button>
                                                </c:if>
                                                <c:if test="${payment.approval=='Rejected!'}">
                                                    <button id="btnDelete" type="submit" name="delNumber"
                                                            value="${payment.number}">Delete!
                                                    </button>
                                                </c:if>
                                            </c:if>
                                        </form>
                                    </td>
                                </div>
                            </div>
                        </tr>
                        <c:set var="sumTotal" value="${sumTotal + payment.total}"/>
                    </c:forEach>
                    <td colspan="5" style="text-align: left">Total cost, €:</td>
                    <td>${sumTotal}</td>
                </table>
            </c:when>
            <c:otherwise>
                <p style="color: #ff0000" align="center">${paymentError}</p>
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
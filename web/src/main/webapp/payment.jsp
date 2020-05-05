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
        <jsp:include page="main_menu.jsp"/>
        <c:choose>
            <c:when test="${payments.size()!=0}">
                <c:if test="${role=='user'}">
                    <h3 align="center">Check your order:</h3>
                    <jsp:include page="main_tabstl.jsp"/>
                    <table align="center">
                        <tr>
                            <th>Order ID</th>
                            <th>Surname<br>Name<br>Passport ID</th>
                            <th>Total, EUR</th>
                            <th>Administrator's approval</th>
                            <th>Comment</th>
                        </tr>
                        <c:set var="sumTotal" value="${0}"/>
                        <c:forEach items="${payments}" var="payment">
                            <form action="${pageContext.request.contextPath}/payment" method="get">
                                <tr>
                                    <td>${payment.number}</td>
                                    <td>${payment.surname}<br>${payment.name}<br><br>${payment.passport}</td>
                                    <td>${payment.total}</td>
                                    <td>${payment.approval}</td>
                                    <td>${payment.comment}</td>
                                    <div class="buttons">
                                        <div class="container">
                                            <td>
                                                <button id="btnRent" type="submit" name="delNumber"
                                                        value="${payment.number}">Delete!
                                                </button>
                                            </td>
                                        </div>
                                    </div>
                                </tr>
                            </form>
                            <c:set var="sumTotal" value="${sumTotal + payment.total}"/>
                        </c:forEach>
                        <td colspan="5" style="text-align: center">Total cost, EUR: ${sumTotal}</td>
                    </table>
                    <br>
                    <br>
                    <form action="${pageContext.request.contextPath}/payment" method="post">
                        <div class="buttons">
                            <div class="container">
                                <td>
                                    <button id="btnUserPayment" type="submit" name="confirm" value="confirm">Pay
                                    </button>
                                </td>
                            </div>
                        </div>
                    </form>
                </c:if>
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
                                window.location.replace("${pageContext.request.contextPath}/order");
                            }
                        }, 1000);
                    </script>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${approvals.size()!=0}">
                <c:if test="${role=='admin'}">
                    <h3 align="center">Check your order:</h3>
                    <jsp:include page="main_tabstl.jsp"/>
                    <table align="center">
                        <tr>
                            <th>Order ID</th>
                            <th>Surname<br>Name<br>Passport ID</th>
                            <th>Total, EUR</th>
                            <th>Administrator's approval</th>
                            <th>Comment</th>
                        </tr>
                        <c:set var="sumTotal" value="${0}"/>
                        <c:forEach items="${approvals}" var="approval">
                            <tr>
                                <td>${approval.number}</td>
                                <td>${approval.surname}<br>${approval.name}<br><br>${approval.passport}</td>
                                <td>${approval.total}</td>
                                <td>${approval.approval}</td>
                                <td>${approval.comment}</td>
                                <div class="buttons">
                                    <div class="container">
                                        <td>
                                            <form action="${pageContext.request.contextPath}/history" method="get">
                                                <button id="btnAccept" type="submit" name="accept"
                                                        value="${approval.number}">Accept!
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/history" method="get">
                                                <button id="btnDecline" type="submit" name="decline"
                                                        value="${approval.number}">Decline!
                                                </button>
                                                <br>
                                                <textarea style="height: 75px;width: 100px" id="commentDecline"
                                                          name="declineComment"></textarea>
                                            </form>
                                        </td>
                                    </div>
                                </div>
                            </tr>
                            <c:set var="sumTotal" value="${sumTotal + approval.total}"/>
                        </c:forEach>
                        <td colspan="5" style="text-align: center">Total cost, EUR: ${sumTotal}</td>
                    </table>
                </c:if>
            </c:when>
            <c:otherwise>
                <c:if test="${role=='admin'}">
                    <p style="color: #ff0000" align="center">${paymentAdError}</p>
                    <div style="color: #ff0000; font-size: 25px" align="center" id="counter">3</div>
                    <script>
                        setInterval(function () {
                            var div = document.querySelector("#counter");
                            var count = div.textContent * 1 - 1;
                            div.textContent = count;
                            if (count <= 0) {
                                window.location.replace("${pageContext.request.contextPath}/order");
                            }
                        }, 1000);
                    </script>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
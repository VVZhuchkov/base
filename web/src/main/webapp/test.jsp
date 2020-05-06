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
                        <td><a name="${approval.number}"
                               href="${pageContext.request.contextPath}/order#${approval.number}">${approval.number}</a>
                        </td>
                        <td>${approval.surname}<br>${approval.name}<br><br>${approval.passport}</td>
                        <td>${approval.total}</td>
                        <td>${approval.approval}</td>
                        <td>${approval.comment}</td>
                        <div class="buttons">
                            <div class="container">
                                <td>
                                    <form action="${pageContext.request.contextPath}/payment" method="post">
                                        <button id="btnAccept" type="submit" name="accept"
                                                value="${approval.number}">Accept!
                                        </button>
                                        <br>
                                        <textarea style="height: 75px;width: 100px" id="commentAccept"
                                                  name="declineComment"></textarea>
                                    </form>
                                </td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/payment" method="post">
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
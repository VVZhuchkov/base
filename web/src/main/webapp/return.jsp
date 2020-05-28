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
            <c:when test="${restitutions.size()!=0}">
                <h3 align="center">Check all restitutions:</h3>
                <jsp:include page="main_tabstl.jsp"/>
                <table align="center">
                    <tr>
                        <th>Order<br>ID</th>
                        <th>Login</th>
                        <th>Status</th>
                        <th>Rating</th>
                        <th>Comment</th>
                    </tr>
                    <c:forEach items="${restitutions}" var="restitution">
                        <tr>
                            <form action="${pageContext.request.contextPath}/return"
                                  style="margin-block-end: 0px" method="post">
                                <td><a name="${restitution.number}"
                                       href="${pageContext.request.contextPath}/deal#${restitution.number}">${restitution.number}</a></td>
                                <td>${restitution.login}</td>
                                <td>${restitution.status}</td>
                                <td>${restitution.rating}</td>
                                <c:if test="${role=='user'}">
                                <td>${restitution.comment}</td>
                                <c:if test="${role=='user'&&restitution.status=='In operation'}">
                                <div class="buttons">
                                    <div class="container">
                                        <td>
                                            <button id="btnReturn" type="submit" name="return"
                                                    value="${restitution.number}">Restitute
                                            </button>
                                        </td>
                                        </c:if>
                                        </c:if>
                                        <c:if test="${role=='admin'&&restitution.status!='Approved'}">
                                        <td><textarea style="height: 75px;width: 300px" id="commentReturn"
                                                      name="commentReturn"></textarea></td>
                                        <td>
                                            <button id="btnAccept" type="submit" name="accept"
                                                    value="${restitution.number}">Accept
                                            </button>
                                        </td>
                                        </c:if>
                                        <c:if test="${role=='admin'&&restitution.status=='Approved'}">
                                        <td>${restitution.comment}</td>
                                        </c:if>
                                        </form>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p style="color: #ff0000" align="center">${restitutionError}</p>
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
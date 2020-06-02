<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<span class="slide">
    <a href="#" onClick="openSlideMenu()">
        <img src="img/main_menu.png" height="30" width="30">
    </a>
  </span>
        <div id="menu" class="nav">
                <c:if test="${param.role=='user'}">
            <a href="${pageContext.request.contextPath}/main">Search</a>
            <a href="${pageContext.request.contextPath}/park">Autopark</a>
            <a href="${pageContext.request.contextPath}/order">Order</a>
            <a href="${pageContext.request.contextPath}/payment">Payment</a>
                    <a href="${pageContext.request.contextPath}/deal">Deal</a>
                    <a href="${pageContext.request.contextPath}/return">Return</a>
                    <a href="${pageContext.request.contextPath}/history">History</a>
                    <a href="${pageContext.request.contextPath}/logout">Out</a>
            </c:if>
                <c:if test="${param.role=='admin'}">
                    <a href="${pageContext.request.contextPath}/main">New&nbsp;car</a>
                    <a href="${pageContext.request.contextPath}/park">Autopark</a>
                    <a href="${pageContext.request.contextPath}/request">Search</a>
                    <a href="${pageContext.request.contextPath}/order">Orders</a>
                    <a href="${pageContext.request.contextPath}/payment">Payments</a>
                    <a href="${pageContext.request.contextPath}/deal">Deals</a>
                    <a href="${pageContext.request.contextPath}/return">Returns</a>
                    <a href="${pageContext.request.contextPath}/history">History</a>
                    <a href="${pageContext.request.contextPath}/logout">Out</a>
                </c:if>
        </div>





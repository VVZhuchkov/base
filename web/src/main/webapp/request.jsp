<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="css/buttons.css">
    <link rel="stylesheet" href="css/main_menu.css">
    <script src="js/main_menu.js" type="text/javascript"></script>
</head>
<body>
<div id="main">
    <div id="content">
        <jsp:include page="main_menu.jsp">
            <jsp:param name="role" value="${role}"/>
        </jsp:include>
        <form action="${pageContext.request.contextPath}/request" method="post">
            <fieldset style="border: solid; position: absolute; left: 40%">
                <legend align="center">Let's find a car</legend>
                <br><select name="location" style="height: 25px;width: 300px;resize:none;text-align: center;text-align-last: center;font-size: medium" required>
                <option style="text-align: left">Tokio</option>
                <option style="text-align: left">Istanbul</option>
                <option style="text-align: left">Paris</option>
            </select></br>
                <br><label for="pickup">Pick-up date</label>
                <br><input id="pickup" style="height: 25px;width: 300px;resize:none;text-align: center;text-align-last: center;font-size: large" required type="date" value="${timeNow}" min="${timeNow}" name="pickup"></br>
                <br><label for="dropoff">Drop-off date</label>
                <br><input id="dropoff" style="height: 25px;width: 300px;resize:none;text-align: center;text-align-last: center;font-size: large" required type="date" value="${timeNow}" min="${timeNow}" name="dropoff"></br>
                <c:if test="${hasContact == false}">
                <br><label for="name">Name</label>
                <br><input type="text" id="name" name="name" placeholder="Name" style="text-transform: uppercase;height: 25px;width: 300px;resize:none;text-align: center;text-align-last: center;font-size: medium" pattern="[A-Za-z]{2,30}" required></br>
                <br><label for="surname">Surname</label>
                <br><input type="text" id="surname" name="surname" placeholder="Surname" style="text-transform: uppercase;height: 25px;width: 300px;resize:none;text-align: center;text-align-last: center;font-size: medium" pattern="[A-Za-z]{2,30}" required></br>
                <br><label for="passport">Private ID (as in the passport)</label>
                <br><input type="text" id="passport" name="passport" placeholder="Identification ID"  style="height: 25px;width: 300px;resize:none;text-align: center;text-align-last: center;font-size: medium" required></br>
                </c:if>
                <div class="buttons">
                    <div class="container">
                        <br><input id="btnRent" type="submit" value="Search!">
                    </div>
                </div>
                <p style="color: red" align="center">${datesError}</p>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
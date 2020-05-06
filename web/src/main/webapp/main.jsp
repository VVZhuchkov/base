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
        <jsp:include page="main_menu.jsp" />
        <form action="${pageContext.request.contextPath}/main"  method="post">
            <fieldset style="border: solid; position: absolute; left: 45%" >
                <legend align="center">Let's find a car</legend>
                <br><select name="location" required>
                <option>Tokio</option>
                    <option>Istanbul</option>
                    <option>Paris</option>
                </select></br>
                <br><label for="pickup">Pick-up date</label>
                <br><input id="pickup" type="date" value="${timeNow}" min="${timeNow}" name="pickup"></br>
                <br><label for="dropoff">Drop-off date</label>
                <br><input id="dropoff" type="date" value="${timeNow}" min="${timeNow}" name="dropoff"></br>
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

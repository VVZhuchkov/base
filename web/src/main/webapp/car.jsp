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
        <form action="${pageContext.request.contextPath}/car" method="post">
                <fieldset style="border: solid; position: absolute;left: 40%">
                    <legend align="center">Add a new car</legend><br>
                    <textarea style="height: 25px;width: 300px;resize:none;text-align: center; font-size: large" rows="1" placeholder="Photo (e.g: img/THC1518b.jpg)" name="photo" required></textarea><br><br>
                    <textarea style="height: 25px;width: 300px;resize:none;text-align: center; font-size: large" rows="1" placeholder="Brand (e.g: Ferrari)" name="brand" required></textarea><br><br>
                    <textarea style="height: 25px;width: 300px;resize:none;text-align: center; font-size: large" rows="1" placeholder="Model (e.g: Portofino)" name="model" required></textarea><br><br>
                    <input type="number" style="height: 25px;width: 300px;resize:none;text-align: center; font-size: medium" rows="1" placeholder="Year (e.g: 2020)" name="year" required></input><br><br>
                    <textarea style="height: 25px;width: 300px;resize:none;text-align: center; font-size: large" rows="1" placeholder="Engine (e.g: 3.9 benzine)" name="engine" required></textarea><br><br>
                    <select name="location" style="height: 25px;width: 300px;resize:none;padding-left: 118px;text-align: center; font-size: medium" required>
                        <option>Tokio</option>
                        <option>Istanbul</option>
                        <option>Paris</option>
                    </select></br><br>
                    <input type="number" style="height: 25px;width: 300px;resize:none;text-align: center; font-size: medium" placeholder="Price" name="price" required></input><br><br>
                    <select name="availability" style="height: 25px;width: 300px;resize:none;padding-left: 110px;text-align: center; font-size: medium" required>
                        <option>Available</option>
                        <option>Not available</option>
                    </select></br>
                    <div class="buttons">
                        <div class="container">
                            <br><input id="btnAdRent" type="submit" value="Add">
                        </div>
                    </div>
                    <p style="color: red" align="center">${datesError}</p>
                </fieldset>
        </form>
    </div>
</div>
</body>
</html>

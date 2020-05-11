<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/login.js" type="text/javascript"></script>
</head>
<body>
    <div class="form-wrap">
        <div class="tabs">
            <h3 class="signup-tab"><a  href="#signup-tab-content">Sign Up</a></h3>
            <h3 class="login-tab"><a class="active" href="#login-tab-content">Sign In</a></h3>
        </div>

        <div class="tabs-content" align="center">
            <div id="signup-tab-content">
                <form class="signup-form" action="${pageContext.request.contextPath}/registration" method="post" >
                    <input type="text" class="input" name="login" autocomplete="off" placeholder="Login">
                    <input type="password" class="input" name="password" autocomplete="off" placeholder="Password">
                    <input type="email" class="input" name="email" autocomplete="off" placeholder="Email">
                    <br><br>
                    <input type="submit" class="button" value="Sign Up">
                </form>
                <div class="help-text">
                    <p>By signing up, you agree to our</p>
                    <p><a href="${pageContext.request.contextPath}/terms.jsp">Terms of service</a></p>
                </div>
            </div>

            <div id="login-tab-content" class="active">
                <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                    <input type="text" class="input" name="login" autocomplete="off" placeholder="Login">
                    <input type="password" class="input" name="password" autocomplete="off" placeholder="Password">
                    <input type="checkbox" class="checkbox" id="remember_me">
                    <label for="remember_me">Remember me</label>
                    <input type="submit" class="button" value="Sign In">
                </form><!--.login-form-->
                <div class="help-text">
                    <p><a href="${pageContext.request.contextPath}/forgot.jsp">Forget your password?</a></p>
                </div>
            </div>
        </div>
    </div>
<p style="color: red" align="center">${error}</p>
</body>
</html>





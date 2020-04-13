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
            <h3 class="signup-tab"><a class="active" href="#signup-tab-content">Sign Up</a></h3>
            <h3 class="login-tab"><a href="#login-tab-content">Login</a></h3>
        </div>

        <div class="tabs-content" align="center">
            <div id="signup-tab-content" class="active">
                <form class="signup-form" action="" method="post" >
                    <input type="email" class="input" id="user_email" autocomplete="off" placeholder="Email">
                    <input type="text" class="input" id="user_name" autocomplete="off" placeholder="Username">
                    <input type="password" class="input" id="user_pass" autocomplete="off" placeholder="Password">
                    <br><br>
                    <input type="submit" class="button" value="Sign Up">
                </form>
                <div class="help-text">
                    <p>By signing up, you agree to our</p>
                    <p><a href="#">Terms of service</a></p>
                </div>
            </div>

            <div id="login-tab-content">
                <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                    <input type="text" class="input" name="id" autocomplete="off" placeholder="Id">
                    <input type="password" class="input" name="password" autocomplete="off" placeholder="Password">
                    <input type="checkbox" class="checkbox" id="remember_me">
                    <label for="remember_me">Remember me</label>
                    <input type="submit" class="button" value="Login">
                </form><!--.login-form-->
                <div class="help-text">
                    <p><a href="#">Forget your password?</a></p>
                </div>
            </div>
        </div>
    </div>
<p style="color: red" align="center">${error}</p>
</body>
</html>





<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to IBM Skill Build</title>
</head>
<body>
<div class="container">
    <h2 class="gamification-element">Welcome to IBM Skills Build</h2>
    <p class="welcome-message">Login</p>
    <form action="/register" method="POST" modelAttribute="user">
        User Name: <input type="text" name="userName" /> <br/>
        Password: <input type="password" name="password" /> <br/>
        <input type="submit" value="Sign In" class="gamification-element" /> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    </form>
</div>
</body>
</html>
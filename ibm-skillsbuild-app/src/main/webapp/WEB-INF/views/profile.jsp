<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile/profile.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="profile"/>
</jsp:include>

<div class="container">
    <!-- Display the avatar at the top middle of the page -->
    <div class="avatar">
        <img src="${avatarDataURL}" alt="Avatar" width="200" height="200">
    </div>

    <!-- Display profile information -->
    <div class="profile-info">
        <h2>Welcome to Your Profile</h2>
        <p>Username: ${sessionScope.username}</p>
        <p>Email: ${sessionScope.email}</p>
        <!-- Add more profile information here -->
    </div>

    <!-- Add an Edit Avatar button/link with parameters -->
    <div class="edit-avatar">
        <a href="/avatar?avatarDataURL=${sessionScope.avatarDataURL}&skinColor=${sessionScope.skinColor}&eyeColor=${sessionScope.eyeColor}&hairType=${sessionScope.hairType}&hairColor=${sessionScope.hairColor}&noseSize=${sessionScope.noseSize}&mouthSize=${sessionScope.mouthSize}&glasses=${sessionScope.glasses}">Edit Avatar</a>
    </div>

    <div class="streak-message">
        <h2>Log in again tomorrow to extend your streak!</h2>
    </div>

    <div class="wrapper">
        <header>
            <p class="user-streak">${userStreak.streakCount} day streak</p>
        </header>
        <div class="calendar">
            <ul class="weeks">
                <li>Sun</li>
                <!-- Weekday abbreviations -->
                <li>Mon</li>
                <li>Tue</li>
                <li>Wed</li>
                <li>Thu</li>
                <li>Fri</li>
                <li>Sat</li>
            </ul>
            <ul class="days"></ul>
            <!-- Container for calendar days -->
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/streak/streak.js"></script>
</div>
</body>
</html>

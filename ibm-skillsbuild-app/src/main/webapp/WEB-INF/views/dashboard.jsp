<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to IBM Skills Build</title>
    <title>Dashboard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}css/dashboard/dashboard.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
</head>
<body >
<div class="container">
    <img src="https://b585204.smushcdn.com/585204/wp-content/uploads/2020/09/ibm-logo-2-1-300x131.png?lossy=0&strip=1&webp=0" alt="IBM"/>
      <span class="box"><h1>Skills Build Dashboard</h1></span>

    <button class="button dashboard-button" role="button">Dashboard</button>
    <button class="button leaderboard-button" role="button">Leaderboard</button>
    <button class="button log-button" role="button">Logout</button>
    <p class="streak">
        <%-- Display streak count or "0 day streak" if streak count is null --%>
        <c:choose>
            <c:when test="${empty userStreak}">
                0 day streak
            </c:when>
            <c:otherwise>
                ${userStreak.streakCount} day streak
            </c:otherwise>
        </c:choose>
    </p>
    <div class="courses">
        <%-- Create the containers for Available, Started, and Completed courses --%>
        <div class="status-courses">
            <h3>Available Courses</h3>
            <%-- Iterate over the list of courses and add the available ones --%>
            <c:forEach var="courseView" items="${courseViews}">
                <c:if test="${courseView.status == 'AVAILABLE'}">
                    <p><a href="${courseView.url}" target="_blank">${courseView.pathName}
                        - ${courseView.courseName}</a></p>
                </c:if>
            </c:forEach>
        </div>
        <div class="status-courses">
            <h3>Started Courses</h3>
            <%-- Iterate over the list of courses and add the started ones --%>
            <c:forEach var="courseView" items="${courseViews}">
                <c:if test="${courseView.status == 'STARTED'}">
                    <p><a href="${courseView.url}" target="_blank">${courseView.pathName}
                        - ${courseView.courseName}</a></p>
                </c:if>
            </c:forEach>
        </div>
        <div class="status-courses">
            <h3>Completed Courses</h3>
            <%-- Iterate over the list of courses and add the completed ones --%>
            <c:forEach var="courseView" items="${courseViews}">
                <c:if test="${courseView.status == 'COMPLETED'}">
                    <p><a href="${courseView.url}" target="_blank">${courseView.pathName}
                        - ${courseView.courseName}</a></p>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
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


    <div class="courses">
        <!-- Set a variable to keep track of the current course status -->
        <c:set var="currentStatus" value=""/>
        <!-- Iterate over the list of courses -->
        <c:forEach var="courseView" items="${courseViews}">
            <!-- Check if the status of the current course is different from the current status -->
            <c:if test="${not currentStatus.equals(courseView.status)}">
                <!-- If it is, update the current status and create a new container for this status -->
                <c:set var="currentStatus" value="${courseView.status}"/>
                <!-- Set the class of the container based on the current status -->
                <div class="<c:choose>
                <c:when test="${currentStatus == 'AVAILABLE'}">available-courses</c:when>
                <c:when test="${currentStatus == 'COMPLETED'}">completed-courses</c:when>
                <c:when test="${currentStatus == 'STARTED'}">started-courses</c:when>

            </c:choose>">
                <!-- Add a header for the current status -->
                <h3><c:choose>
                    <c:when test="${currentStatus == 'AVAILABLE'}">Available Courses</c:when>
                    <c:when test="${currentStatus == 'COMPLETED'}">Completed Courses</c:when>
                    <c:when test="${currentStatus == 'STARTED'}">Started Courses</c:when>
                </c:choose></h3>
            </c:if>
            <!-- Display the course information TODO: Replace with link to courses-->
            <p class="ligthhover">${courseView.pathName} - ${courseView.courseName}</p>
            <!-- Check if the status of the next course is different from the current status -->
            <c:if test="${courseViews.indexOf(courseView) + 1 == courseViews.size() or not currentStatus.equals(courseViews[courseViews.indexOf(courseView) + 1].status)}">
                <!-- If it is, close the current container -->
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>
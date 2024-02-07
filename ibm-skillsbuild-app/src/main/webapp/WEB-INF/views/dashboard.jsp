<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/global.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}css/dashboard/dashboard.css">
</head>
<body>
<div class="container">
    <h1>Dashboard</h1>
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
                <c:when test="${currentStatus == 'STARTED'}">started-courses</c:when>
                <c:when test="${currentStatus == 'COMPLETED'}">completed-courses</c:when>
            </c:choose>">
                <!-- Add a header for the current status -->
                <h3><c:choose>
                    <c:when test="${currentStatus == 'AVAILABLE'}">Available Courses</c:when>
                    <c:when test="${currentStatus == 'STARTED'}">Started Courses</c:when>
                    <c:when test="${currentStatus == 'COMPLETED'}">Completed Courses</c:when>
                </c:choose></h3>
            </c:if>
            <!-- Display the course information TODO: Replace with link to courses-->
            <p>${courseView.pathName} - ${courseView.courseName}</p>
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
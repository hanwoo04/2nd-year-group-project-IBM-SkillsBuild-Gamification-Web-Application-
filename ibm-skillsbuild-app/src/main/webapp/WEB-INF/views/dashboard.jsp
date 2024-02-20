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
    <jsp:include page="nav.jsp">
        <jsp:param name="activePage" value="jspName"/>
        <jsp:param name="user" value="${user}"/>
    </jsp:include>
    <h1>Dashboard</h1>
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
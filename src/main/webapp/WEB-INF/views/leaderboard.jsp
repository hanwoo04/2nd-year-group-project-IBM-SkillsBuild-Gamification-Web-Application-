<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Leaderboard</title>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/leaderboard/leaderboard.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="leaderboard"/>
</jsp:include>
<div class="container">
    <table id="global" class="leaderBoardHead">
        <tr>
            <th colspan="2" class="head">Leaderboard(Global)</th>
        </tr>
        <tr>
            <th>Player</th>
            <th>Score</th>
        </tr>
        <c:forEach items="${players}" var="user">
            <tr>
                <td> ${user.getUserName()}</td>
                <td> ${user.getScore()}</td>
            </tr>
        </c:forEach>
    </table>
    <table id="friend" class="leaderBoardHead">
        <tr>
            <th colspan="2" class="head">Leaderboard(Friends)</th>
        </tr>
        <tr>
            <th>Player</th>
            <th>Score</th>
        </tr>
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td> ${friend.getUserName()}</td>
                <td> ${friend.getScore()}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Leaderboard</title>
    <link rel="stylesheet" href="/Styling.css" type="text/css" >
</head>
<body><div>
<table id="global" class="leaderBoardHead">
    <tr>
        <th colspan="2" class="head">Leaderboard(Global)</th>
    </tr>
<%--    <tr>--%>
<%--        <td>Global</td>--%>
<%--        <td>Friends</td>--%>
<%--    </tr>--%>
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
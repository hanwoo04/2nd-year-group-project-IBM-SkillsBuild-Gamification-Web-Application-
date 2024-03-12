<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Friends/friendsList.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Friends</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="friendsPage"/>
</jsp:include>
    <div class="title">
        <h1>SkillsBuild Friends List</h1>
    </div>
<div class="searchBar">
    <form action="/viewFriends/search">
        <input type="text" name="usernameSearch" placeholder="Search for Users...">
        <input type="submit" value="Search">
    </form>
</div>
<c:if test="${searchResults.size()>0}">
<div class="results">
    <c:forEach items="${searchResults}" var="result">

        <p>${result.getUserName()},<a class="addFriend" href="addFriend?friendId=${result.id}&userId=${user.id}">Add Friend</a>

        </p>
    </c:forEach>
</div>
</c:if> <!-- Stops css showing up until anything is searched-->
<div class="container">
    <div class="friends">
    <p>${friends.size()} Friends</p>
    <c:forEach items="${friends}" var="friend">
        <p>${friend.getUserName()}</p>
    </c:forEach>
    </div>
   <!-- <div class="all">
        <p>${allUsers.size()} Friends</p>
        <c:forEach items="${allUsers}" var="user">
            <p>${user.getUserName()}</p>
        </c:forEach>
    </div>-->
</div>
</body>
</html>
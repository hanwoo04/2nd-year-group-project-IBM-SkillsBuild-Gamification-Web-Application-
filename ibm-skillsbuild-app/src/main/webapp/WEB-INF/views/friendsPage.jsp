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
        <h1>Friends List</h1>
    </div>
<div class="searchBar"><!-- SearchBar to search for users-->
    <form action="/viewFriends/search">
        <input type="text" name="usernameSearch" placeholder="Search for Users...">
        <input type="submit" value="Search">
    </form>
</div>
<!-- Only if the search provided result will the results be displayed-->
<c:if test="${searchResults.size()>0}">
<div class="results">
    <c:forEach items="${searchResults}" var="result">

        <p>${result.getUserName()},
            <form action="addFriend" method="post">
        <input type="hidden" name="friendId" value="${result.id}">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Add Friend" class="addFriend">
    </form><!-- The form acts as a button to add the user as a friend providing all necessary information in the background-->
        </p>
    </c:forEach>
</div>
</c:if> <!-- Stops css showing up until anything is searched-->
<div class="container">
    <div class="friends">
    <p>${friends.size()} Friends</p><!--Provides list of friends along with option to remove them as a friend-->
    <c:forEach items="${friends}" var="friend">
        <p>${friend.getUserName()},
        <form action="deleteFriend" method="post">
            <input type="hidden" name="friendId" value="${friend.id}">
            <input type="hidden" name="userId" value="${user.id}">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Remove" class="removeFriend">
        </form>
        </p>
    </c:forEach>
    </div>
    <div class="followers"><!-- Shows a list of people that have the user in their friends list/follow the user-->
        <p>${followers.size()} Followers </p>
        <c:forEach items="${followers}" var="follower">
            <p>${follower.getUserName()}</p>
        </c:forEach>
    </div>
</div>
</body>
</html>
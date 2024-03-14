<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Comment and Rating</title>
</head>
<body>
<h2>Comment</h2>

<form action="${pageContext.request.contextPath}/comment-rating/comment" method="post">
    <label>
        <textarea name="text" rows="3" cols="50" placeholder="Add a comment"></textarea>
    </label>
    <br>
    <input type="submit" value="Add Comment">
</form>

<h2>Rating</h2>

<form action="${pageContext.request.contextPath}/comment-rating/rating" method="post">
    <label for="rating">Add a rating:</label>
    <select name="value" id="rating">
        <option value="1">1 star</option>
        <option value="2">2 stars</option>
        <option value="3">3 stars</option>
        <option value="4">4 stars</option>
        <option value="5">5 stars</option>
    </select>
    <br>
    <input type="submit" value="Add Rating">
</form>
</body>
</html>

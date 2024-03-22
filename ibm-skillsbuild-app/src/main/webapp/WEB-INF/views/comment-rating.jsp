<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Comment and Rating</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/comment-rating/Comment-Rating.css">
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="activePage" value="comment-rating"/>
</jsp:include>
<%--this is to display the comment and rating on the navigation bar in the webpage--%>
<h2>Comment</h2>
<div class="comments">
    <ul id="commentList">
        <li>I loved studying this course!</li>
    </ul>
</div>

<h2 class="comment-container">
    <img src="${pageContext.request.contextPath}/img/textbox.png" alt="Textbox Image" class="textbox-image">
    <%--putting image inside the comment-contatiner--%>
    <label for="commentArea"></label><textarea id="commentArea" class="comment-area" placeholder="Add a comment here!"></textarea>
</h2>
<br>
<button id="addCommentButton" class="addcomment">Add Comment</button>
<%--this is the button for adding comments--%>

<script>
    function addCommentList() {
        const commentText = document.getElementById('commentArea').value.trim();

        if (commentText !== '') {
            const newComment = document.createElement('li');
            newComment.textContent = commentText;

            const existingComments = document.getElementById('commentList');
            existingComments.appendChild(newComment);
            <%--the user should enter a comment before clicking on the button--%>
        } else {
            alert('Please enter a comment.');
        }
    }
    document.getElementById('addCommentButton').addEventListener('click', addCommentList);
</script>
<br>

<h2 class="rating-star">
    Rating <img src="${pageContext.request.contextPath}/img/star.png" alt="Rating Image" class="rating-image">
    <%--Displaying star image next to the title Rating--%>
</h2>

<ul id="ratingList" class="rating">
    <li>5 star/stars</li>
</ul>

<form id="ratingForm">
    <label for="rating" class="h22">Add a rating:</label>
    <%--5 options between 1 to 5 stars--%>
    <select name="value" id="rating">
        <option value="1">1 star</option>
        <option value="2">2 stars</option>
        <option value="3">3 stars</option>
        <option value="4">4 stars</option>
        <option value="5">5 stars</option>
    </select>
    <br>
    <br>
    <button id="addRatingButton" class="addrating">Add Rating</button>
</form>


<script>
    <%--function for list of rating--%>
    function addRatingList(event) {
        event.preventDefault();
        const rating = document.getElementById('rating').value;

        if (rating !== '') {

            const newRating = document.createElement('li');
            newRating.textContent =rating + " star/stars";
            <%--Displaying pre setted existing rating list--%>
            const existingRatings = document.getElementById('ratingList');
            existingRatings.appendChild(newRating);
        }
    }

    document.getElementById('addRatingButton').addEventListener('click', addRatingList);
</script>
</body>
</html>

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
<h2>Comment</h2>
<div class="comments">
    <ul id="commentList">
        <li>I loved studying this course!</li>
    </ul>
</div>

<h2 class="comment-container">
    <img src="${pageContext.request.contextPath}/img/textbox.png" alt="Textbox Image" class="textbox-image">
    <textarea id="commentBox" class="comment-box" placeholder="Add a comment here!"></textarea>
</h2>
<br>
<button id="addCommentButton" class="addcomment">Add Comment</button>

<script>
    function addCommentButtonClick() {
        const commentText = document.getElementById('commentBox').value.trim();

        if (commentText !== '') {
            const newCommentItem = document.createElement('li');
            newCommentItem.textContent = commentText;

            const existingCommentsList = document.getElementById('commentList');
            existingCommentsList.appendChild(newCommentItem);

        } else {
            alert('Please enter a comment before adding.');
        }
    }
    document.getElementById('addCommentButton').addEventListener('click', addCommentButtonClick);
</script>
<br>

<h2 class="rating-star">
    Rating <img src="${pageContext.request.contextPath}/img/star.png" alt="Rating Image" class="rating-image">
</h2>

<ul id="ratingList" class="rating">
    <li>5 star/stars</li>
</ul>

<form id="ratingForm">
    <label for="rating" class="h22">Add a rating:</label>
    <select name="value" id="rating">
        <option value="1">1 star</option>
        <option value="2">2 stars</option>
        <option value="3">3 stars</option>
        <option value="4">4 stars</option>
        <option value="5">5 stars</option>
    </select>
    <br>
    <br>
    <button id="addRatingBtn" class="addrating">Add Rating</button>
</form>


<script>
    function addRatingButtonClick(event) {
        event.preventDefault();

        const ratingValue = document.getElementById('rating').value;

        if (ratingValue !== '') {

            const newRatingItem = document.createElement('li');
            newRatingItem.textContent =ratingValue + " star/stars";

            const existingRatingsList = document.getElementById('ratingList');
            existingRatingsList.appendChild(newRatingItem);
        }
    }

    document.getElementById('addRatingBtn').addEventListener('click', addRatingButtonClick);
</script>
</body>
</html>

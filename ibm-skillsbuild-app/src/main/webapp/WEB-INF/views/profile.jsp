<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        .container {
            margin-top: 50px;
        }
        .avatar {
            margin-bottom: 20px;
        }
        .profile-info {
            margin-bottom: 20px;
        }
        .edit-avatar {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- Display the avatar at the top middle of the page -->
    <div class="avatar">
        <img src="data:image/png;base64,${avatarDataURL}" alt="Avatar" width="200" height="200">
    </div>



    <!-- Display profile information -->
    <div class="profile-info">
        <h2>Welcome to Your Profile</h2>
        <p>Username: ${sessionScope.username}</p>
        <p>Email: ${sessionScope.email}</p>
        <!-- Add more profile information here -->
    </div>

    <!-- Add an Edit Avatar button/link with parameters -->
    <div class="edit-avatar">
        <a href="/avatar?skinColor=${sessionScope.skinColor}&eyeColor=${sessionScope.eyeColor}">Edit Avatar</a>
    </div>
</div>
</body>
</html>

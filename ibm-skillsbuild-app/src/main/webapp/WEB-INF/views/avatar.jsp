<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Avatar Customization</title>
    <link rel="stylesheet" href="/css/avatar/avatar.css">

</head>
<body>
<div class="container">
    <canvas id="avatarCanvas" width="200" height="200"></canvas>

    <div class="options">
        <label for="skinColor">Skin Color:</label>
        <select id="skinColor" onchange="changeSkinColor()">
            <option value="#ffddb3">Light</option>
            <option value="#d2a679">Medium</option>
            <option value="#8b5a2b">Dark</option>
        </select>

        <label for="eyeColor">Eye Color:</label>
        <select id="eyeColor" onchange="changeEyeColor()">
            <option value="#66533d">Brown</option>
            <option value="#348899">Blue</option>
            <option value="#90c3d4">Gray</option>
        </select>
    </div>

    <button onclick="saveAvatar()">Save Avatar</button>
</div>

<script src="/js/avatar/avatar.js"></script>

</body>
</html>

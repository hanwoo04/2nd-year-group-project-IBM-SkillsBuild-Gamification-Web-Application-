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
            <option value="#4e3c28">Brown</option> <!-- Darker brown -->
            <option value="#348899">Blue</option>
            <option value="#90c3d4">Gray</option>
        </select>

        <label for="hairType">Hair Type:</label>
        <select id="hairType" onchange="changeHairType()">
            <option value="short">Short Hair</option>
            <option value="long">Long Hair</option>
            <option value="curly">Curly Hair</option>
            <option value="bald">Bald</option> <!-- Added bald option -->
        </select>

        <label for="hairColor">Hair Color:</label>
        <select id="hairColor" onchange="changeHairColor()">
            <option value="#000000">Black</option>
            <option value="#D2B48C">Blonde</option>
            <option value="#8B4513">Brown</option>
            <option value="#3D2B1F">Brunette</option>
        </select>

        <label for="noseSize">Nose Size:</label>
        <select id="noseSize" onchange="changeNoseSize()">
            <option value="small">Small Nose</option>
            <option value="medium">Medium Nose</option>
            <option value="big">Big Nose</option>
        </select>

        <!-- Add the mouth size selection dropdown -->
        <label for="mouthSize">Mouth Size:</label>
        <select id="mouthSize" onchange="changeMouthSize()">
            <option value="small">Small Mouth</option>
            <option value="medium">Medium Mouth</option>
            <option value="big">Big Mouth</option>
        </select>

        <!-- Add checkbox for glasses -->
        <label for="glassesCheckbox">Wear Glasses:</label>
        <input type="checkbox" id="glassesCheckbox" onchange="changeGlasses()">

        <!-- Add the save button below the glasses option -->
        <button onclick="saveAvatar()">Save Avatar</button>
    </div>

</div>


<script src="/js/avatar/avatar.js"></script>
</body>
</html>

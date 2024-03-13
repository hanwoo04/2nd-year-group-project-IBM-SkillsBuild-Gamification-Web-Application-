// avatar.js

// Get canvas and context
const canvas = document.getElementById('avatarCanvas');
const ctx = canvas.getContext('2d');

// Retrieve previous skin color and eye color from sessionStorage
let skinColor = sessionStorage.getItem('skinColor') || "#ffddb3"; // Default to light
let eyeColor = sessionStorage.getItem('eyeColor') || "#66533d"; // Default to brown

// Function to draw the avatar
function drawAvatar() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // Draw head with corresponding skin color
    ctx.fillStyle = skinColor;
    ctx.beginPath();
    ctx.arc(100, 100, 70, 0, Math.PI * 2);
    ctx.fill();

    // Draw eyes with corresponding eye color
    ctx.fillStyle = "#ffffff"; // White iris
    ctx.beginPath();
    ctx.arc(80, 90, 10, 0, Math.PI * 2); // Left eye
    ctx.arc(120, 90, 10, 0, Math.PI * 2); // Right eye
    ctx.fill();

    // Draw pupils (same color as the eye color)
    ctx.fillStyle = eyeColor; // Pupil color matches eye color
    ctx.beginPath();
    ctx.arc(80, 90, 5, 0, Math.PI * 2); // Left pupil
    ctx.arc(120, 90, 5, 0, Math.PI * 2); // Right pupil
    ctx.fill();
}

// Function to handle skin color change
function changeSkinColor() {
    skinColor = document.getElementById("skinColor").value;
    drawAvatar(); // Redraw the avatar with the new skin color
    // Store the current state in sessionStorage
    sessionStorage.setItem('skinColor', skinColor);
}

// Function to handle eye color change
function changeEyeColor() {
    eyeColor = document.getElementById("eyeColor").value;
    drawAvatar(); // Redraw the avatar with the new eye color
    // Store the current state in sessionStorage
    sessionStorage.setItem('eyeColor', eyeColor);
}

// Function to save the avatar
function saveAvatar() {
    const dataURL = canvas.toDataURL();

    // Convert data URL to Blob
    const blob = dataURItoBlob(dataURL);

    // Create FormData object
    const formData = new FormData();
    formData.append('avatar', blob);
    formData.append('skinColor', skinColor);
    formData.append('eyeColor', eyeColor);

    // Send FormData using fetch
    fetch('/saveAvatar', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                // Handle success response
                console.log('Avatar saved successfully!');
                alert('Avatar saved successfully!');
                // Redirect to the profile page
                window.location.href = '/profile';
            } else {
                // Handle error response
                console.error('Error saving avatar:', response.statusText);
                alert(`Error saving avatar: ${response.statusText}`);
                // Optional: Redirect to an error page or handle it as needed
            }
        })
        .catch(error => {
            // Handle network or other errors
            console.error('Error saving avatar:', error);
            alert('Error saving avatar. Check the console for details.');
        });

    // Function to convert data URI to Blob
    function dataURItoBlob(dataURI) {
        const byteString = atob(dataURI.split(',')[1]);
        const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
        const ab = new ArrayBuffer(byteString.length);
        const ia = new Uint8Array(ab);
        for (let i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
        return new Blob([ab], { type: mimeString });
    }

}





// Call drawAvatar function when the page loads
drawAvatar();

import fetchData from './fetchData.js';

// Fetch data when the page loads
fetchData().catch(error => console.error('Error fetching data:', error));
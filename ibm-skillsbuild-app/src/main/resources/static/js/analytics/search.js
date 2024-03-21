import fetchData from './fetchData.js';
import createChart from "./chart.js";

// Function to search for a course
async function searchCourse() {
  const data = await fetchData();
  // Get the search query
  const query = document.getElementById('search').value.toLowerCase();

  // Find the courses that match the query
  const matchingCourses = data.filter(
      item => item.name.toLowerCase().includes(query));

  // If matching courses are found, display their graph
  if (matchingCourses.length > 0) {
    createChart(matchingCourses, '');
  } else {
    alert('No course found with that name');
  }
}

// Function for displaying matching courses in the dropdown list
function displayMatchingCourses(query, data) {
  // Get the dropdown list element
  const dropdown = document.getElementById('searchDropdown');

  // Clear the dropdown list
  dropdown.innerHTML = '';

  // Find the courses that match the query
  const matchingCourses = data.filter(
      item => item.name.toLowerCase().includes(query));

  // For each matching course, create a new HTML element and append it to the dropdown list
  matchingCourses.forEach(course => {
    const option = document.createElement('option');
    option.value = course.name;
    dropdown.appendChild(option);
  });
}

export {searchCourse, displayMatchingCourses};

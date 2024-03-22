import fetchData from './fetchData.js';
import createChart from "./chart.js";
import displayCourseInfo from "./displayCourseInfo.js";

// Function to search for a course
async function searchCourse(event) {
  // Get the search query
  const query = event?.target?.value.toLowerCase() || document.getElementById(
      'search').value.toLowerCase();

  const data = await fetchData();
  // Find the courses that match the query
  const matchingCourses = data.filter(
      item => item.name.toLowerCase().includes(query));

  // If matching courses are found, display their graph
  if (matchingCourses.length > 0) {
    createChart(matchingCourses, '');

    // If only one course matches the query, display its data
    if (matchingCourses.length === 1) {
      displayCourseInfo(matchingCourses[0]);
    }
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
    // Add 'click' event listener to each option to search for the course
    option.addEventListener('click', searchCourse);
    dropdown.appendChild(option);
  });
}

export {searchCourse, displayMatchingCourses};

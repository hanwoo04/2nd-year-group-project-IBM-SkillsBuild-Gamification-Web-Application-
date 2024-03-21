import fetchData from './fetchData.js';
import createChart from './chart.js';
import sortData from './sortData.js';
import {searchCourse, displayMatchingCourses} from './search.js';

// Ensure the DOM is fully loaded before adding event listeners
document.addEventListener('DOMContentLoaded', (event) => {
  // Event listener for the search button
  document.getElementById('searchButton').addEventListener('click',
      searchCourse);

  // Event listener for the search bar
  document.getElementById('search').addEventListener('input',
      async function () {
        const data = await fetchData();
        displayMatchingCourses(this.value.toLowerCase(), data);
      });

  // Event listener for the search bar when a course is selected from the dropdown
  document.getElementById('search').addEventListener('change', searchCourse);

  // Event listener for the filter selection
  document.getElementById('filter').addEventListener('change',
      async function () {
        const data = await fetchData();
        // Check if the selected filter is 'All'
        if (this.value === 'all') {
          // Create a new chart with the original, unsorted data
          createChart(data, '');
        } else {
          // Sort the data based on the selected filter
          const sortedData = sortData(data, this.value);
          // Create a new chart with the sorted data and the selected filter
          createChart(sortedData, this.value);
        }
      });
});

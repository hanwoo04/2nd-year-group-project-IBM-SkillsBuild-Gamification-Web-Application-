let chart;

// Function to fetch data from the server
async function fetchData() {
  const response = await fetch('/analyticsData');
  const data = await response.json();
  createChart(data, '');
  return data;
}

// Function to create the chart
function createChart(data, filter) {
  // Prepare the data
  const courses = data.map(item => {
    // Truncate the course name to 30 characters and append "..." if it's longer
    return item.name.length > 30 ? item.name.substring(0, 30) + '...'
        : item.name;
  });
  const startedData = data.map(item => item.started);
  const completedData = data.map(item => item.completed);
  const ratedData = data.map(item => item.rated);

  // Create a new canvas element
  const canvas = document.createElement('canvas');
  canvas.id = 'courseAnalyticsChart';

  // Append the canvas to the parent container
  const chartContainer = document.getElementById('chartContainer');
  chartContainer.innerHTML = '';
  chartContainer.appendChild(canvas);

  const ctx = canvas.getContext('2d');

  // Determine the type and datasets of the chart based on the selected filter
  let type, datasets;
  switch (filter) {
    case 'enrollment':
      type = 'bar';
      datasets = [
        {
          label: 'Started',
          data: startedData,
          backgroundColor: 'red',
          stack: 'Stack 0'
        },
        {
          label: 'Completed',
          data: completedData,
          backgroundColor: 'blue',
          stack: 'Stack 0'
        }
      ];
      break;
    case 'trending':
      type = 'line';
      datasets = [
        {
          label: 'Enrollment Last 30 Days',
          data: data.map(item => item.enrollmentLast30Days),
          fill: false,
          borderColor: 'blue'
        }
      ];
      break;
    case 'highestCompletionRate':
    case 'lowestCompletionRate':
      type = 'bar';
      datasets = [
        {
          label: 'Completion Rate',
          data: data.map(
              item => (item.completed / (item.started + item.completed)) * 100),
          backgroundColor: 'green'
        }
      ];
      break;
    default:
      type = 'bar';
      datasets = [
        {
          label: 'Started',
          data: startedData,
          backgroundColor: 'red'
        },
        {
          label: 'Completed',
          data: completedData,
          backgroundColor: 'blue'
        },
        {
          label: 'Rated',
          data: ratedData,
          backgroundColor: 'green'
        }
      ];
  }

  chart = new Chart(ctx, {
    type: type,
    data: {
      labels: courses,
      datasets: datasets
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: true
        }
      },
      title: {
        display: true,
        text: 'Course Analytics'
      },
    }
  });

  // Attach click event listener to the canvas
  canvas.onclick = function (evt) {
    // Get the chart's scale to calculate the position of the labels
    const xAxis = chart.scales['x'];

    // Calculate the position of the click event relative to the canvas
    const eventPosition = {
      x: evt.clientX - canvas.getBoundingClientRect().left,
      y: evt.clientY - canvas.getBoundingClientRect().top
    };

    // Check if the click event is within the area of a label
    xAxis.ticks.forEach((value, index) => {
      const labelWidth = xAxis.getPixelForTick(index + 1)
          - xAxis.getPixelForTick(index);
      const left = xAxis.getPixelForTick(index) - labelWidth / 2;
      const right = xAxis.getPixelForTick(index) + labelWidth / 2;
      const bottom = xAxis.bottom;
      const top = xAxis.top;

      if (eventPosition.x >= left && eventPosition.x <= right && eventPosition.y
          >= top && eventPosition.y <= bottom) {
        // Find the corresponding course
        const course = data.find(item => item.name === value.label);

        // Display the course info and create a new chart with only that course
        if (course) {
          displayCourseInfo(course);
          createChart([course], '');
        }
      }
    });
  }
}

// Function to sort the data based on the selected filter
function sortData(data, filter) {
  // Create a copy of the data array
  const dataCopy = [...data];

  switch (filter) {
    case 'enrollment':
      return dataCopy.sort(
          (a, b) => (b.started + b.completed) - (a.started + a.completed));
    case 'highestCompletionRate':
      return dataCopy.sort(
          (a, b) => (b.completed / b.started) - (a.completed / a.started));
    case 'lowestCompletionRate':
      return dataCopy.sort(
          (a, b) => (a.completed / a.started) - (b.completed / b.started));
    case 'trending':
      return dataCopy.sort(
          (a, b) => b.enrollmentLast30Days - a.enrollmentLast30Days);
  }
}

// Function to search for a course
async function searchCourse() {
  const data = await fetchData();
  // Get the search query
  const query = document.getElementById('search').value.toLowerCase();

  // Find the course that matches the query
  const course = data.find(item => item.name.toLowerCase() === query);

  // If a matching course is found, display its graph
  if (course) {
    createChart([course], '');
  } else {
    alert('No course found with that name');
  }
}

// Event listener for the filter selection
document.getElementById('filter').addEventListener('change', async function () {
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

// Function to display course information
function displayCourseInfo(course) {
  // Get the element where the course information will be displayed
  const infoContainer = document.getElementById('infoContainer');

  // Create the HTML for the course information
  const html = `
    <h3>${course.name}</h3>
    <p>Started: ${course.started}</p>
    <p>Completed: ${course.completed}</p>
    <p>Rated: ${course.rated}</p>
    <p>Enrollment Last 30 Days: ${course.enrollmentLast30Days}</p>
  `;

  // Set the HTML of the infoContainer to the created HTML
  infoContainer.innerHTML = html;
}

// Fetch data when the page loads
fetchData().catch(error => console.error('Error fetching data:', error));
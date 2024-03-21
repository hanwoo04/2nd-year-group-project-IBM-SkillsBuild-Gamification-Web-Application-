import displayCourseInfo from "./displayCourseInfo.js";

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

  let chart = new Chart(ctx, {
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

export default createChart;

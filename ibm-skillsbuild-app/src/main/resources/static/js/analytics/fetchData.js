import createChart from "./chart.js";

// Function to fetch data from the server
async function fetchData(createNewChart = true) {
  const response = await fetch('/analyticsData');
  const data = await response.json();
  if (createNewChart) {
    createChart(data, '');
  }
  return data;
}

export default fetchData;

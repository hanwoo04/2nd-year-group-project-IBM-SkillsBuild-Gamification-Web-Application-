import createChart from "./chart.js";

// Function to fetch data from the server
async function fetchData() {
  const response = await fetch('/analyticsData');
  const data = await response.json();
  createChart(data, '');
  return data;
}

export default fetchData;

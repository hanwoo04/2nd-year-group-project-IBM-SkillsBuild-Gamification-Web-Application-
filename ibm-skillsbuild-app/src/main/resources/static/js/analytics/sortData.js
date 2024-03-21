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

export default sortData;

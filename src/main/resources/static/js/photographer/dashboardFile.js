// Sales Chart
const salesCtx = document.getElementById('salesChart').getContext('2d');
const salesChart = new Chart(salesCtx, {
  type: 'line',
  data: {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
    datasets: [{
      label: 'Sales ($)',
      data: [300, 450, 600, 500, 700, 800],
      borderColor: '#36a2eb',
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      fill: true,
    }]
  },
  options: {
    responsive: true,
  }
});

// Engagement Chart
const engagementCtx = document.getElementById('engagementChart').getContext('2d');
const engagementChart = new Chart(engagementCtx, {
  type: 'pie',
  data: {
    labels: ['Portraits', 'Landscapes', 'Events'],
    datasets: [{
      label: 'Engagement',
      data: [50, 30, 20],
      backgroundColor: ['#ff6384', '#36a2eb', '#ffce56'],
    }]
  },
  options: {
    responsive: true,
  }
});
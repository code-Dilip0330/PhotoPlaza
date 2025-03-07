// Revenue Over Time Chart
const revenueCtx = document.getElementById('revenueChart').getContext('2d');
const revenueChart = new Chart(revenueCtx, {
  type: 'line',
  data: {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
    datasets: [{
      label: 'Revenue ($)',
      data: [300, 450, 600, 500, 700, 800],
      borderColor: '#36a2eb',
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      fill: true,
    }]
  },
  options: {
    responsive: true,
    plugins: {
      tooltip: {
        enabled: true,
      }
    }
  }
});

// Product Engagement Chart
const productEngagementCtx = document.getElementById('productEngagementChart').getContext('2d');
const productEngagementChart = new Chart(productEngagementCtx, {
  type: 'pie',
  data: {
    labels: ['Portraits', 'Landscapes', 'Events'],
    datasets: [{
      label: 'Sales',
      data: [120, 90, 60],
      backgroundColor: ['#ff6384', '#36a2eb', '#ffce56'],
    }]
  },
  options: {
    responsive: true,
  }
});

// Customer Interaction Chart
const customerInteractionCtx = document.getElementById('customerInteractionChart').getContext('2d');
const customerInteractionChart = new Chart(customerInteractionCtx, {
  type: 'bar',
  data: {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
    datasets: [{
      label: 'Interactions',
      data: [50, 70, 60, 80, 90, 100],
      backgroundColor: '#4bc0c0',
    }]
  },
  options: {
    responsive: true,
  }
});

// Sales vs. Ratings Chart
const salesRatingsCtx = document.getElementById('salesRatingsChart').getContext('2d');
const salesRatingsChart = new Chart(salesRatingsCtx, {
  type: 'scatter',
  data: {
    datasets: [{
      label: 'Sales vs. Ratings',
      data: [
        { x: 4.5, y: 40 },
        { x: 4.7, y: 50 },
        { x: 4.2, y: 30 },
        { x: 4.8, y: 60 },
      ],
      backgroundColor: '#ff6384',
    }]
  },
  options: {
    responsive: true,
    scales: {
      x: {
        type: 'linear',
        position: 'bottom',
        title: {
          display: true,
          text: 'Rating',
        }
      },
      y: {
        title: {
          display: true,
          text: 'Sales',
        }
      }
    }
  }
});
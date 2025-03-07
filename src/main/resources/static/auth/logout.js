function confirmLogout(event) {
        event.preventDefault(); // Stop default link behavior
        if (confirm("Are you sure you want to logout?")) {
            document.getElementById("logoutForm").submit(); // Submit form if confirmed
        }
    }
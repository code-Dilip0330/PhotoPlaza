    // Search Toggle
    const searchContainer = document.getElementById('searchContainer');
    const searchIcon = searchContainer.querySelector('.search-icon');

    searchIcon.addEventListener('click', () => {
        searchContainer.classList.toggle('search-active');
    });

    // Profile Dropdown
    const profile = document.getElementById('profile');
    const dropdown = document.getElementById('dropdown');

    profile.addEventListener('click', (e) => {
        e.stopPropagation();
        dropdown.classList.toggle('active');
    });

    // Close dropdown when clicking outside
    document.addEventListener('click', (e) => {
        if (!profile.contains(e.target)) {
            dropdown.classList.remove('active');
        }
    });
    // Get references to the input and form
    const searchInput = document.getElementById('searchInput');
    const searchForm = document.getElementById('searchForm');

    // Set a delay time (in milliseconds)
    const delay = 1000; // 500ms = 0.5 seconds

     // Variable to hold the timeout ID
      let timeoutId;

      // Add an event listener to the input field
      searchInput.addEventListener('keyup', function () {
        // Clear the previous timeout (if any)
        clearTimeout(timeoutId);

        // Set a new timeout to submit the form after the user stops typing
        timeoutId = setTimeout(function () {
          // Only submit if input length is >= 3
          if (searchInput.value.length >= 3) {
            searchForm.submit(); // Submit the form
          }
        }, delay);
      });


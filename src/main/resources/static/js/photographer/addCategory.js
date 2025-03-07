document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("categoryForm");
    const categoryNameInput = document.getElementById("categoryName");
    const nameError = document.getElementById("nameError");
    const dateCreatedInput = document.getElementById("dateCreated");

    // Example: List of existing categories (for uniqueness validation)
    const existingCategories = ["Electronics", "Clothing", "Books"];

    // Set current date and time for the hidden field
    const now = new Date();
    dateCreatedInput.value = now.toISOString();

    // Validate category name
    categoryNameInput.addEventListener("input", function () {
      const categoryName = categoryNameInput.value.trim();

      if (categoryName === "") {
        nameError.textContent = "Category name is required.";
        nameError.style.display = "block";
      } else if (existingCategories.includes(categoryName)) {
        nameError.textContent = "Category name must be unique.";
        nameError.style.display = "block";
      } else {
        nameError.style.display = "none";
      }
    });

    // Handle form submission
    form.addEventListener("submit", function (event) {
      const categoryName = categoryNameInput.value.trim();

      if (categoryName === "" || existingCategories.includes(categoryName)) {
        event.preventDefault(); // Prevent form submission
        nameError.style.display = "block";
      } else {
        alert("Category added successfully!");
        // Here you can add code to send the form data to a server
      }
    });

    // Handle form reset
    form.addEventListener("reset", function () {
      nameError.style.display = "none";
    });
  });
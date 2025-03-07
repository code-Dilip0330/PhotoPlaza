let categories = [
      { id: 1, name: "Wedding", dateCreated: "2023-10-01", status: "Active" },
      { id: 2, name: "Portrait", dateCreated: "2023-10-02", status: "Inactive" }
    ];

    // Render categories table
    function renderCategories() {
      const tableBody = document.getElementById('categoryTableBody');
      tableBody.innerHTML = '';
      categories.forEach(category => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${category.id}</td>
          <td>${category.name}</td>
          <td>${category.dateCreated}</td>
          <td><span class="status-${category.status.toLowerCase()}">${category.status}</span></td>
          <td class="action-buttons">
            <button class="edit-btn" onclick="editCategory(${category.id})">Edit</button>
            <button class="delete-btn" onclick="deleteCategory(${category.id})">Delete</button>
          </td>
        `;
        tableBody.appendChild(row);
      });
    }

    // Add new category
    function addCategory() {
      const categoryName = document.getElementById('categoryName').value.trim();
      const errorMessage = document.getElementById('error-message');

      if (!categoryName) {
        alert('Please enter a category name.');
        return;
      }

      // Check for duplicate category name
      if (categories.some(cat => cat.name.toLowerCase() === categoryName.toLowerCase())) {
        errorMessage.style.display = 'block';
        return;
      }

      errorMessage.style.display = 'none';

      const newCategory = {
        id: categories.length + 1,
        name: categoryName,
        dateCreated: new Date().toISOString().split('T')[0],
        status: "Active"
      };

      categories.push(newCategory);
      renderCategories();
      document.getElementById('categoryName').value = '';
    }

    // Edit category
    function editCategory(id) {
      const category = categories.find(cat => cat.id === id);
      const newName = prompt('Edit category name:', category.name);

      if (newName && !categories.some(cat => cat.name.toLowerCase() === newName.toLowerCase())) {
        category.name = newName;
        renderCategories();
      } else {
        alert('Invalid name or duplicate category.');
      }
    }

    // Delete category
    function deleteCategory(id) {
      if (confirm('Are you sure you want to delete this category?')) {
        categories = categories.filter(cat => cat.id !== id);
        renderCategories();
      }
    }

    // Initial render
    renderCategories();
// Variables to manage pagination state
let currentPage = 1; // Holds the current page number
const itemsPerPage = 3; // Number of items to display per page
const apiEndpoint = "/api/products"; // Backend API endpoint

// DOM elements
const photoTable = document.getElementById("photoTable");
const prevPageBtn = document.getElementById("prevPage");
const nextPageBtn = document.getElementById("nextPage");
const currentPageSpan = document.getElementById("currentPage");
const sortBtn = document.getElementById("sortBtn");
const searchInput = document.getElementById("search");

// Sorting state
let currentSort = "desc";

// Fetch data for the current page from the API
async function fetchPaginatedData(page = 1, size = itemsPerPage, search = "", sort = currentSort) {
    try {
        // Construct URL with query params for pagination, search, and sort
        const response = await fetch(
            `${apiEndpoint}?page=${page}&size=${size}&search=${search}&sort=${sort}`
        );

        const data = await response.json(); // Get JSON response (e.g., `{ products, currentPage, totalItems, totalPages }`)
        return data;
    } catch (error) {
        console.error("Error fetching paginated data:", error);
        return null; // Return null if there's an error
    }
}

// Render table with dynamic data
async function renderTable() {
    const searchQuery = searchInput.value.toLowerCase(); // Get current search value
    const data = await fetchPaginatedData(currentPage, itemsPerPage, searchQuery, currentSort);

    if (data && data.products) {
        const tbody = photoTable.querySelector("tbody");
        tbody.innerHTML = ""; // Clear table body

        // Populate table rows dynamically
        data.products.forEach((product) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${product.id}</td>
                <td>${product.title}</td>
                <td>${product.category}</td>
                <td>$${product.price.toFixed(2)}</td>
                <td>${product.uploadDate}</td>
                <td>
                    <button class="details-btn" data-id="${product.id}">Details</button>
                </td>
            `;
            tbody.appendChild(row);
        });

        // Update the current pagination state
        updatePagination(data.currentPage, data.totalPages);
    }
}

// Update pagination buttons and display
function updatePagination(current, total) {
    currentPageSpan.textContent = `Page ${current} of ${total}`; // Update pagination text
    prevPageBtn.disabled = current === 1; // Disable "Previous" if on first page
    nextPageBtn.disabled = current === total; // Disable "Next" if on last page
}

// Event listener for sorting
sortBtn.addEventListener("click", () => {
    // Toggle current sort order and re-render the table
    currentSort = currentSort === "asc" ? "desc" : "asc";
    renderTable();
});

// Event listener for search
searchInput.addEventListener("input", () => {
    // Reset to the first page and fetch filtered data
    currentPage = 1;
    renderTable();
});

// Event listeners for pagination buttons
prevPageBtn.addEventListener("click", () => {
    if (currentPage > 1) {
        currentPage--; // Move to the previous page
        renderTable();
    }
});

nextPageBtn.addEventListener("click", () => {
    currentPage++; // Move to the next page
    renderTable();
});

// Initial table rendering
renderTable();
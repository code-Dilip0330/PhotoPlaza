document.addEventListener('DOMContentLoaded', function () {
    // Get the modal and its elements
    const modal = document.getElementById('imageModal');
    const modalImg = document.getElementById('modalImage');
    const modalTitle = document.getElementById('modalTitle');
    const modalDescription = document.getElementById('modalDescription');
    const modalPrice = document.getElementById('modalPrice');
    const modalPhotographerImage = document.getElementById('modalPhotographerImage');
    const modalPhotographerName = document.getElementById('modalPhotographerName');
    const closeBtn = document.getElementsByClassName('close')[0];

    // Add click event to all images
    document.querySelectorAll('.grid-item img').forEach(img => {
        img.addEventListener('click', function () {
            modal.style.display = "block";
            modalImg.src = this.src;
            modalTitle.textContent = this.dataset.title;
            modalDescription.textContent = this.dataset.description;
            modalPrice.textContent = `$${parseFloat(this.dataset.price).toFixed(2)}`;
            modalPhotographerImage.src = this.dataset.photographerImage; // Assuming you have this data attribute
            modalPhotographerName.textContent = this.dataset.photographerName; // Assuming you have this data attribute
        });
    });

    // Close modal with close button
    closeBtn.addEventListener('click', function () {
        modal.style.display = "none";
    });

    // Close modal when clicking outside
    window.addEventListener('click', function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });

    // Close modal with ESC key
    document.addEventListener('keydown', function (event) {
        if (event.key === "Escape" && modal.style.display === "block") {
            modal.style.display = "none";
        }
    });
});
//document.addEventListener('DOMContentLoaded', function() {
//    // Get the modal and its elements
//    const modal = document.getElementById('imageModal');
//    const modalImg = document.getElementById('modalImage');
//    const modalTitle = document.getElementById('modalTitle');
//    const modalDescription = document.getElementById('modalDescription');
//    const modalPrice = document.getElementById('modalPrice');
//    const closeBtn = document.getElementsByClassName('close')[0];
//
//    // Add click event to all images
//    document.querySelectorAll('.grid-item img').forEach(img => {
//        img.addEventListener('click', function() {
//            modal.style.display = "block";
//            modalImg.src = this.src;
//            modalTitle.textContent = this.dataset.title;
//            modalDescription.textContent = this.dataset.description;
//            modalPrice.textContent = `$${parseFloat(this.dataset.price).toFixed(2)}`;
//        });
//    });
//
//    // Close modal with close button
//    closeBtn.addEventListener('click', function() {
//        modal.style.display = "none";
//    });
//
//    // Close modal when clicking outside
//    window.addEventListener('click', function(event) {
//        if (event.target === modal) {
//            modal.style.display = "none";
//        }
//    });
//
//    // Close modal with ESC key
//    document.addEventListener('keydown', function(event) {
//        if (event.key === "Escape" && modal.style.display === "block") {
//            modal.style.display = "none";
//        }
//    });
//});
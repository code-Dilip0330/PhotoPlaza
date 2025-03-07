// Slideshow functionality
let slideIndex = 0;

function showSlides() {
    let slides = document.getElementsByClassName("mySlides");
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";  // Hide all slides
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1;  // Reset to the first slide if it exceeds the number of slides
    }
    slides[slideIndex - 1].style.display = "block";  // Display the current slide
    setTimeout(showSlides, 3000);  // Change slide every 3 seconds
}

showSlides();  // Call the function to start the slideshow

// Image Modal functionality
function showModal(imgElement) {
    const modal = document.getElementById('imageModal');
    const modalImage = document.getElementById('modalImage');
    modalImage.src = imgElement.src;
    modal.style.display = 'block';
}

function closeModal() {
    const modal = document.getElementById('imageModal');
    modal.style.display = 'none';
}

// Add event listeners to all images in the gallery for modal display
window.onload = function () {
    const images = document.querySelectorAll('.category .images img');
    images.forEach((img) => {
        img.addEventListener('click', function () {
            showModal(this);
        });
    });

    const closeButton = document.querySelector('.close');
    closeButton.addEventListener('click', closeModal);

    // Close the modal when clicking outside the image
    const modal = document.getElementById('imageModal');
    modal.addEventListener('click', function (event) {
        if (event.target === modal) {
            closeModal();
        }
    });
};

// Initialize the slideshow navigation
function initializeSlideshows() {
    const slideshows = document.querySelectorAll('.slideshow');

    slideshows.forEach((slideshow) => {
        let slideIndex = 0;
        const slides = slideshow.querySelectorAll('.slide');

        function showSlide(index) {
            slides.forEach((slide, i) => {
                slide.style.display = i === index ? 'block' : 'none';
            });
        }

        function autoSlide() {
            slideIndex = (slideIndex + 1) % slides.length;
            showSlide(slideIndex);
            setTimeout(autoSlide, 3000); // Change image every 3 seconds
        }

        showSlide(slideIndex); // Initialize the first slide
        setTimeout(autoSlide, 3000); // Start automatic sliding

        slideshow.querySelector('.prev').onclick = () => {
            slideIndex = (slideIndex - 1 + slides.length) % slides.length;
            showSlide(slideIndex);
        };

        slideshow.querySelector('.next').onclick = () => {
            slideIndex = (slideIndex + 1) % slides.length;
            showSlide(slideIndex);
        };
    });
}

// Initialize slideshow
initializeSlideshows();

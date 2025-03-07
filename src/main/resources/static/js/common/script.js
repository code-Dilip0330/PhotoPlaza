document.addEventListener('DOMContentLoaded', () => {
    // Mobile Navigation Toggle
    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.nav-links');
    const navLinks = document.querySelectorAll('.nav-links li');

    burger.addEventListener('click', () => {
        // Toggle Nav
        nav.classList.toggle('nav-active');

        // Animate Links
        navLinks.forEach((link, index) => {
            if (link.style.animation) {
                link.style.animation = '';
            } else {
                link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.3}s`;
            }
        });

        // Burger Animation
        burger.classList.toggle('toggle');
    });

    // Photo Gallery
    const photoGrid = document.querySelector('.photo-grid');
    const filterButtons = document.querySelectorAll('.filter-btn');

    // Sample photo data (you can replace this with actual data from a backend)
    const photos = [
        { src: '/placeholder.svg?height=300&width=300', category: 'nature', name: 'Mountain Landscape', price: '$50', photographer: 'John Doe' },
        { src: '/placeholder.svg?height=300&width=300', category: 'portrait', name: 'Studio Portrait', price: '$75', photographer: 'Jane Smith' },
        { src: '/placeholder.svg?height=300&width=300', category: 'event', name: 'Wedding Ceremony', price: '$100', photographer: 'Mike Johnson' },
        { src: '/placeholder.svg?height=300&width=300', category: 'nature', name: 'Sunset Beach', price: '$60', photographer: 'Sarah Brown' },
        { src: '/placeholder.svg?height=300&width=300', category: 'portrait', name: 'Street Photography', price: '$80', photographer: 'Tom Wilson' },
        { src: '/placeholder.svg?height=300&width=300', category: 'event', name: 'Concert Photography', price: '$90', photographer: 'Emily Davis' },
    ];

    // Function to create photo items
    function createPhotoItem(photo) {
        const photoItem = document.createElement('div');
        photoItem.classList.add('photo-item');
        photoItem.innerHTML = `
            <img src="${photo.src}" alt="${photo.name}">
            <div class="photo-details">
                <h3>${photo.name}</h3>
                <p>Price: ${photo.price}</p>
                <p>Photographer: ${photo.photographer}</p>
            </div>
        `;
        return photoItem;
    }

    // Function to filter photos
    function filterPhotos(category) {
        photoGrid.innerHTML = '';
        const filteredPhotos = category === 'all' ? photos : photos.filter(photo => photo.category === category);
        filteredPhotos.forEach(photo => {
            photoGrid.appendChild(createPhotoItem(photo));
        });
    }

    // Initial load of all photos
    filterPhotos('all');

    // Add click event listeners to filter buttons
    filterButtons.forEach(button => {
        button.addEventListener('click', () => {
            filterButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
            filterPhotos(button.dataset.filter);
        });
    });

    // Testimonial Carousel
    const testimonialCarousel = document.querySelector('.testimonial-carousel');
    let isDown = false;
    let startX;
    let scrollLeft;

    testimonialCarousel.addEventListener('mousedown', (e) => {
        isDown = true;
        startX = e.pageX - testimonialCarousel.offsetLeft;
        scrollLeft = testimonialCarousel.scrollLeft;
    });

    testimonialCarousel.addEventListener('mouseleave', () => {
        isDown = false;
    });

    testimonialCarousel.addEventListener('mouseup', () => {
        isDown = false;
    });

    testimonialCarousel.addEventListener('mousemove', (e) => {
        if (!isDown) return;
        e.preventDefault();
        const x = e.pageX - testimonialCarousel.offsetLeft;
        const walk = (x - startX) * 2;
        testimonialCarousel.scrollLeft = scrollLeft - walk;
    });
});


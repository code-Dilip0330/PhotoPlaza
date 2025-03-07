// Tab Navigation
        function showSection(sectionId) {
            // Hide all sections
            document.querySelectorAll('.settings-section').forEach(section => {
                section.classList.remove('active');
            });

            // Show selected section
            document.getElementById(sectionId).classList.add('active');

            // Update tab buttons
            document.querySelectorAll('.tab-button').forEach(btn => {
                btn.classList.remove('active');
                if(btn.textContent.toLowerCase() === sectionId) {
                    btn.classList.add('active');
                }
            });
        }

        // Dark Mode Toggle
        function toggleDarkMode() {
            document.body.classList.toggle('dark-mode');
        }

        // Image Preview
        function previewImage(input) {
            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.querySelector('.profile-picture').src = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        // Mobile Responsive
        function handleMobileView() {
            const mobileSelect = document.querySelector('.mobile-select');
            if (window.innerWidth <= 768) {
                mobileSelect.style.display = 'block';
                document.querySelector('.settings-tabs').style.display = 'none';
            } else {
                mobileSelect.style.display = 'none';
                document.querySelector('.settings-tabs').style.display = 'flex';
            }
        }

        // Initial setup
        window.addEventListener('resize', handleMobileView);
        handleMobileView();
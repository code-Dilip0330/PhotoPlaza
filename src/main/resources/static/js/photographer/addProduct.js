document.addEventListener('DOMContentLoaded', function() {

        const form = document.getElementById('addPhotoForm');
        const imageUpload = document.getElementById('imageUpload');
        const fileInput = document.getElementById('photoUpload');
        const imagePreview = document.getElementById('imagePreview');
        const previewImg = document.getElementById('previewImg');
        const tagInput = document.getElementById('tagInput');
        const tagsContainer = document.querySelector('.tags-input');
        const hiddenTagsInput = document.getElementById('photoTags');

        // Handle image upload
        imageUpload.addEventListener('click',() =>fileInput.click());

        fileInput.addEventListener('change', function(e) {
            handleFileSelect(e.target.files[0]);
        });

        // Handle drag and drop
        ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
            imageUpload.addEventListener(eventName, preventDefaults, false);
        });

        function preventDefaults(e) {
            e.preventDefault();
            e.stopPropagation();
        }

        ['dragenter', 'dragover'].forEach(eventName => {
            imageUpload.addEventListener(eventName, highlight, false);
        });

        ['dragleave', 'drop'].forEach(eventName => {
            imageUpload.addEventListener(eventName, unhighlight, false);
        });

        function highlight(e) {
            imageUpload.style.borderColor = '#3498db';
        }

        function unhighlight(e) {
            imageUpload.style.borderColor = '#ddd';
        }

        imageUpload.addEventListener('drop', handleDrop, false);

        function handleDrop(e) {
            const dt = e.dataTransfer;
            const file = dt.files[0];
            handleFileSelect(file);
        }

        function handleFileSelect(file) {
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    previewImg.src = e.target.result;
                    imagePreview.style.display = 'block';
                }
                reader.readAsDataURL(file);
            }
        }

        // Handle tags
        tagInput.addEventListener('keydown', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                addTag(this.value.trim());
                this.value = '';
            }
        });

        function addTag(tag) {
            if (tag && !getExistingTags().includes(tag)) {
                const tagElement = document.createElement('div');
                tagElement.classList.add('tag');
                tagElement.innerHTML = `
                    <span>${tag}</span>
                    <button type="button">&times;</button>
                `;
                tagElement.querySelector('button').addEventListener('click', function() {
                    tagElement.remove();
                    updateHiddenTagsInput();
                });
                tagsContainer.insertBefore(tagElement, tagInput);
                updateHiddenTagsInput();
            }
        }

        function getExistingTags() {
            return Array.from(tagsContainer.querySelectorAll('.tag span')).map(span => span.textContent);
        }

        function updateHiddenTagsInput() {
            hiddenTagsInput.value = getExistingTags().join(',');
        }

        // Form validation and submission
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            if (validateForm()) {
                // Here you would typically send the form data to your server
                alert('Photo added successfully!');
                form.reset();
                imagePreview.style.display = 'none';
                Array.from(tagsContainer.querySelectorAll('.tag')).forEach(tag => tag.remove());
                updateHiddenTagsInput();
            }
        });

        function validateForm() {
            let isValid = true;

            // Reset error messages
            document.querySelectorAll('.error').forEach(error => error.textContent = '');

            // Validate title
            if (!form.photoTitle.value.trim()) {
                document.getElementById('photoTitleError').textContent = 'Please enter a title for your photo';
                isValid = false;
            }

            // Validate description
            if (!form.photoDescription.value.trim()) {
                document.getElementById('photoDescriptionError').textContent = 'Please provide a description for your photo';
                isValid = false;
            }

            // Validate category
            if (!form.photoCategory.value) {
                document.getElementById('photoCategoryError').textContent = 'Please select a category';
                isValid = false;
            }

            // Validate file upload
            if (!fileInput.files[0]) {
                document.getElementById('photoUploadError').textContent = 'Please upload a photo';
                isValid = false;
            }

            return isValid;
        }
    });
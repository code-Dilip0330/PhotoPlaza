document.getElementById('photoUpload').addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('previewImg').setAttribute('src', e.target.result);
                document.getElementById('imagePreview').style.display = 'block';
            }
            reader.readAsDataURL(file);
        }
    });

    document.getElementById('imageUpload').addEventListener('click', function() {
        document.getElementById('photoUpload').click();
    });

    document.getElementById('imageUpload').addEventListener('dragover', function(event) {
        event.preventDefault();
        this.style.borderColor = 'rgba(255, 255, 255, 0.5)';
    });

    document.getElementById('imageUpload').addEventListener('dragleave', function(event) {
        event.preventDefault();
        this.style.borderColor = 'rgba(255, 255, 255, 0.3)';
    });

    document.getElementById('imageUpload').addEventListener('drop', function(event) {
        event.preventDefault();
        this.style.borderColor = 'rgba(255, 255, 255, 0.3)';
        const file = event.dataTransfer.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('previewImg').setAttribute('src', e.target.result);
                document.getElementById('imagePreview').style.display = 'block';
            }
            reader.readAsDataURL(file);
        }
    });
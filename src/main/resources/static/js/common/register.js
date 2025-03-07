document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('register-form');
    const roleInputs = document.querySelectorAll('input[name="role"]');
    const photographerFields = document.getElementById('photographer-fields');

    // Toggle photographer fields based on role selection
    roleInputs.forEach(input => {
        input.addEventListener('change', () => {
            if (input.value === 'photographer') {
                photographerFields.style.display = 'block';
            } else {
                photographerFields.style.display = 'none';
            }
        });
    });

    // Form validation
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        if (validateForm()) {
            // Here you would typically send the registration data to your server
            console.log('Registration data:', getFormData());
            alert('Registration successful!'); // Replace with actual registration logic
        }
    });

    function validateForm() {
        let isValid = true;

        // Validate common fields
        isValid = validateField('fullName', 'Please enter your full name') && isValid;
        isValid = validateField('email', 'Please enter a valid email address', isValidEmail) && isValid;
        isValid = validateField('password', 'Password must be at least 8 characters long', (value) => value.length >= 8) && isValid;
        isValid = validateField('confirmPassword', 'Passwords do not match', (value) => value === document.getElementById('password').value) && isValid;
        isValid = validateField('contactNumber', 'Please enter a valid contact number', isValidPhone) && isValid;

        // Validate photographer-specific fields if the role is photographer
        if (document.querySelector('input[name="role"]:checked').value === 'photographer') {
            isValid = validateField('portfolioLink', 'Please enter a valid URL', isValidURL) && isValid;
            isValid = validateField('experienceLevel', 'Please select your experience level') && isValid;
            isValid = validateCheckboxGroup('specializations', 'Please select at least one specialization') && isValid;
        }

        return isValid;
    }

    function validateField(fieldId, errorMessage, validationFn = (value) => value.trim() !== '') {
        const field = document.getElementById(fieldId);
        const value = field.value;
        const isValid = validationFn(value);

        if (!isValid) {
            showError(field, errorMessage);
        } else {
            removeError(field);
        }

        return isValid;
    }

    function validateCheckboxGroup(name, errorMessage) {
        const checkboxes = document.querySelectorAll(`input[name="${name}"]:checked`);
        const isValid = checkboxes.length > 0;

        if (!isValid) {
            const container = document.querySelector('.checkbox-group');
            showError(container, errorMessage);
        } else {
            const container = document.querySelector('.checkbox-group');
            removeError(container);
        }

        return isValid;
    }

    function showError(element, message) {
        removeError(element);
        const errorElement = document.createElement('div');
        errorElement.className = 'error-message';
        errorElement.textContent = message;
        errorElement.style.color = 'red';
        errorElement.style.fontSize = '12px';
        errorElement.style.marginTop = '4px';
        element.parentNode.appendChild(errorElement);
        element.style.borderColor = 'red';
    }

    function removeError(element) {
        const errorElement = element.parentNode.querySelector('.error-message');
        if (errorElement) {
            errorElement.remove();
        }
        element.style.borderColor = '';
    }

    function isValidEmail(email) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    function isValidPhone(phone) {
        return /^\+?[\d\s-]{10,}$/.test(phone);
    }

    function isValidURL(url) {
        try {
            new URL(url);
            return true;
        } catch {
            return false;
        }
    }

    function getFormData() {
        const formData = new FormData(form);
        const data = Object.fromEntries(formData.entries());
        data.specializations = formData.getAll('specializations');
        return data;
    }
});


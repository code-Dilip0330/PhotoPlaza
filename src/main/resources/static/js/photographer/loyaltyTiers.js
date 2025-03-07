// Progress Ring Animation
        const progressCircle = document.querySelector('.progress-circle circle');
        const radius = progressCircle.r.baseVal.value;
        const circumference = 2 * Math.PI * radius;

        progressCircle.style.strokeDasharray = circumference;

        function setProgress(percent) {
            const offset = circumference - (percent / 100 * circumference);
            progressCircle.style.strokeDashoffset = offset;
        }

        setProgress(60);

        // Tooltip functionality
        document.querySelectorAll('.milestone').forEach(milestone => {
            milestone.addEventListener('mouseover', showTooltip);
            milestone.addEventListener('mouseout', hideTooltip);
        });

        function showTooltip(e) {
            const tooltip = document.createElement('div');
            tooltip.className = 'tooltip';
            tooltip.innerHTML = `
                <h4>${e.target.textContent} Tier</h4>
                <p>Requirements: X products, Y rating</p>
                <p>Benefits: Increased visibility, premium support</p>
            `;
            document.body.appendChild(tooltip);

            const rect = e.target.getBoundingClientRect();
            tooltip.style.position = 'absolute';
            tooltip.style.left = `${rect.left}px`;
            tooltip.style.top = `${rect.top - 100}px`;
        }

        function hideTooltip() {
            const tooltip = document.querySelector('.tooltip');
            if(tooltip) tooltip.remove();
        }

        // Dynamic stats update
        function updateStats() {
            // Add logic to update progress circles and metrics
        }
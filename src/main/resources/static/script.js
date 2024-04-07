    // Function to toggle mode
    function toggleMode() {
        const body = document.body;
        const toggleBtn = document.querySelector('.toggle-btn');
        const footerLinks = document.querySelectorAll('footer a');
        let bgColor, textColor;
        if (body.classList.contains('dark-mode')) {
            // Switch to light mode
            body.classList.remove('dark-mode');
            bgColor = '#fff';
            textColor = '#222';
            toggleBtn.textContent = '🌙'; // Display moon icon
            toggleBtn.classList.remove('dark');
            toggleBtn.classList.add('light');
            footerLinks.forEach(function(link) {
                link.style.color = '#eee';
            });
            // Store mode and colors in localStorage
            localStorage.setItem('mode', 'light');
            localStorage.setItem('bgColor', bgColor);
            localStorage.setItem('textColor', textColor);
        } else {
            // Switch to dark mode
            body.classList.add('dark-mode');
            bgColor = '#222';
            textColor = '#eee';
            toggleBtn.textContent = '🔆'; // Display sun icon
            toggleBtn.classList.remove('light');
            toggleBtn.classList.add('dark');
            footerLinks.forEach(function(link) {
                link.style.color = '#eee';
            });
            // Store mode and colors in localStorage
            localStorage.setItem('mode', 'dark');
            localStorage.setItem('bgColor', bgColor);
            localStorage.setItem('textColor', textColor);
        }
        // Apply colors
        body.style.backgroundColor = bgColor;
        document.querySelectorAll('h1, p, .title').forEach(function(element) {
            element.style.color = textColor;
        });
    }

    // Function to set mode and colors based on localStorage
    function setThemeFromStorage() {
        const mode = localStorage.getItem('mode');
        const bgColor = localStorage.getItem('bgColor');
        const textColor = localStorage.getItem('textColor');
        if (mode === 'dark' && bgColor && textColor) {
            // Apply dark mode and colors
            const body = document.body;
            body.classList.add('dark-mode');
            body.style.backgroundColor = bgColor;
            document.querySelectorAll('h1, p, .title').forEach(function(element) {
                element.style.color = textColor;
            });
        }
    }

        function validateForm() {
            var cpuWeight = parseFloat(document.getElementById('cpu-weight').value);
            var gpuWeight = parseFloat(document.getElementById('gpu-weight').value);
            var budget = parseFloat(document.getElementById('budget').value)
            var combinedWeight = cpuWeight + gpuWeight;

            // Check if the combined value is less than 1
            if (combinedWeight != 1) {
                alert('The combined value of CPU and GPU weights must equal 1.');
                return false; // Prevent form submission
            }
            if-else(budget > 0){
            return false}
            document.getElementById("buildForm").submit();
             return true;// Allow form submission
        }

    // Default mode and colors from localStorage
    setThemeFromStorage();
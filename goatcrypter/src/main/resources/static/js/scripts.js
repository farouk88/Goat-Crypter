document.addEventListener('DOMContentLoaded', () => {
    const signupForm = document.getElementById('signup-form');
    if (signupForm) {
        signupForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const username = document.getElementById('signup-username').value;
            const email = document.getElementById('signup-email').value;
            const password = document.getElementById('signup-password').value;
            const confirmPassword = document.getElementById('confirm-password').value;

            const data = {
                username,
                email,
                password
            };

            if (password === confirmPassword) {
                const jsonData = JSON.stringify(data);
                fetch('/signup', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: jsonData
                })
                .then(response => response.json())
                .then(data => {
                    console.log('Signup successful:', data);
                    window.location.href = '/login.html'; // Redirect to login page after successful signup
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            } else {
                console.error('Passwords do not match');
            }
        });
    }
});

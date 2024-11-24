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
                .then(response => {
                    if (response.ok) {
                        console.log('Signup successful');
                        window.location.href = '/login'; // Redirect to login page after successful signup
                    } else {
                        return response.text().then(errorMessage => {
                            console.error('Error:', errorMessage);
                            const urlParams = new URLSearchParams(window.location.search);
                            document.getElementById('error-message').style.display = 'block';
                        });
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            } else {
                alert("Passwords do not match");
            }
        });
    }
});

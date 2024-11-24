document.addEventListener('DOMContentLoaded', () => {
    const profileForm = document.getElementById('profile-form');

    // Fetch and display user profile data
    if (profileForm) {
        fetch('/api/profile')
            .then(response => response.json())
            .then(data => {
                document.getElementById('profile-username').value = data.username;
                document.getElementById('profile-email').value = data.email;
            })
            .catch((error) => {
                console.error('Error fetching profile:', error);
            });

        // Handle profile form submission
        profileForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const email = document.getElementById('profile-email').value;
            const password = document.getElementById('profile-password').value;
            const confirmPassword = document.getElementById('confirm-profile-password').value;

            const data = {
                email,
                password
            };
            
            if(password === confirmPassword){
                const jsonData = JSON.stringify(data);
                fetch('/api/profile/update', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: jsonData
                })
                .then(response => response.json())
                .then(data => {
                    document.getElementById('update-message').style.display = 'block';
                    setTimeout(() => {
                        document.getElementById('update-message').style.display = 'none';
                    }, 3000);
                    console.log('Profile updated successfully:', data);
                })
                .catch((error) => {
                    console.error('Error updating profile:', error);
                });
            } else{
                document.getElementById('error-message').style.display = 'block';
            }
                
        });
    }
});

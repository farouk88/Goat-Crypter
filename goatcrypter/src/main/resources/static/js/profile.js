document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/profile')
        .then(response => response.json())
        .then(data => {
            document.getElementById('profile-username').textContent = data.username;
            document.getElementById('profile-email').textContent = data.email;
        })
        .catch(error => console.error('Error:', error));

    document.getElementById('logout-button').addEventListener('click', () => {
        fetch('/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(() => {
            window.location.href = '/login.html';
        });
    });
});

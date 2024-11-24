// document.addEventListener('DOMContentLoaded', () => {
//     const goatForm = document.getElementById('goat-form');

//     if (goatForm) {
//         goatForm.addEventListener('submit', (event) => {
//             event.preventDefault();
//             const word = document.getElementById('word').value;
//             const keyword = document.getElementById('keyword').value;
//             const encryption = document.getElementById('encryption').value;

//             const data = {
//                 word,
//                 keyword,
//                 encryption,
//                 result: "" // Placeholder, backend will handle this
//             };

//             const jsonData = JSON.stringify(data);
//             fetch('/goat', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json'
//                 },
//                 body: jsonData
//             })
//             .then(response => response.json())
//             .then(data => {
//                 console.log('Success:', data);
//                 alert('Result: ' + data.result);
//                 // Optionally, redirect or update UI with the result
//             })
//             .catch((error) => {
//                 console.error('Error:', error);
//             });
//         });
//     }
// });

document.addEventListener('DOMContentLoaded', () => {
    const goatForm = document.getElementById('goat-form');
    const historyList = document.getElementById('history-list');

    if (goatForm) {
        goatForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const word = document.getElementById('word').value;
            const keyword = document.getElementById('keyword').value;
            const encryption = document.getElementById('encryption').value;

            const data = {
                word,
                keyword,
                encryption,
                result: "" // Placeholder, backend will handle this
            };

            const jsonData = JSON.stringify(data);
            fetch('/goat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: jsonData
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                alert('Result: ' + data.result);
                fetchHistory(); // Refresh history
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        });
    }

    function fetchHistory() {
        fetch('/goat/history')
        .then(response => response.json())
        .then(data => {
            historyList.innerHTML = '';
            data.forEach(item => {
                const li = document.createElement('li');
                li.textContent = `Word: ${item.word}, Keyword: ${item.keyword}, Action: ${item.encryption}, Result: ${item.result}`;
                historyList.appendChild(li);
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }

    fetchHistory(); // Fetch history on page load
});

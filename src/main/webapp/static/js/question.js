let originalAnswer = document.getElementById('answer-editor').innerHTML;
document.getElementById('answer-editor').innerHTML = '';

pell.init({
    element: document.getElementById('answer-editor'),
    onChange: html => {
        document.getElementById('html-output').value = html
    },
}).content.innerHTML = originalAnswer;

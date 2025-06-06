$('#login-button').click(function () {
    const user = {
        name: $('#fullName').val(),
        password: $('#password').val()
    };

    $.ajax({
        url: 'http://localhost:8080/EmpoyeeManagementSystem_Web_exploded/signin',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: function (response) {
            alert("Login successful!");
            localStorage.setItem('email', email);
            window.location.href = 'index.html';
        },
        error: function (xhr) {
            if (xhr.status === 401) {
                alert("Invalid password!");
            } else if (xhr.status === 404) {
                alert("User not found!");
            } else {
                alert("Server error! Check logs.");
            }
        }
    });
});

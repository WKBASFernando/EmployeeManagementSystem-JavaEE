$('#signup-button').click(function () {
    const user = {
        name:$('#name').val(),
        email: $('#email').val(),
        password: $('#password').val(),
    };
    $.ajax({
        url: 'http://localhost:8080/EmpoyeeManagementSystem_Web_exploded/signup',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: function () {
            alert('Acccount created successfully');
        },
        error: function () {
            alert("Error creating account. Please try again.");
        }
    });
});
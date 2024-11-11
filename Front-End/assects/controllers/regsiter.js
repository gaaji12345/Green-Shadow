

$("#registerForms").on("submit", function(event) {
    event.preventDefault();

    const signupData = {
        name: $("#registerName").val(),
        email: $("#registerEmail").val(),
        password: $("#registerPassword").val(),
        role: $("#userRole").val()
    };

    // Make AJAX request to backend
    // $.ajax({
    //     url: 'http://localhost:8080/api/v2/auth/signup',  // Adjust URL to match your server
    //     type: 'POST',
    //     contentType: 'application/json',
    //     data: JSON.stringify(signupData),
    //     success: function(response) {
    //         // Show success alert with SweetAlert
    //         Swal.fire({
    //             icon: 'success',
    //             title: 'Sign Up Successful!',
    //             text: 'You can now log in to your account.',
    //             confirmButtonText: 'Proceed',
    //             timer: 2000 // Optionally set a timer to auto close the alert
    //         }).then(() => {
    //             // Redirect or take further action after success
    //             // For example, you can redirect to the login page
    //             window.location.href = 'login.html';
    //         });
    //
    //         // Clear the form
    //         $("#signupForm")[0].reset();
    //     },
    //     error: function(xhr, status, error) {
    //         // Show error alert with SweetAlert
    //         Swal.fire({
    //             icon: 'error',
    //             title: 'Sign Up Failed!',
    //             text: 'Please check your details and try again.',
    //             confirmButtonText: 'Retry'
    //         });
    //     }
    // });

    $.ajax({
        url: 'http://localhost:8080/api/v2/auth/signup',
        type: 'POST',
        contentType: 'application/json',

        data: JSON.stringify(signupData),
        success: function(response) {
            Swal.fire({
                icon: 'success',
                title: 'Registration Successful',
                text: response.role
            });
            $("#registerForms")[0].reset();
            $('#login').show();
            $('#register').hide();
        },
        error: function() {
            Swal.fire({
                icon: 'error',
                title: 'Registration Failed',
                text: 'Error while registering user.'
            });
        }
    });
});

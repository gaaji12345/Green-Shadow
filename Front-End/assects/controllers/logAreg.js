

$(document).ready(function() {
    $('#loginForm').on('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        const email = $('#userNamelog').val();
        const password = $('#passwordlog').val();


        if (!email || !password) {
            showError("Email and Password are required");
            return;
        }

        login(email, password)
            .then(response => {
                if (response.role === 'MANAGER') {
                    localStorage.setItem('role', response.role);

                    $('#dashboard').show();
                    $('#login').hide();

                    Swal.fire({
                        icon: 'success',
                        title: 'Login Successful',
                        text: response.role
                    });
                }   else {
                    $('#login').hide();
                    $('.seclog').hide();
                    Swal.fire({
                        icon: 'success',
                        title: 'Login Successful',
                        text: response.name
                    });
                }
            })
            .catch(error => {
                showError(error.message);
            });

        function showError(message) {

            setTimeout(() => {

            }, 3000);
        }

        function login(email, password) {
            return new Promise((resolve, reject) => {
                $.ajax({
                    url: 'http://localhost:8080/auth/login', // Your login endpoint
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ email, password }),
                    success: function(response) {
                        resolve(response);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        reject(new Error(errorThrown || textStatus));
                    }
                });
            });
        }
    });
});





var token = localStorage.getItem('token');




$(document).ready(function() {

    fetchAllUsers();


});



$('#userSave').click(function() {


    // Create a JSON object from the form data
    var formData = $("#registrationForms2").serializeArray();
    var data = {};
    $(formData).each(function(index, obj) {
        data[obj.name] = obj.value;
    });

    console.log(data);
    console.log('Token:', token);

    $.ajax({
        url: 'http://localhost:8080/auth/register', // Make sure this matches your controller endpoint
        method: "POST",
        contentType: 'application/json', // Specify content type
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        data: JSON.stringify(data), // Convert data to JSON string
        success: function(res) {

            Swal.fire({
                icon: 'success',
                title: 'Saved Successfully',
                text: res
            });
            fetchAllUsers();
            clearInputFields();
            // clearFeilds();
        },
        error: function(ob, txtStatus, error) {
            alert(txtStatus);
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: ob.responseText // Show the error response text
            });
        }
    });
});



function fetchAllUsers() {
    console.log("Token being sent: ", token);

    $.ajax({
        url: 'http://localhost:8080/auth', // Adjust URL as needed
        type: 'GET',
        contentType: 'application/json',
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },

        success: function(response) {

            const userTableBody = $('#userTable tbody');
            userTableBody.empty(); // Clear previous entries

            response.userList.forEach(user => {
                userTableBody.append(`
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.name}</td>
                                <td>${user.phoneNumber}</td>
                                <td>${user.role}</td>
                                <td><button class="btn btn-danger btn-sm deleteBtn" data-email="${user.email}">Delete</button></td>
                            </tr>
                        `);
            });


        },
        error: function() {
            alert('Error fetching user data.');
        }
    });
    btnRowClickuser();
}

// Populate input fields when a row is clicked
function btnRowClickuser() {

    $('#userTboady').on('click', 'tr', function() {
        const email = $(this).find('td:eq(0)').text(); // Get email from the first cell
        const name = $(this).find('td:eq(1)').text(); // Get name from the second cell
        const phoneNumber = $(this).find('td:eq(2)').text(); // Get phone number from the third cell
        const role = $(this).find('td:eq(3)').text(); // Get role from the fourth cell

        // Populate input fields
        $('#emailuser').val(email);
        $('#name').val(name);
        $('#phoneNumber').val(phoneNumber);
        $('#roleuser').val(role); // Ensure the role matches the select value
    });
}

$('#deleteUser').click(function (){
    // $('#tbCustomer').empty();
    let email = $("#emailuser").val();
    let r=confirm("Are you sure you want to delete this user?");
    $.ajax({
        url:"http://localhost:8080/auth?email="+email,
        method:"DELETE",
        // data:data ,
        success:function (res){
            if(r){
                console.log(res)
                Swal.fire({
                    icon: 'success',
                    title: 'Removed Successfully',
                    text: ''
                });
                alert("Succes Fully deleted");
                fetchAllUsers();
                clearInputFields();
            }
        },
        error:function (ob,status,t){
            console.log(ob);
            console.log(status);
            console.log(t);

        }
    })
});

// Call the remove function to attach the event listener




// Initial fetch of users
function clearInputFields() {
    $('#emailuser').val('');
    $('#name').val('');
    $('#phoneNumber').val('');
    $('#password').val('');
    $('#roleuser').val('MANAGER'); // Set default role
}


$('#updateuser').click(function() {
    // Create a JSON object from the form data
    var formData = $("#registrationForms2").serializeArray();
    var data = {};
    $(formData).each(function(index, obj) {
        data[obj.name] = obj.value;
    });

    console.log(data); // For debugging
    console.log('Token:', token); // If using JWT token

    $.ajax({
        url: 'http://localhost:8080/auth/update', // Adjust URL to match your endpoint
        method: "PUT", // Specify HTTP PUT method for update
        contentType: 'application/json', // Set content type as JSON
        // headers: {
        //     'Authorization': 'Bearer ' + token // Include the JWT token for authorization
        // },
        data: JSON.stringify(data), // Convert the data object to JSON string
        success: function(res) {
            Swal.fire({
                icon: 'success',
                title: 'Updated Successfully',
                text: res.message // Adjust to match your response structure
            });
            fetchAllUsers(); // Refresh user list after update
            clearInputFields(); // Optional: Clear input fields after successful update
        },
        error: function(ob, txtStatus, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: ob.responseText // Display the error message
            });
        }
    });
});


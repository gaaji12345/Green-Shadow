getNextEcodes();
getAllEQ();
loadAllfiledCodesEq();
loadAllSCodesEq();
btnRowClickE();


function getNextEcodes(){
    $.ajax({
        url:'http://localhost:8080/auth/eq/nextEId',
        method:'GET',
        contentType: 'application/json',


        success: function(resp){
            console.log(resp);
            $('#equipmentId').val(resp)
        }
    });
}


function getAllEQ() {


    $("#equipmentTableBody").empty();
    $.ajax({
        url: "http://localhost:8080/auth/eq",
        method: "GET",
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (res) {
            console.log(res);
            for (var f of res.data) {

                let row = `<tr>

                     <th>${f.equipmentId}</th>
                        <td>${f.name}</td>
                        <td>${f.type}</td>
                        <td>${f.status}</td>
                        <td>${f.equantity}</td>
                        <td>${f.assignedStaffId}</td>
                        <td>${f.assignedFieldCode}</td>

                    </tr>`;
                $("#equipmentTableBody").append(row);

            }
        }

    });

}

function loadAllfiledCodesEq() {
    $("#assignedFieldCode").empty();
    // return new Promise(function (resolve, reject) {
    var Cus = '';
    $.ajax({
        url: "http://localhost:8080/auth/field",
        method: "GET",
        dataType: "json",//please convert the response into jason
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (resp) {

            for (const customer of resp.data) {
                $("#assignedFieldCode").empty();
                Cus += '<option value="' + customer.fieldCode + '">' + customer.fieldCode+ '</option>';

                console.log(typeof resp);
                $("#assignedFieldCode").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}

function loadAllSCodesEq() {
    $('#assignedStaffId').empty();
    // return new Promise(function (resolve, reject) {
    var Cus = '';
    $.ajax({
        url: "http://localhost:8080/auth/staff",
        method: "GET",
        dataType: "json",//please convert the response into jason
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (resp) {

            for (const customer of resp.data) {
                $("#assignedStaffId").empty();
                Cus += '<option value="' + customer.staffId + '">' + customer.staffId+ '</option>';

                console.log(typeof resp);
                $("#assignedStaffId").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}


$('#saveEq').click(function() {
    // Create a JSON object from the form data
    var formData = $("#equipmentForm").serializeArray();
    var data = {};
    $(formData).each(function(index, obj) {
        data[obj.name] = obj.value;
    });

    console.log(data);
    console.log('Token:', token);

    $.ajax({
        url: 'http://localhost:8080/auth/eq', // Make sure this matches your controller endpoint
        method: "POST",
        contentType: 'application/json', // Specify content type
        data: JSON.stringify(data), // Convert data to JSON string
        success: function(res) {


            Swal.fire({
                icon: 'success',
                title: 'Saved Successfully',
                text: res.text
            });
            getAllEQ();
            clearFieldsEq();

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

function btnRowClickE() {
    $('#eqTable').on('click', 'tr', function() {
        var headers = $(this).children('th'); // Select header cells for `staffId1`
        var cells = $(this).children('td');    // Select data cells for other fields
        $('#equipmentId').val(headers.eq(0).text()); // staffId1 from <th>
        $('#name1').val(cells.eq(0).text());  // First Name
        $('#type').val(cells.eq(1).text());   // Last Name
        $('#status1').val(cells.eq(2).text()); // Designation
        $('#quantity').val(cells.eq(3).text());      // Gender
        $('#assignedStaffId').val(cells.eq(4).text()); // Date of Joining
        $('#assignedFieldCode').val(cells.eq(5).text()); // Attached Branch


        // Optionally show the form if it’s hidden
        // $('#mainEmployee').show();
    });
}

$('#updateEq').click(function() {
    // Create a JSON object from the form data
    var formData = $("#equipmentForm").serializeArray();
    var data = {};
    $(formData).each(function(index, obj) {
        data[obj.name] = obj.value;
    });

    console.log('Data to update:', data);
    console.log('Token:', token);

    $.ajax({
        url: 'http://localhost:8080/auth/eq/update', // Update endpoint URL
        method: "PUT",
        contentType: 'application/json', // Specify content type
        data: JSON.stringify(data), // Convert data to JSON string
        success: function(res) {
            Swal.fire({
                icon: 'success',
                title: 'Updated Successfully',
                text: 'Equpment details updated successfully'
            });
           getAllEQ();
           clearFieldsEq();
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

$('#deleteEq').click(function (){
    let eCode = $("#equipmentId").val();

    // Check if fieldID is empty
    if (!eCode) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Ecode code is required to delete a field.'
        });
        return;
    }

    $.ajax({
        url: "http://localhost:8080/auth/eq?eCode="+eCode,
        method: "DELETE",
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (res) {
            console.log(res);

            Swal.fire({
                icon: 'success',
                title: 'Deleted Successfully',
                text: res.text
            });
          clearFieldsEq();
          getAllEQ();



        },
        error: function (ob, status, t) {
            console.error("Error deleting field:", status, t);

            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Eqipment to delete the field. Please try again.'
            });
        }
    });
});





function clearFieldsEq() {
    $('#equipmentId').val('');
    $('#name1').val('');
    $('#type').val('');
    $('#status1').val('');
    $('#quantity').val('');
    $('#assignedStaffId').val('');
    $('#assignedFieldCode').val('');


   getNextEcodes();
    $('#equipmentId').focus();
}





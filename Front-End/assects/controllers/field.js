var token = localStorage.getItem('token');

$(document).ready(function() {
    getAllFileds();
    getNextFiledCode();
    loadAllCropCodes();
    loadAllStaffCodes();
    btnRowClick();

});

// AJAX to load data into the table



function getNextFiledCode(){
    $.ajax({
        url:'http://localhost:8080/auth/field/generateFieldCode',
        method:'GET',
        contentType: 'application/json',
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },

        success: function(resp){
            console.log(resp);
            $('#fieldCode').val(resp)
        }
    });
}


function getAllFileds() {


    $("#fieldTableBody").empty();
    $.ajax({
        url: "http://localhost:8080/auth/field",
        method: "GET",
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (res) {
            console.log(res);
            for (var field of res.data) {
                let empPic = field.fieldImageFile ? `data:image/jpeg;base64,${field.fieldImageFile}` : 'path/to/default/image.jpg'; // Use a default image if empPic is empty

                let row = `<tr>

                     <td>${field.fieldCode}</td>
                        <td>${field.fieldName}</td>
                        <td>${field.fieldLocation}</td>
                        <td>${field.size}</td>
                        <td>${field.cropCode}</td>
                        <td>${field.nameOfCrop}</td>
                        <td>${field.staffId}</td>
                        <td><img src="${empPic}" alt="${field.nameOfCrop}'s Picture" style="width: 50px; height: 50px;"/></td>

                    </tr>`;
                $("#fieldTableBody").append(row);

            }
        }

    });

}


function loadAllCropCodes() {
    $('#cropCode').empty();
    // return new Promise(function (resolve, reject) {
    var Cus = '';
    $.ajax({
        url: "http://localhost:8080/auth/crop",
        method: "GET",
        dataType: "json",//please convert the response into jason
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (resp) {

            for (const customer of resp) {
                $("#cropCode").empty();
                Cus += '<option value="' + customer.cropCode + '">' + customer.cropCode+ '</option>';

                console.log(typeof resp);
                $("#cropCode").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}




function loadAllStaffCodes() {
    $('#staffId').empty();
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
                $("#staffId").empty();
                Cus += '<option value="' + customer.staffId + '">' + customer.staffId+ '</option>';

                console.log(typeof resp);
                $("#staffId").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}

$('#fieldForm').on('submit', function(e) {
    e.preventDefault(); // Prevent default form submission

    var formData = new FormData(this);

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/auth/field/save', // Adjust the URL as per your API
        data: formData,
        contentType: false,
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        processData: false,
        success: function(response) {
            Swal.fire({
                icon: 'success',
                title: 'Saved Successfully',
                text: response.text
            });

            getAllFileds();
            clearFields();
            btnRowClick();

            // loadAllInventory();
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'EROOR',
                text: status,xhr,error
            });
        }
    });
});


$('#deleteFiled').click(function (){
    let fieldID = $("#fieldCode").val();

    // Check if fieldID is empty
    if (!fieldID) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Field code is required to delete a field.'
        });
        return;
    }

    $.ajax({
        url: "http://localhost:8080/auth/field?fCode="+fieldID,
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
            clearFields();
            getAllFileds();

            getNextFiledCode();
        },
        error: function (ob, status, t) {
            console.error("Error deleting field:", status, t);

            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Failed to delete the field. Please try again.'
            });
        }
    });
});

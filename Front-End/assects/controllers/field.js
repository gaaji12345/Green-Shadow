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

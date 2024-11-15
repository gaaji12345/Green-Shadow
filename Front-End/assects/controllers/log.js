
loadlogCode('LOG-'); // Replace 'IIM' with your desired prefix
loadAllCropCodesLogs();
loadAllfiledCodesLogs();
loadAllStaffCodeslog();

// Function to generate and load inventory code
function loadlogCode(prefix) {
    const token = localStorage.getItem('jwtToken');
    $.ajax({
        url: `http://localhost:8080/api/v2/log/generateCode?prefix=${prefix}`,
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`  // Add the JWT token to the Authorization header
        },
        success: function (newCode) {
            console.log('Generated Inventory Code:', newCode);
            $('#logcode').val(newCode); // Populate the input field with the new code
        },
        error: function (xhr, status, error) {
            console.error('Error generating inventory code:', error);
        }
    });
}

function loadAllCropCodesLogs() {
    $('#cropCodeDesc').empty();
    const token = localStorage.getItem('jwtToken');
    // return new Promise(function (resolve, reject) {
    var Cus = '';
    $.ajax({
        url: "http://localhost:8080/api/v2/crop",
        method: "GET",
        dataType: "json",//please convert the response into jason
        headers: {
            "Authorization": `Bearer ${token}`  // Add the JWT token to the Authorization header
        },
        success: function (resp) {

            for (const customer of resp) {
                $("#cropCodeDesc").empty();
                Cus += '<option value="' + customer.cropCode + '">' + customer.cropCode+ '</option>';

                console.log(typeof resp);
                $("#cropCodeDesc").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}

function loadAllfiledCodesLogs() {
    $("#fieldCode2").empty();
    const token = localStorage.getItem('jwtToken');
    // return new Promise(function (resolve, reject) {
    var Cus = '';
    $.ajax({
        url: "http://localhost:8080/api/v2/field",
        method: "GET",
        dataType: "json",//please convert the response into jason
        headers: {
            "Authorization": `Bearer ${token}`  // Add the JWT token to the Authorization header
        },

        success: function (resp) {

            for (const customer of resp.data) {
                $("#fieldCode2").empty();
                Cus += '<option value="' + customer.fieldCode + '">' + customer.fieldCode+ '</option>';

                console.log(typeof resp);
                $("#fieldCode2").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}


function loadAllStaffCodeslog() {
    $('#staffId').empty();
    const token = localStorage.getItem('jwtToken');
    // return new Promise(function (resolve, reject) {
    var Cus = '';
    $.ajax({
        url: baseUrlStaff,
        method: "GET",
        dataType: "json",//please convert the response into jason
        headers: {
            "Authorization": `Bearer ${token}`  // Add the JWT token to the Authorization header
        },
        success: function (resp) {

            for (const customer of resp.data) {
                $("#staffCode2").empty();
                Cus += '<option value="' + customer.staffId + '">' + customer.staffId+ '</option>';

                console.log(typeof resp);
                $("#staffCode2").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}


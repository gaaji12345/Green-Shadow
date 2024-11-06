getNextEcodes();
getAllEQ();
loadAllfiledCodesEq();
loadAllSCodesEq();


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





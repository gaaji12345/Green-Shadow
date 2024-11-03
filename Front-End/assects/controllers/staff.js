getAllStaff();
loadAllfiledCodes();
loadAllVCodes();
getNextStaffCode();
btnRowClickStaff();


function getNextStaffCode(){
    $.ajax({
        url:'http://localhost:8080/auth/staff/nextId',
        method:'GET',
        contentType: 'application/json',

        success: function(resp){
            console.log(resp);
            $('#staffId1').val(resp)
        }
    });
}
function getAllStaff() {


    $("#staffTboady").empty();
    $.ajax({
        url: "http://localhost:8080/auth/staff",
        method: "GET",
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (res) {
            console.log(res);
            for (var r of res.data) {
                let row = `<tr>
                    <th>${r.staffId}</th>
                    <td>${r.firstName}</td>
                    <td>${r.lastName}</td>
                    <td>${r.designation}</td>
                    <td>${r.gender}</td>
                    <td>${r.joinedDate}</td>
                    <td>${r.dob}</td>
                    <td>${r.addressLine1}</td>
                    <td>${r.addressLine2}</td>
                    <td>${r.addressLine3}</td>
                    <td>${r.addressLine4}</td>
                    <td>${r.addressLine5}</td>
                    <td>${r.contactNo}</td>
                    <td>${r.email}</td>
                    <td>${r.members}</td>
                     <td>${r.fieldCode}</td>
                     <td>${r.vcode}</td>
                    
                    </tr>`;
                $("#staffTboady").append(row);

            }
        }

    });

}


$('#savestaffbtn').click(function() {
    // Create a JSON object from the form data
    var formData = $("#staffForm").serializeArray();
    var data = {};
    $(formData).each(function(index, obj) {
        data[obj.name] = obj.value;
    });

    console.log(data);
    console.log('Token:', token);

    $.ajax({
        url: 'http://localhost:8080/auth/staff', // Make sure this matches your controller endpoint
        method: "POST",
        contentType: 'application/json', // Specify content type
        data: JSON.stringify(data), // Convert data to JSON string
        success: function(res) {


            Swal.fire({
                icon: 'success',
                title: 'Saved Successfully',
                text: res.text
            });
           getAllStaff();
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

function loadAllfiledCodes() {
    $("#fieldCode1").empty();
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
                $("#fieldCode1").empty();
                Cus += '<option value="' + customer.fieldCode + '">' + customer.fieldCode+ '</option>';

                console.log(typeof resp);
                $("#fieldCode1").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}


$('#deletestaffbtn').click(function (){
    // $('#tbCustomer').empty();
    let staffId = $("#staffId1").val();
    $.ajax({
        url:"http://localhost:8080/auth/staff?sCode="+staffId,
        method:"DELETE",
        // data:data ,
        success:function (res) {
            console.log(res)
           getAllStaff();


            Swal.fire({
                icon: 'success',
                title: 'Delete Successfully',
                text: res
            });


        },
        error:function (ob,status,t){
            console.log(ob);
            console.log(status);
            console.log(t);

        }
    })
});

function loadAllVCodes() {
    $("#vCode").empty();
    // return new Promise(function (resolve, reject) {
    var Cus = '';
    $.ajax({
        url: "http://localhost:8080/auth/car",
        method: "GET",
        dataType: "json",//please convert the response into jason
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (resp) {

            for (const customer of resp.data) {
                $("#vCode").empty();
                Cus += '<option value="' + customer.vehicleCode + '">' + customer.vehicleCode+ '</option>';

                console.log(typeof resp);
                $("#vCode").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}

function btnRowClickStaff() {
    $('#staffTable').on('click', 'tr', function() {
        var headers = $(this).children('th'); // Select header cells for `staffId1`
        var cells = $(this).children('td');    // Select data cells for other fields
        $('#staffId1').val(headers.eq(0).text()); // staffId1 from <th>
        $('#firstName').val(cells.eq(0).text());  // First Name
        $('#lastName').val(cells.eq(1).text());   // Last Name
        $('#designation').val(cells.eq(2).text()); // Designation
        $('#gender').val(cells.eq(3).text());      // Gender
        $('#joinedDate').val(cells.eq(4).text()); // Date of Joining
        $('#dob').val(cells.eq(5).text()); // Attached Branch
        $('#addressLine1').val(cells.eq(6).text()); // Address Line 1
        $('#addressLine2').val(cells.eq(7).text()); // Address Line 2
        $('#addressLine3').val(cells.eq(8).text()); // Address Line 3
        $('#addressLine4').val(cells.eq(9).text()); // Address Line 4
        $('#addressLine5').val(cells.eq(10).text()); // Address Line 5
        $('#contactNo').val(cells.eq(11).text()); // Contact No
        $('#email1').val(cells.eq(12).text()); // Email
        $('#members').val(cells.eq(13).text()); // Emergency Contact
        $('#fieldCode1').val(cells.eq(14).text()); // Emergency Contact Person
        $('#vCode').val(cells.eq(15).text()); // Vehicle Code

        // Optionally show the form if it’s hidden
        // $('#mainEmployee').show();
    });
}





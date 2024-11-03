getAllStaff();
loadAllfiledCodes();
loadAllVCodes();
getNextStaffCode();


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

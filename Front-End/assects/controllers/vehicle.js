getAllV();
getNextVcodes();
btnRowClickV();
loadAllSCodes();

function getNextVcodes(){
    $.ajax({
        url:'http://localhost:8080/auth/car/nextVd',
        method:'GET',
        contentType: 'application/json',
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },

        success: function(resp){
            console.log(resp);
            $('#vehicleCode').val(resp)
        }
    });
}


function getAllV() {


    $("#vTbody").empty();
    $.ajax({
        url: "http://localhost:8080/auth/car",
        method: "GET",
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (res) {
            console.log(res);
            for (var f of res.data) {

                let row = `<tr>

                     <th>${f.vehicleCode}</th>
                        <td>${f.licensePlateNumber}</td>
                        <td>${f.vehicleCategory}</td>
                        <td>${f.fuelType}</td>
                        <td>${f.status}</td>
                        <td>${f.allocatedStaffId}</td>
                        <td>${f.remarks}</td>

                    </tr>`;
                $("#vTbody").append(row);

            }
        }

    });

}

function loadAllSCodes() {
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
                $("#allocatedStaffId").empty();
                Cus += '<option value="' + customer.staffId + '">' + customer.staffId+ '</option>';

                console.log(typeof resp);
                $("#allocatedStaffId").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}


$(document).ready(function() {
    // Define license plate prefixes for each vehicle category
    const licensePrefixes = {
        CAR: "CAX-1236",
        TRUCK: "TRK-4561",
        TRACTOR: "TRAC-3310",
        VAN: "VN-7898",
        SUV: "SUV-0121",
        CRANE: "CRI-210"
    };

    // When vehicle category changes, update license plate number
    $("#vehicleCategory").change(function() {
        const selectedCategory = $(this).val();
        const licensePlate = licensePrefixes[selectedCategory] || "";
        $("#licensePlateNumber").val(licensePlate);
    });
});

function btnRowClickV() {
    $('#vTable').on('click', 'tr', function() {
        var headers = $(this).children('th'); // Select header cells for `staffId1`
        var cells = $(this).children('td');    // Select data cells for other fields
        $('#vehicleCode').val(headers.eq(0).text()); // staffId1 from <th>
        $('#licensePlateNumber').val(cells.eq(0).text());  // First Name
        $('#vehicleCategory').val(cells.eq(1).text());   // Last Name
        $('#fuelType').val(cells.eq(2).text()); // Designation
        $('#status').val(cells.eq(3).text());      // Gender
        $('#allocatedStaffId').val(cells.eq(4).text()); // Date of Joining
        $('#remarks').val(cells.eq(5).text()); // Attached Branch


        // Optionally show the form if it’s hidden
        // $('#mainEmployee').show();
    });
}

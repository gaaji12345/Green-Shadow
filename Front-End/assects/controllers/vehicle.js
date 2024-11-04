getAllV();
getNextVcodes();

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

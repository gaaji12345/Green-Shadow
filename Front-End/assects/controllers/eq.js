getNextEcodes();
getAllEQ();


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

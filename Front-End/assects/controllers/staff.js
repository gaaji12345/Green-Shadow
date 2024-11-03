getAllStaff();
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
    btnRowClick();
}

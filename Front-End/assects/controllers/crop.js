

$(document).ready(function() {
    getNextCropCode();
   getAllCrops();
   loadAllfiledCodes1();

});


function loadAllfiledCodes1() {
    $("#fieldCodes").empty();
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
                $("#fieldCodes").empty();
                Cus += '<option value="' + customer.fieldCode + '">' + customer.fieldCode+ '</option>';

                console.log(typeof resp);
                $("#fieldCodes").append(Cus);
            }
            //  btnRowClick();
            //rowBack();
        }
    });

}

function getNextCropCode() {
    const token = localStorage.getItem('jwtToken');
    $.ajax({
        url: 'http://localhost:8080/api/v2/crop/cropCode',
        method: 'GET',
        dataType: 'json',  // Ensure the response is expected to be in JSON format
        headers: {
            "Authorization": `Bearer ${token}`  // Add the JWT token to the Authorization header
        },
        success: function (resp) {
            console.log(resp);  // Check the response to make sure it's the correct JSON object
            $('#cropCode1').val(resp.cropCode);  // Set the crop code to the input field
        },
        error: function (err) {
            console.error('Error fetching crop code:', err);
        }
    });
}


function getAllCrops() {
    $("#cropTableBody").empty();
    const token = localStorage.getItem('jwtToken');

    $.ajax({
        url: 'http://localhost:8080/api/v2/crop',
        method: "GET",
        dataType: "json",
        headers: {
            "Authorization": `Bearer ${token}`  // Add the JWT token to the Authorization header
        },
        success: function (res) {
            console.log(res);
            for (var crop of res) {
                // Assuming cropImage contains the image filename or path
                let cropImageUrl = crop.cropImage ? `/images/${crop.cropImage}` : '../assects/images/login.jpg';  // Fallback image if no cropImage

                // Log the final URL for debugging
                console.log(`Image URL: ${cropImageUrl}`);

                // Create the table row
                let row = `<tr>
                    <td>${crop.cropCode}</td>
                    <td>${crop.cropCommonName}</td>
                    <td>${crop.cropScientificName}</td>
                    <td><img src="${cropImageUrl}" alt="${crop.cropCommonName}" width="20" height="20"></td>
                    <td>${crop.category}</td>
                    <td>${crop.qty}</td>
                    <td>${crop.cropSeason}</td>
                    <td>${crop.fieldCodes}</td>
                    <td>${crop.filedNames}</td>
                </tr>`;
                $("#cropTableBody").append(row);
            }
        }
    });
}



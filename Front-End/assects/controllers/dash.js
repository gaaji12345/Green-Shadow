$('#dashboard').hide();

$('.register-link').click(function (){
    $('#login').hide(); // Hide login form-->
    $('#register').show();

});

$('#backButton').click(function (){
    $('#login').show();
    $('#register').hide();
});

$('#userbtn2').click(function (){
    $('#maindash').hide();
    $("#user").show();
});

$('#employeebtn').click(function (){
    $('#maindash').hide();
    $("#email").show();
});

$('#filedlink').click(function (){
    $('#maindash').hide();
    $("#fieldmain").show();
});

$('#staffbtn').click(function (){
    $('#maindash').hide();
        $("#staffmain").show();
});


$('#vBtn').click(function (){
    $('#maindash').hide();
    $("#vmain").show();
});


$('#eqlink').click(function (){
    $('#maindash').hide();
    $("#eqmian").show();
});


$('#dashboard').hide();

$('.register-link').click(function (){
    $('#login').hide(); // Hide login form-->
    $('#register').show();

});

$('#backButton').click(function (){
    $('#login').show();
    $('#register').hide();
});

$(document).ready(function () {
    $('H1').addClass('text-center');
    $('H2').addClass('text-center');
    $('.myBannerHeading').addClass('page-header');
    $('.myBannerHeading').removeClass('myBannerHeading');
    $('#yellowHeading').append('H2').text('Yellow Team');
    $('#yellowHeading').css('background-color', 'yellow');
    $('#redHeading').css('background-color', 'red');
    $('#orangeHeading').css('background-color', 'orange');
    $('#blueHeading').css('background-color', 'blue');
    $('#yellowTeamList').append('<li>Joseph Banks</li>');
    $('#yellowTeamList').append('<li>Simon Jones</li>');
    $('#oops').hide();
    $('#footerPlaceholder').remove();
    $('footer').append('p').text('Jake Curley jacobtcurley@gmail.com');
    $('footer').css('font', '24px Courier');
});

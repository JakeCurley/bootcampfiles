$(document).ready(function () {
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
    $('#akronButton').on('click', function () {
        $('#mainInfoDiv').hide();
        $('#minneapolisInfoDiv').hide();
        $('#louisvilleInfoDiv').hide();
        $('#akronWeather').hide();
        $('#akronInfoDiv').toggle('slow');
    })
    $('#minneapolisButton').on('click', function () {
        $('#mainInfoDiv').hide();
        $('#akronInfoDiv').hide();
        $('#louisvilleInfoDiv').hide();
        $('#minneapolisWeather').hide();
        $('#minneapolisInfoDiv').toggle('slow');
    })
    $('#louisvilleButton').on('click', function () {
        $('#mainInfoDiv').hide();
        $('#akronInfoDiv').hide();
        $('#minneapolisInfoDiv').hide();
        $('#louisvilleWeather').hide();
        $('#louisvilleInfoDiv').toggle('slow');
    })
    $('#akronWeatherButton').on('click', function() {
        $('#akronWeather').toggle('slow');
    })
    $('#minneapolisWeatherButton').on('click', function() {
        $('#minneapolisWeather').toggle('slow');
    })
    $('#louisvilleWeatherButton').on('click', function() {
        $('#louisvilleWeather').toggle('slow');
    })
    $('tr').not('th').hover(
      function () {
        $(this).css('background-color', 'WhiteSmoke');
        $('th').css('background-color', 'AliceBlue');
      },
      function () {
        $(this).css('background-color', 'white');
        $('th').css('background-color', 'AliceBlue');
      }
    )
});

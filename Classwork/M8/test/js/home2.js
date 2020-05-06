$(document).ready(function(){

  $('#toggleNumbers').on('click', function () {
    $('H2').toggle('slow');
  });

  $('#centerUp').on('click', function () {
    $('H1').addClass('text-center');
    $('H2').addClass('text-center');
    $('#buttonDiv').addClass('text-center');
  });

  $('#headingsBlue').on('click', function() {
    $('H1').css('color', 'blue');
  });

  $('div').hover(
    function () {
      $(this).css('background-color', 'cornflowerblue');
    },
    function () {
      $(this).css('background-color', '');
    }
  );

  $('H2').hover(
    function () {
      $(this).css('color', 'darkorange');
    },
    function () {
      $(this).css('color', '');
    }
  );

  $('#mainHeading').hover(
    function () {
      $(this).css('color', 'red');
    },
    function () {
      $(this).css('color', 'pink');
    }
  )

});

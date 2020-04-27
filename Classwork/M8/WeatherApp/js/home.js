$(document).ready(function() {
  $('#weather-button').click(function(event) {
    clearWeather();
    getCurrentConditions();
    getFiveDayForecast();
    displayWeather();
  });
});

function getFiveDayForecast() {
  var zipcode = $('#add-zipcode').val();
  $.ajax({
    type: "GET",
    url: "http://api.openweathermap.org/data/2.5/forecast?zip="+zipcode+"&appid=34b5195b5bf5feca1f4616aa55dcb164 ",
    success: function(weather) {
      var weatherArray = weather.list;
      for (var i=0; i<40;i++){
        var d = weatherArray[i].dt;
        var newDate = new Date(d*1000);
        var intmonth = newDate.getMonth(date);
        var month = toMonth(intmonth);
        var day = newDate.getDay(date);
        var date = day + " " + month;
        var description = weatherArray[i].weather[0].description;
        var icon = weatherArray[i].weather[0].icon;
        var address = 'http://openweathermap.org/img/w/' + icon +'.png'
        var minTemp = weatherArray[i].main.temp_min;
        var maxTemp = weatherArray[i].main.temp_max;

        var row = '<div class="row"><p>'+date+'</p></div>';
            row+= '<div class="row"><img src='+address+'/><div class="col-sm-1">'+description+'</div></div>'
            row+= '<div class="row"><div class="col-sm-1">'+maxTemp+'</div><div class="col-sm-1">'+minTemp+'</div></div>'

            $('#day-'+i).append(row);
            i=i+8;
      }
    },
    error: function() {
      $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service.. Please try again later.'))
    }
  });
}
function getCurrentConditions() {
  var zipcode = $('#add-zipcode').val();
  $.ajax({
    type: "GET",
    url: "http://api.openweathermap.org/data/2.5/weather?zip="+zipcode+",us&APPID=34b5195b5bf5feca1f4616aa55dcb164",
    success: function(newWeather) {
        var name = newWeather.name;
        var main = newWeather.main;
        var temp = main.temp;
        var humidity = main.humidity;
        var wind = newWeather.wind.speed;
        var weatherDesc = newWeather.weather[0];
        var description = weatherDesc.description;
        var image = weatherDesc.icon;
        var address = 'http://openweathermap.org/img/w/' + image+'.png';

        $('#currentCity').append('<h2>Current Conditions for ' +name+ '</h2>');
        $('#weatherDescription').append(description);
        $('#icon').append('<img src="'+address+'"/>');
        $('#temperature').append(temp);
        $('#humidity').append(humidity);
        $('#wind').append(wind);
    },
    error: function() {
      $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service.. Please try again later.'))
    }
  });
}
function displayWeather() {
  $('#currentConditionsDiv').show();
  $('#fiveDayConditions').show();
}

function clearWeather() {
  $('#currentCity').text('');
  $('#weatherDescription').text('');
  $('#icon').text('');
  $('#temperature').text('');
  $('#humidity').text('');
  $('#wind').text('');
}

function toMonth(intmonth) {
  switch (intmonth) {
    case 0:
      month = 'January';
      break;
    case 1:
      month = 'February';
      break;
    case 2:
      month = 'March';
      break;
    case 3:
      month = 'April';
      break;
    case 4:
      month = 'May';
      break;
    case 5:
      month = 'June';
      break;
    case 6:
      month = 'July';
      break;
    case 7:
      month = 'August';
      break;
    case 8:
      month = 'September';
      break;
    case 9:
      month = 'October';
      break;
    case 10:
      month = 'November';
      break;
    case 11:
      month = 'Decemeber';
      break;
  }
  return month;
}

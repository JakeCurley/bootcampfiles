$(document).ready(function(){
  $('#search-button').click(function(event) {
    findMovie();
  });
  $("#movieSearchBox").autocomplete({
    autoFocus: true,
    delay: 700,
    appendTo: $('#resultsBox'),
    source: function(request, response) {
      suggestions = [];
      console.log(request.term);
      $.getJSON('https://www.omdbapi.com/?type=movie&s=' + request.term + '&apikey=be2d5c34', function(data, textStatus) {
          for (var i = 0; i < data.Search.length; i++) {
              suggestions.push(data.Search[i]);
          }
          console.log(suggestions);
          response(suggestions);
      })
    },
  }).autocomplete().data("uiAutocomplete")._renderItem = function(ul, item) {
          return $('<li>')
          .append(
              '<a id="link"><div class="row" id="test"><div class="col-sm-3"><img src="'+item.Poster+'"id="image"></div><div class="col-sm-9"><h3 id="movieTitle">'+item.Title+'('+item.Year+')</h3></div></div><div style="display:none" id="imdbID">'+item.imdbID+'</div></a>'
          )
          .appendTo(ul);
 };

  $(document).on('click', '#link', function(event) {
    var realId = $(this).find('#imdbID').html();
    findMovie(realId);
  });

});

function findMovie(realId) {
  $('#searchDiv').hide();
  $('#searchResult').show();
  var contentRows = $('#searchResult');

  $.ajax({
    type: "GET",
    url: "http://www.omdbapi.com/?i="+realId+"&apikey=be2d5c34",
    success: function(movie) {
        var poster = movie.Poster;
        var title = movie.Title;
        var rating = movie.Rated;
        var releaseDate = movie.Released;
        var runtime = movie.Runtime;
        var genre = movie.Genre;
        var director = movie.Director;

        var row = '<div class="col-sm-offset-2 col-sm-12" style="border: 2px solid white">';
            row += '<div class="row movie"><img src="'+poster+'" /></div>'
            row += '<div class="row movie"><p>Title: </p>'+title+'</div>'
            row += '<div class="row movie"><p>Release Date: </p>'+releaseDate+'</div>'
            row += '<div class="row movie"><p>Rating: </p>'+rating+'</div>'
            row += '<div class="row movie"><p>Run time: </p>'+runtime+'</div>'
            row += '<div class="row movie"><p>Genre: </p>'+genre+'</div>'
            row += '<div class="row movie"><p>Director: </p>'+director+'</div>'
            row += '</div>'
        contentRows.append(row);
    },
    error: function() {
      $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service.. Please try again later.'))
    }
  });
}

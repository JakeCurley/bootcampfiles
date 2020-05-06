$(document).ready(function () {
  +$("#search-button").click(function (event) {
    findMovie();
  });

  $("#stars").emojiRating(
    (options = {
      emoji: "U+2B50",
      count: 10,
      fontSize: 25,
      inputName: "rating",
      onUpdate: function (rating) {
        alert(rating);
      },
    })
  );

  $("select.hiddenMovieList").change(function () {
    var selectedList = $(this).children("option:selected").val();

    alert("You have selected the country - " + selectedList);
  });

  $("#movieSearchBox")
    .autocomplete({
      autoFocus: true,
      delay: 700,
      appendTo: $("#resultsBox"),
      source: function (request, response) {
        suggestions = [];
        console.log(request.term);
        $.getJSON(
          "https://www.omdbapi.com/?type=movie&s=" +
            request.term +
            "&apikey=be2d5c34",
          function (data, textStatus) {
            for (var i = 0; i < data.Search.length; i++) {
              suggestions.push(data.Search[i]);
            }
            console.log(suggestions);
            response(suggestions);
          }
        );
      },
    })
    .autocomplete()
    .data("uiAutocomplete")._renderItem = function (ul, item) {
    var result = '<a id="link">';
    result += '<div class="row" id="test">';
    result += '<div class="col-sm-3">';
    result += '<img src="' + item.Poster + '"id="image">';
    result += "</div>";
    result += '<div class="col-sm-9">';
    result += '<h4 id="movieTitle">' + item.Title + "(" + item.Year + ")</h3>";
    result += "</div></div>";
    result += '<div style="display:none" id="imdbID">' + item.imdbID + "</div>";
    result += "</a>";

    return $("<li>").append(result).appendTo(ul);
  };

  $(document).on("click", "#link", function (event) {
    var realId = $(this).find("#imdbID").html();
    findMovie(realId);
    displayMovie();
  });

  $("#createListButton").click(function (event) {
    var listName = $("#createNewList").val();
    createNewList(listName);
    rateAndAddToList();
  });

  $("#addMovieButton").click(function (event) {
    addMovieToList();
  });
});

function displayMovie() {
  getMovieLists();
  $("#searchDiv").hide();
  $("#listDiv").show();
  $("#searchResult").show();
}

function rateAndAddToList() {
  getMovieLists();
  $("#searchDiv").hide();
  $("#searchResult").show();
  $("#listDiv").hide();
  $("#movieRating").show();
}

function findMovie(realId) {
  var contentRows = $("#searchResult");

  $.ajax({
    type: "GET",
    url: "http://www.omdbapi.com/?i=" + realId + "&apikey=be2d5c34",
    success: function (movie) {
      var test = JSON.stringify(movie);
      var imdbID = movie.imdbID;
      var poster = movie.Poster;
      var title = movie.Title;
      var rating = movie.Rated;
      var releaseDate = movie.Released;
      var runtime = movie.Runtime;
      var genre = movie.Genre;
      var director = movie.Director;
      var plot = movie.Plot;
      var actor = movie.Actors;
      var row =
        '<div class="col-sm-offset-2 col-sm-8" style="border: 4px solid #f2ac16; padding: 3vw;">';
      row += '<div class="row">';
      row +=
        '<div class="col-5"><div class="row movie text-center"><img src="' +
        poster +
        '"/></div></div>';
      row +=
        '<div class="row movie" style="display:none" id="posterUrl">' +
        poster +
        "</div>";
      row +=
        '<div class="col-7"><div class="row movie"><p class="infoTitles">Title: </p><br><p id="title">' +
        title +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Plot: </p><p id="plot">' +
        plot +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Release Date: </p><p id="releaseDate">' +
        releaseDate +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Rating: </p><p id="rating">' +
        rating +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Run time: </p><p id="runtime">' +
        runtime +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Genre: </p><p id="genre">' +
        genre +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Director: </p><p id="director">' +
        director +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Actors: </p><p id="actor">' +
        actor +
        "</p></div>";
      row += '<div style="display:none" id="imdbID">' + imdbID + "</div>";
      row += "</div></div></div>";
      contentRows.append(row);
    },
    error: function () {
      $("#errorMessages").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("Error calling web service.. Please try again later.")
      );
    },
  });
}

function addMovieToList(movie) {
  var list = $("select.hiddenMovieList").val();
  var test = $("#imdbID").html();
  var test2 = $("#posterUrl").html();
  var posterUrl = encodeURIComponent(test2);
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/addMovie",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      listName: list,
      imdbID: $("#imdbID").html(),
      poster: posterUrl,
      title: $("#title").html(),
      releaseDate: $("#releaseDate").html(),
      rating: $("#rating").html(),
      runTime: $("#runtime").html(),
      director: $("#director").html(),
      userScore: 1,
      plot: $("#plot").html(),
      actors: $("#actor").html(),
      genres: $("#genre").html(),
    }),
    success: function (message) {},
    error: function (xhr, status, error) {
      alert("Failed");
      /*var message = xhr.responseText;
      var newMessage = message.substring(12, message.length-2);
      $('#enter-messages').html(newMessage);*/
    },
  });
}

function getMovieLists() {
  var contentRows = $(".hiddenMovieList");
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/getMovieList",
    success: function (movieListArray) {
      $.each(movieListArray, function (index, movie) {
        var listID = movie.listID;
        var listName = movie.listName;

        var row = '<option value="' + listName + '">' + listName + "</option>";

        contentRows.prepend(row);
      });
    },
    error: function () {
      $("#errorMessages").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("Error calling web service.. Please try again later.")
      );
    },
  });
}

function createNewList(name) {
  var contentRows = $(".hiddenMovieList");
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/addList",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      listName: name,
    }),
    success: function (message) {
      var row = '<option value="' + name + '">' + name + "</option>";
      contentRows.prepend(row);
    },
    error: function (xhr, status, error) {
      alert("Failed");
      /*var message = xhr.responseText;
      var newMessage = message.substring(12, message.length-2);
      $('#enter-messages').html(newMessage);*/
    },
  });
}

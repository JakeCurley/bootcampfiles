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

  $("#movieSearchBox").autocomplete({
    source: function (request, response) {
      $.ajax({
        url:
          "https://api.themoviedb.org/3/search/movie?api_key=1d86b5ada9e966c6efb7b12938ffccf4&query=" +
          request.term,
        dataType: "jsonp",
        data: {
          term: request.term,
        },
        success: function (data) {
          $.each(data.results, function (index, movie) {
            var title = movie.title;
            var releaseDate = movie.release_date;
            var date = releaseDate.substring(0, 4);
            var url = "https://image.tmdb.org/t/p/w92/" + movie.poster_path;
            var id = movie.id;

            var result = '<div class="col-12"><a id="link">';
            result += '<div class="row" id="test">';
            result += '<div class="col-sm-3">';
            result += '<img src="' + url + '"id="image">';
            result += "</div>";
            result += '<div class="col-sm-9 text-left my-auto">';
            result += '<h4 id="movieTitle">' + title + "(" + date + ")</h3>";
            result += "</div></div>";
            result += '<div style="display:none" id="imdbID">' + id + "</div>";
            result += "</a></div>";

            $("#resultsBox").append(result);
          });
        },
      });
    },
    minLength: 2,
  });

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
    url:
      "https://api.themoviedb.org/3/movie/" +
      realId +
      "?api_key=1d86b5ada9e966c6efb7b12938ffccf4&append_to_response=credits",
    success: function (movie) {
      var test = JSON.stringify(movie);
      var imdbID = movie.imdb_id;
      var poster = "https://image.tmdb.org/t/p/w342/" + movie.poster_path;
      var title = movie.title;
      var releaseDate = movie.release_date;
      var rating = null;
      var runtime = movie.runtime;
      var genres = [];
      movie.genres.forEach(function (entry) {
        genres.push(entry.name);
      });
      var directors = [];
      movie.credits.crew.forEach(function (entry) {
        if (entry.job === "Director") {
          directors.push(entry.name);
        }
      });
      var plot = movie.overview;
      var actors = [];
      movie.credits.cast.forEach(function (entry) {
        actors.push(entry.name);
      });
      var test = actors.slice(0, 8);
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
        " minutes</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Genre: </p><p id="genre">' +
        genres.toString() +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Director: </p><p id="director">' +
        directors.toString() +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Actors: </p><p id="actor">' +
        test.toString() +
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
  var test3 = JSON.stringify($("#actor").html());
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

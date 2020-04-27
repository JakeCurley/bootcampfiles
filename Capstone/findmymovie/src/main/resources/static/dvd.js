$(document).ready(function () {
  $("#registerButton").click(function (event) {
    var newUserName = $("#newUserName").val();
    var newPassword = $("#newPassword").val();
    register(newUserName, newPassword);
  });

  $("#loginButton").click(function (event) {
    var userName = $("#userName").val();
    var password = $("#password").val();
    login(userName, password);
  });

  $("#genreStats").click(function (event) {
    $("#myCharts").show();
    if ($("#genreChartDiv").is(":hidden")) {
      $("#genreChartDiv").toggle();
      getAllGenres();
      return;
    } else {
      getAllGenres();
    }
  });

  $("#actorStats").click(function (event) {
    $("#myCharts").show();
    if ($("#genreChartDiv").is(":hidden")) {
      $("#genreChartDiv").toggle();
      getAllActors();
      return;
    } else {
      getAllActors();
    }
  });

  $("#scoreComparison").click(function (event) {
    $("#myCharts").show();
    if ($("#genreChartDiv").is(":hidden")) {
      $("#genreChartDiv").toggle();
      scoreComparisonChart();
      return;
    } else {
      scoreComparisonChart();
    }
  });

  $("#budgetScoreComparison").click(function (event) {
    $("#myCharts").show();
    if ($("#genreChartDiv").is(":hidden")) {
      $("#genreChartDiv").toggle();
      budgetScoreChart();
      return;
    } else {
      budgetScoreChart();
    }
  });

  $("#movieLengthComparison").click(function (event) {
    $("#myCharts").show();
    if ($("#genreChartDiv").is(":hidden")) {
      $("#genreChartDiv").toggle();
      movieLengthChart();
      return;
    } else {
      movieLengthChart();
    }
  });

  $("#totalViewTime").click(function (event) {
    $("#myCharts").show();
    if ($("#genreChartDiv").is(":hidden")) {
      $("#genreChartDiv").toggle();
      totalRuntimeChart();
      return;
    } else {
      totalRuntimeChart();
    }
  });

  //Star rating for add and update movie
  $(".stars").emojiRating(
    (options = {
      emoji: "U+2B50",
      count: 10,
      fontSize: 25,
      inputName: "rating",
      onUpdate: function (rating) {},
    })
  );

  /*Autocomplete for search*/
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
            $("#resultsBox").show();
            var title = movie.title;
            var releaseDate = movie.release_date;
            var date = releaseDate.substring(0, 4);
            var url = "https://image.tmdb.org/t/p/w92/" + movie.poster_path;
            var id = movie.id;

            var result = '<div class="col-12"><a class="link">';
            result += '<div class="row test">';
            result += '<div class="col-sm-3">';
            result += '<img src="' + url + '"class="image">';
            result += "</div>";
            result += '<div class="col-sm-9 text-left my-auto">';
            result += '<h4 class="movieTitle">' + title + "(" + date + ")</h3>";
            result += "</div></div>";
            result +=
              '<div style="display:none" class="imdbID">' + id + "</div>";
            result += "</a></div>";

            $("#resultsBox").append(result);
          });
        },
      });
    },
    minLength: 3,
  });

  /*Empty search box input by clicking on it*/

  $("#movieSearchBox").click(function (event) {
    $(this).val("");
    $("#resultsBox").empty();
    $("#resultsBox").css("display", "none");
  });

  /*Links for search results*/
  $(document).on("click", ".link", function (event) {
    var realId = $(this).find(".imdbID").html();
    findMovie(realId);
  });

  /*Links for My Lists*/
  $(document).on("click", ".listLink", function (event) {
    var listID = $(this).find(".listID").html();
    var listName = $(this).find(".listName").html();
    $("#selectedList").empty();
    getCompleteList(listID, listName);
  });

  /*Links for movies in a list*/
  $(document).on("click", "#myMovieLink", function (event) {
    var movieID = $(this).find(".myMovieID").html();
    var listID = $(this).find(".listIDFromMovie").html();
    $("#searchResult").empty();
    findMyMovie(movieID, listID);
  });

  /*Close button for movie from TMDB API*/
  $(document).on("click", "#closeButton", function (event) {
    clearErrorsAndSuccesses();
    displayHomePage();
  });

  /*Close list button*/
  $(document).on("click", "#closeListButton", function (event) {
    displayHomePage();
  });

  /*Close button for MyMovie return to HomePage*/
  $(document).on("click", ".closeMyMovieButton", function (event) {
    clearErrorsAndSuccesses();
    displayHomePage();
  });

  /*Close stats button*/
  $("#closeStatsButton").click(function (event) {
    displayHomePage();
  });

  /*Send updated score to database*/
  $(document).on("click", "#updateScoreButton", function (event) {
    var movieID = $(".removeMovieButton").find(".movieIDToRemove").html();
    var listID = $("#myMovieLink").find(".listIDFromMovie").html();
    updateUserScore(movieID, listID);
  });

  //Create a list and save to database
  $("#createListButton").click(function (event) {
    $("#errorSelectedList").hide();
    $("#newListSuccess").show();
    var listName = $("#createNewList").val();
    if (listName === "") {
      $("#errorBlankNewList").show();
      return;
    }
    $("#errorBlankNewList").hide();
    createNewList(listName);
  });

  /*Select list to add movie to -> goes to rate*/
  $("#selectList").click(function (event) {
    clearErrorsAndSuccesses();
    var list = $("select.hiddenMovieList").val();

    if ($("select.hiddenMovieList").val() === null) {
      $("#errorSelectedList").show();
      return;
    }
    displayRateMovie();
  });

  /*Add movie to database*/
  $("#addMovieButton").click(function (event) {
    $(".jqEmoji-container").children().css("opacity", ".2");
    addMovieToList();
  });

  /*Delete list button - Displays delete warning screen*/
  $(document).on("click", ".deleteList", function (event) {
    var listID = $(this).find(".listIDFromMovie").html();
    $("#listToBeDeleted").append(listID);
    displayDeleteWarningScreen();
  });

  /*Confirm Delete Button*/
  $(document).on("click", "#confirmDeleteButton", function (event) {
    var listIDToRemove = "";
    listIDToRemove = $(this).find("#listToBeDeleted").html();
    deleteList(listIDToRemove);
  });

  /*Delete movie button*/
  $(document).on("click", ".removeMovieButton", function (event) {
    var movieID = $(this).find(".movieIDToRemove").html();
    deleteMovie(movieID);
  });
});

var chart;
var genreNamesX = [];
var genreCountY = [];
var actorNamesX = [];
var actorCountY = [];

/*Clear errors*/
function clearErrorsAndSuccesses() {
  $("#createNewList").val("");
  $("#errorSelectedList").hide();
  $("#newListSuccess").hide();
  $("#errorListName").hide();
  $("#errorBlankNewList").hide();
  $("#duplicateMovie").hide();
}

/*Register new account*/
function register(newUserName, newPassword) {
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/Register",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      userName: newUserName,
      password: newPassword,
    }),
    success: function (message) {
      $("#loginScreen").hide();
      displayHomePage();
    },
    error: function (xhr, status, error) {
      var message = xhr.status;
      if (xhr.status === 406) {
        var message =
          '<h5 style="color: white; margin-left: 1vw;">That name is in use, please select another.</h5>';
        console.log(message);
        $("#duplicateUserName").text("");
        $("#duplicateUserName").append(message);
      }
    },
  });
}

/*Login*/
function login(userName, password) {
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/Login",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      userName: userName,
      password: password,
    }),
    success: function (message) {
      $("#loginScreen").hide();
      displayHomePage();
    },
    error: function (xhr, status, error) {
      var message = xhr.status;
      if (xhr.status === 406) {
        var message =
          '<h5 style="color: white; margin-left: 1vw;">Invalid login information.  Please try again.</h5>';
        console.log(message);
        $("#invalidLogin").text("");
        $("#invalidLogin").append(message);
      }
    },
  });
}

/*Returns movie details from TMDB API*/
function findMovie(realId) {
  var contentRows = $("#searchResult");
  $("#searchResult").empty();
  $.ajax({
    type: "GET",
    url:
      "https://api.themoviedb.org/3/movie/" +
      realId +
      "?api_key=1d86b5ada9e966c6efb7b12938ffccf4&append_to_response=credits",
    success: function (movie) {
      var test = JSON.stringify(movie);
      var imdbID = movie.imdb_id;
      var posterPath = movie.poster_path;
      if (posterPath != null) {
        var workingPoster = posterPath.substring(1);
      } else {
        workingPoster == null;
      }
      var poster = "https://image.tmdb.org/t/p/w342/" + movie.poster_path;
      var title = movie.title;
      var releaseDate = movie.release_date;
      var rating = null;
      var runtime = movie.runtime;
      var popularity = movie.vote_average;
      var budget = movie.budget;
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
        workingPoster +
        "</div>";
      row +=
        '<div class="col-7"><div class="row justify-content-end" style="margin: 0px"><a id="closeButton"><span id="closeMovieSelection" style="font-size: 2vw; font-family: Times New Roman; color: #f2ac16;">&#10006;</span></a></div><div class="row movie"><p class="infoTitles">Title: </p><br><p id="title">' +
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
        "</p><p> minutes</p></div>";
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
      row += '<div style="display:none;" id="budget">' + budget + "</div>";
      row +=
        '<div style="display:none" id="popularity">' + popularity + "</div>";
      row += '<div style="display:none" class="imdbID">' + imdbID + "</div>";
      row += "</div></div></div>";
      contentRows.append(row);
      displayMovie();
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

/*Returns movie details my LocalHost*/
function findMyMovie(movieID, listID) {
  var contentRows = $("#searchResult");
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/getMyMovie",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      movieID: movieID,
    }),
    success: function (movie) {
      var actors = movie.actorList;
      var genres = movie.genreList;
      console.log(actors[1].actorName);
      var movieID = movie.movie.movieID;
      var imdbID = movie.movie.imdbID;
      var poster = movie.movie.poster;
      var workingPoster = "https://image.tmdb.org/t/p/w342/" + poster;
      var title = movie.movie.title;
      var userScore = movie.movie.userScore;
      var releaseDate = movie.movie.releaseDate;
      var runTime = movie.movie.runTime;
      var plot = movie.movie.plot;
      var rating = null;

      var actorString = "";
      for (i = 0; i < actors.length; i++) {
        actorString += actors[i].actorName;
        if (i == actors.length - 1) {
          break;
        }
        actorString += ", ";
      }
      console.log(actorString);

      var genreString = "";
      for (i = 0; i < genres.length; i++) {
        genreString += genres[i].genreName;
        if (i == genres.length - 1) {
          break;
        }
        genreString += ", ";
      }
      console.log(genreString);

      var row =
        '<div class="col-sm-offset-2 col-sm-8" style="border: 4px solid #f2ac16; padding: 3vw; margin-top: 2vw;">';
      row += '<div class="row">';
      row +=
        '<div class="col-5"><div class="row movie text-center"><img src="' +
        workingPoster +
        '"/></div></div>';
      row +=
        '<div class="row movie" style="display:none" id="posterUrl">' +
        workingPoster +
        "</div>";
      row +=
        '<div class="col-7"><div class="row justify-content-end" style="margin: 0px"><a class="closeMyMovieButton"><span id="closeMovieSelection" style="font-size: 2vw; font-family: Times New Roman; color: #f2ac16;">&#10006;</span></a></div><div class="row movie"><p class="infoTitles">Title: </p><br><p id="title">' +
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
        runTime +
        " minutes</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Genre: </p><p id="genre">' +
        genreString +
        "</p></div>";
      row +=
        '<div class="row movie"><p class="infoTitles">Actors: </p><p id="actor">' +
        actorString +
        "</p></div>";
      row += '<div class="row"><p class="infoTitles">My Rating: </p>';
      row +=
        '<span class="ml-auto star-rating star' + userScore + '"></span></div>';
      row +=
        '<button class="btn btn-dark removeMovieButton">Remove from list<div style="display:none" class="movieIDToRemove">' +
        movieID +
        "</div></button>";
      row += '<div style="display:none" class="imdbID">' + imdbID + "</div>";
      row += '<div style="display:none" class="listid">' + listID + "</div>";
      row += "</div></div></div>";

      contentRows.append(row);
      displayMyMovie();
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

/*Add movie to a list*/
function addMovieToList(movie) {
  var test = $("#budget").html();
  $("#duplicateMovie").empty();
  var list = $("#selectCurrentList option:selected").val();
  var userscore = $("input[name=rating]").val();
  var test2 = $("#posterUrl").html();
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/addMovie",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      listName: list,
      imdbID: $(".imdbID").html(),
      poster: $("#posterUrl").html(),
      title: $("#title").html(),
      releaseDate: $("#releaseDate").html(),
      rating: $("#rating").html(),
      runTime: $("#runtime").html(),
      director: $("#director").html(),
      userScore: userscore,
      plot: $("#plot").html(),
      actors: $("#actor").html(),
      genres: $("#genre").html(),
      popularity: $("#popularity").html(),
      budget: $("#budget").html(),
    }),
    success: function (message) {
      $("#duplicateMovie").hide();
      displayHomePage();
    },
    error: function (xhr, status, error) {
      var message = xhr.status;
      if (xhr.status === 406) {
        var message =
          '<h5 style="color: white;">That movie already exists in that list!</h5>';
        $("#duplicateMovie").append(message);
        $("#duplicateMovie").show();
        $("#listDiv").show();
        $("#searchResult").show();
        $("#movieRating").hide();
      }
    },
  });
}

/*Get's list names for select boxes*/
function getMovieListsForSelection() {
  $(".hiddenMovieList").empty();
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

/*Create new list*/
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
      var msg = '<h5 style="color: white">Your new list has been added!</h5>';
      $("#selectCurrentList").val(name).prop("selected", true);
      $("#newListSuccess").text("");
      $("#newListSuccess").append(msg);
    },
    error: function (xhr, status, error) {
      var message = xhr.status;
      if (xhr.status === 406) {
        var message =
          '<h5 style="color: white; margin-left: 1vw;">You already have a list with that name.</h5>';
        console.log(message);
        $("#errorListName").text("");
        $("#errorListName").append(message);
        $("#errorListName").show();
        $("#newListSuccess").hide();
        $("#selectCurrentList").val(listName).change();
      }
    },
  });
}

/*Returns data to display a list*/
function getCompleteList(listID, listName) {
  var contentRows = $("#selectedList");
  $("#myListContainer").empty();
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/getCompleteList",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      listID: listID,
      listName: listName,
    }),
    success: function (movieList) {
      var listNamef = movieList.listName;
      var movies = movieList.movies;
      var row =
        '<div class="col-offset-1 col-10" margin-bottom: 2vw; width: 100%;"><a id="closeListButton"><div class="row justify-content-end"><span id="closeListSelection" style="font-size: 2vw; font-family: Times New Roman; color: #f2ac16;">&#10006;</span></div></a>';
      row +=
        '<div class="row justify-content-center" style="background-color: #f2ac16; width: 100%;"><div class="col-10">' +
        movieList.listName +
        '</div><button class="btn btn-dark text-right deleteList" style="color: white; height: 80%">Delete<div style="display:none" class="listIDFromMovie">' +
        listID +
        "</div></button>";

      for (i = 0; i < movies.length; i++) {
        var rating = movies[i].userScore;
        var string = movies[i].releaseDate.substring(0, 4);
        var release = "(" + string + ")";
        row +=
          '<div class="row" style="border: 1px solid #14120b; width: 100%; margin-left:1vw;"><a id="myMovieLink"><div class="row myMovie" style="background-color: white; font-size: 2vw; width: 100%;">' +
          movies[i].title +
          " " +
          release +
          " " +
          '<span class="ml-auto star-rating star' +
          rating +
          '"></span></div>' +
          '<div style="display:none" class="myMovieID">' +
          movies[i].movieID +
          '</div><div style="display:none" class="listIDFromMovie">' +
          listID +
          "</div></a></div>";
      }

      row += "</div>";
      contentRows.append(row);
      displayCompleteList();
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

/*Returns all lists to display in My Lists*/
function loadListLinks() {
  $("#myListContainer").empty();
  var contentRows = $("#myListContainer");
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/getMovieList",
    success: function (movieListArray) {
      $.each(movieListArray, function (index, movie) {
        var listID = movie.listID;
        var listName = movie.listName;

        var row =
          '<a class="listLink"><div class="col-5 btn btn-primary listName" style="margin-top: 1vw; margin-bottom: 1vw; margin-left: 3.7vw;">' +
          listName +
          '</div><div class="listID" style="display:none">' +
          listID +
          "</div></div></a>";

        contentRows.append(row);
        $("#myStatsTitle").show();
        $("#myStatsContainer").show();
      });
    },
    error: function () {
      $("#myListContainer").hide();
      $("#myListsTitle").hide();
      $("#errorMessages").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("Error calling web service.. Please try again later.")
      );
    },
  });
}

//Delete list from database
function deleteList(listID) {
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/deleteList",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      listID: listID,
    }),
    success: function (movieList) {
      displayHomePage();
      $("#listToBeDeleted").empty();
    },
    error: function () {
      $("#listToBeDeleted").empty();
      $("#errorMessages").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("Error calling web service.. Please try again later.")
      );
    },
  });
}

//Delete movie from list and database
function deleteMovie(movieID) {
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/deleteMovie",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      movieID: movieID,
    }),
    success: function (movieList) {
      displayHomePage();
      //Add ability to confirm to user that movie was deleted
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

function updateUserScore(movieID, listID) {
  var userScore = $("input[name=rating]").val();
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/updateUserScore",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      movieID: movieID,
      userScore: userScore,
    }),
    success: function (message) {
      findMyMovie(movieID, listID);
      $("#searchResult").empty();
      $(".jqEmoji-container").children().css("opacity", ".2");
      displayMyMovie();
    },
    error: function (xhr, status, error) {
      /*var message = xhr.responseText;
      var newMessage = message.substring(12, message.length-2);
      $('#enter-messages').html(newMessage);*/
    },
  });
}

function getColors(length) {
  let pallet = [
    "#003f5c",
    "#444e86",
    "#955196",
    "#dd5182",
    "#ff6e54",
    "#f2ac16",
  ];
  let colors = [];
  var k = 0;
  for (let i = 0; i < length; i++) {
    if (k == 6) {
      k = 0;
    }
    colors.push(pallet[k]);
    k++;
  }
  return colors;
}

function getSingleColor(length) {
  let pallet = [
    "#003f5c",
    "#444e86",
    "#955196",
    "#dd5182",
    "#ff6e54",
    "#f2ac16",
  ];
  var k = 0;
  for (let i = 0; i < length; i++) {
    if (k == 5) {
      k = 0;
    }
    k++;
  }
  return pallet[k];
}

/*Chart for Genre Stats*/
function getAllGenres() {
  if (chart) {
    chart.destroy();
  }
  genreNamesX = [];
  genreCountY = [];
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/getAllGenres",
    success: function (genreArray) {
      $.each(genreArray, function (index, genre) {
        genreNamesX.push(genre.genreName);
        genreCountY.push(genre.genreCount);
        console.log(genre.genreName);
        console.log(genre.genreCount);
      });
      chart = new Chart(document.getElementById("genreChart"), {
        type: "doughnut",
        responsive: false,
        data: {
          labels: genreNamesX,
          datasets: [
            {
              borderWidth: 4,
              backgroundColor: getColors(genreCountY.length),
              data: genreCountY,
            },
          ],
        },
        options: {
          animation: {
            duration: 3000,
            animateRotate: true,
          },
          legend: {
            display: false,
          },
          responsive: true,
          title: {
            display: true,
            fontSize: 30,
            fontColor: "#f2ac16",
            text: "Genre counts across all lists.",
            padding: 10,
          },
        },
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

/*Chart for Actors*/
function getAllActors() {
  var actorNamesX = [];
  var actorCountY = [];
  if (chart) {
    chart.destroy();
  }
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/getAllActors",
    success: function (actorArray) {
      $.each(actorArray, function (index, actor) {
        actorNamesX.push(actor.actorName);
        actorCountY.push(actor.actorCount);
        console.log(actor.actorName);
        console.log(actor.actorCount);
      });
      chart = new Chart(document.getElementById("genreChart"), {
        type: "bar",
        data: {
          labels: actorNamesX,
          datasets: [
            {
              backgroundColor: getColors(actorCountY.length),
              data: actorCountY,
            },
          ],
        },
        options: {
          animation: {
            duration: 3000,
            animateRotate: true,
          },
          legend: {
            display: false,
          },
          responsive: true,
          title: {
            display: true,
            fontSize: 30,
            fontColor: "#f2ac16",
            text: "Top 30 Most Viewed Actors/Actresses",
            padding: 10,
          },
          scales: {
            yAxes: [
              {
                ticks: {
                  fontColor: "white",
                  beginAtZero: true,
                },
              },
            ],
            xAxes: [
              {
                ticks: {
                  fontColor: "white",
                },
              },
            ],
          },
        },
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

function scoreComparisonChart() {
  if (chart) {
    chart.destroy();
  }
  var userScore = [];
  var popularityScore = [];
  var titles = [];
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/scoreComparisonChart",
    success: function (movieArray) {
      $.each(movieArray, function (index, movie) {
        userScore.push(movie.userScore);
        popularityScore.push(movie.popularity);
        titles.push(movie.title);
        console.log(movie.title);
        console.log(movie.userScore);
        console.log(movie.popularity);
      });
      chart = new Chart(document.getElementById("genreChart"), {
        type: "bar",
        responsive: false,
        data: {
          labels: titles,
          datasets: [
            {
              label: "My Score",
              borderWidth: 1,
              backgroundColor: "#003f5c",
              data: userScore,
            },
            {
              label: "TMDB User's Score",
              borderWidth: 1,
              backgroundColor: "#955196",
              data: popularityScore,
            },
          ],
        },
        options: {
          animation: {
            duration: 3000,
            animateRotate: true,
          },
          legend: {
            display: true,
            labels: {
              fontColor: "white",
            },
          },
          responsive: true,
          title: {
            display: true,
            fontSize: 30,
            fontColor: "#f2ac16",
            text: "My Score Compared To TMDB Users",
            padding: 10,
          },
          scales: {
            yAxes: [
              {
                ticks: {
                  max: 10,
                  fontColor: "white",
                  beginAtZero: true,
                },
              },
            ],
            xAxes: [
              {
                ticks: {
                  fontColor: "white",
                },
              },
            ],
          },
        },
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

function budgetScoreChart() {
  if (chart) {
    chart.destroy();
  }
  var userScore = [];
  var budget = [];
  var titles = [];
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/budgetScoreChart",
    success: function (movieArray) {
      $.each(movieArray, function (index, movie) {
        userScore.push(movie.userScore);
        budget.push(movie.budget);
        titles.push(movie.title);
        console.log(movie.title);
        console.log(movie.userScore);
        console.log(movie.budget);
      });
      chart = new Chart(document.getElementById("genreChart"), {
        type: "bar",
        responsive: false,
        data: {
          labels: titles,
          datasets: [
            {
              type: "line",
              label: "Budget",
              data: budget,
              backgroundColor: "#f2ac16",
              borderColor: "#f2ac16",
              fill: false,
              yAxisID: "B",
              order: 2,
            },
            {
              type: "bar",
              label: "My Score",
              borderWidth: 1,
              backgroundColor: "#003f5c",
              data: userScore,
              yAxisID: "A",
              order: 1,
            },
          ],
        },
        options: {
          animation: {
            duration: 3000,
            animateRotate: true,
          },
          legend: {
            display: true,
            labels: {
              fontColor: "white",
            },
          },
          responsive: true,
          title: {
            display: true,
            fontSize: 30,
            fontColor: "#f2ac16",
            text: "Big budget or Indie?",
            padding: 10,
          },
          scales: {
            yAxes: [
              {
                ticks: {
                  fontColor: "white",
                  beginAtZero: true,
                },
                scaleLabel: {
                  fontColor: "white",
                  display: true,
                  labelString: "My Score",
                },
                id: "A",
                type: "linear",
                position: "right",
              },
              {
                ticks: {
                  fontColor: "white",
                  beginAtZero: true,
                },
                scaleLabel: {
                  fontColor: "white",
                  display: true,
                  labelString: "Budget",
                },
                id: "B",
                type: "linear",
                position: "left",
              },
            ],
            xAxes: [
              {
                ticks: {
                  fontColor: "white",
                },
              },
            ],
          },
        },
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

function movieLengthChart() {
  if (chart) {
    chart.destroy();
  }
  var userScore = [];
  var runtime = [];
  var titles = [];
  var total = 0;
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/movieLengthChart",
    success: function (movieArray) {
      $.each(movieArray, function (index, movie) {
        userScore.push(movie.userScore);
        runtime.push(movie.runTime);
        titles.push(movie.title);
      });
      chart = new Chart(document.getElementById("genreChart"), {
        type: "bar",
        responsive: false,
        data: {
          labels: titles,
          datasets: [
            {
              type: "line",
              label: "Runtime",
              data: runtime,
              backgroundColor: "#f2ac16",
              borderColor: "#f2ac16",
              fill: false,
              yAxisID: "B",
              order: 2,
            },
            {
              type: "bar",
              label: "My Score",
              borderWidth: 1,
              backgroundColor: "#003f5c",
              data: userScore,
              yAxisID: "A",
              order: 1,
            },
          ],
        },
        options: {
          animation: {
            duration: 3000,
            animateRotate: true,
          },
          legend: {
            labels: {
              fontColor: "white",
            },
            display: true,
          },
          responsive: true,
          title: {
            display: true,
            fontSize: 30,
            fontColor: "#f2ac16",
            text: "Long or Short Movie?",
            padding: 10,
          },
          scales: {
            yAxes: [
              {
                ticks: {
                  fontColor: "white",
                  max: 10,
                  beginAtZero: true,
                },
                scaleLabel: {
                  fontColor: "white",
                  display: true,
                  labelString: "My Score",
                },
                id: "A",
                type: "linear",
                position: "right",
              },
              {
                ticks: {
                  fontColor: "white",
                  min: 60,
                  beginAtZero: true,
                },
                scaleLabel: {
                  fontColor: "white",
                  display: true,
                  labelString: "Runtime",
                },
                id: "B",
                type: "linear",
                position: "left",
              },
            ],
            xAxes: [
              {
                ticks: { fontColor: "white" },
              },
            ],
          },
        },
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

function totalRuntimeChart() {
  if (chart) {
    chart.destroy();
  }
  var test = [];
  test.push("Run Time");
  var movieLength = [];
  var runtime = [];
  var titles = [];
  var totalMin = 0;
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/movieLengthChart",
    success: function (movieArray) {
      $.each(movieArray, function (index, movie) {
        runtime.push(movie.runTime);
        titles.push(movie.title);
        totalMin += movie.runTime;
      });
      var totalHour = totalMin / 60;
      var totalDay = totalHour / 24;
      for (var i = 0; i < movieArray.length; i++) {
        movieLength[i] = {
          title: "",
          label: titles[i],
          data: [runtime[i]],
          backgroundColor: getSingleColor(i),
          borderStyle: "solid",
          borderWidth: 2,
        };
      }
      chart = new Chart(document.getElementById("genreChart"), {
        type: "horizontalBar",
        responsive: false,
        labels: movieArray,
        data: {
          datasets: movieLength,
        },
        options: {
          tooltips: {
            mode: "nearest",
            callbacks: {
              title: function () {},
            },
          },
          animation: {
            duration: 3000,
            animateRotate: true,
          },
          responsive: true,
          title: {
            display: true,
            fontSize: 30,
            fontColor: "#f2ac16",
            text:
              "Total Viewing Time: (" +
              totalMin +
              " minutes) OR (" +
              totalHour.toFixed(2) +
              " hours) OR (" +
              totalDay.toFixed(2) +
              " days)",
            padding: 10,
          },
          legend: {
            display: false,
          },
          scales: {
            yAxes: [
              {
                ticks: {
                  fontColor: "white",
                },
                barThickness: 60,
                stacked: true,
              },
            ],
            xAxes: [
              {
                scaleLabel: {
                  display: true,
                  labelString: "Time (in minutes)",
                  fontColor: "white",
                },
                ticks: {
                  fontColor: "white",
                },
                categoryPercentage: 1.0,
                barPercentage: 1.0,
                stacked: true,
              },
            ],
          },
        },
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

/*All Page Displays -> Route every event to one of these*/

function displayHomePage() {
  loadListLinks();
  $("#searchDiv").show();
  $("#myListsTitle").show();
  $("#myListContainer").show();
  $("#resultsBox").hide();
  $("#selectedList").hide();
  $("#searchResult").hide();
  $("#listDiv").hide();
  $("#movieRating").hide();
  $("#updateUserScore").hide();
  $("#deleteWarningScreen").hide();
  $("#myCharts").hide();
}

function displayMovie() {
  getMovieListsForSelection();
  $("#searchResult").show();
  $("#listDiv").show();
  $("#resultsBox").hide();
  $("#movieRating").hide();
  $("#searchDiv").hide();
  $("#myListsTitle").hide();
  $("#myListContainer").hide();
  $("#selectedList").hide();
  $("#updateUserScore").hide();
  $("#deleteWarningScreen").hide();
  $("#myStatsTitle").hide();
  $("#myStatsContainer").hide();
  $("#myCharts").hide();
}

function displayMyMovie() {
  $("#searchResult").show();
  $("#movieRating").show();
  $("#myRating").show();
  $("#updateScoreButton").show();
  $("#resultsBox").hide();
  $("#likeIt").hide();
  $("#addMovieButton").hide();
  $("#listDiv").hide();
  $("#searchDiv").hide();
  $("#myListsTitle").hide();
  $("#myListContainer").hide();
  $("#selectedList").hide();
  $("#updateUserScore").hide();
  $("#deleteWarningScreen").hide();
  $("#myStatsTitle").hide();
  $("#myStatsContainer").hide();
  $("#myCharts").hide();
}

function displayRateMovie() {
  $("#searchResult").show();
  $("#movieRating").show();
  $("#likeIt").show();
  $("#addMovieButton").show();
  $("#resultsBox").hide();
  $("#myRating").hide();
  $("#updateScoreButton").hide();
  $("#listDiv").hide();
  $("#searchDiv").hide();
  $("#myListsTitle").hide();
  $("#myListContainer").hide();
  $("#selectedList").hide();
  $("#updateUserScore").hide();
  $("#deleteWarningScreen").hide();
  $("#myStatsTitle").hide();
  $("#myStatsContainer").hide();
  $("#myCharts").hide();
}

function displayCompleteList() {
  $("#selectedList").show();
  $("#resultsBox").hide();
  $("#searchResult").hide();
  $("#movieRating").hide();
  $("#listDiv").hide();
  $("#searchDiv").hide();
  $("#myListsTitle").hide();
  $("#myListContainer").hide();
  $("#updateUserScore").hide();
  $("#deleteWarningScreen").hide();
  $("#myStatsTitle").hide();
  $("#myStatsContainer").hide();
  $("#myCharts").hide();
}

function displayDeleteWarningScreen() {
  $("#deleteWarningScreen").show();
  $("#searchResult").hide();
  $("#listDiv").hide();
  $("#movieRating").hide();
  $("#searchDiv").hide();
  $("#myListsTitle").hide();
  $("#myListContainer").hide();
  $("#selectedList").hide();
  $("#updateUserScore").hide();
  $("#resultsBox").hide();
  $("#myStatsTitle").hide();
  $("#myStatsContainer").hide();
  $("#myCharts").hide();
}

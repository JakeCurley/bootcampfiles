$(document).ready(function() {

  $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/guessGame/game',
      success: function(gameArray) {
          // get a reference to the 'allContacts' div
          var gamesDiv = $('#allGames');

          // go through each of the returned contacts and append the info to the
          //contactsDiv
          $.each(gameArray, function(index, game) {
              var gameInfo = '<p>';
              gameInfo += 'GameID: ' + game.gameID + '<br>';
              gameInfo += 'Finished: ' + game.finished;
              gameInfo += '<hr>';

              gamesDiv.append(gameInfo);
          })
      },
      error: function (jqXHR, textStatus, errorThrown) {
          alert("FAILURE!");
      }
  });
  $('#guess-button').on('click', function() {
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/guessGame/guess',
      data: JSON.stringify({
        gameID: $('#gameID').val(),
        guess: $('#guess').val()
      }),
      headers: {
        'Accept' : 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'json',
      success: function (guess) {
        var newGuess = $('#newGuess');

        var guessInfo = '<p>';
        guessInfo += 'Guess: ' + guess.guess;
        guessInfo += 'GuessTime: ' + guess.guessTime;
        guessInfo += 'CorrectGuess: ' + guess.correctGuess;
        guessInfo += 'Part: ' + guess.part;
        guessInfo+= 'Guess: ' + guess.exact;
        guessInfo+= '<hr>'

        newGuess.append(guessInfo);
      },
      error: function(jqXHR, textStatus, errorThrown) {
          alert('FAILURE');
      }
  });
 });
})

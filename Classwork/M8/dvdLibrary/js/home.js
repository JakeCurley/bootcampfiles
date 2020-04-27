$(document).ready(function() {
  loadDvds();
  $('#create-button').click(function(event) {

  });

  $('#confirm-button').click(function(event) {
    createDvd();
  });

  //http://localhost:8080/dvds
});

function loadDvds() {
  clearDvdTable();
  var contentRows = $('#contentRows');

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/dvds",
    success: function(dvdArray) {
      $.each(dvdArray, function(index, dvd) {
        var dvdId = dvd.dvdId;
        var title = dvd.title;
        var releaseDate = dvd.releaseYear;
        var director = dvd.director;
        var rating = dvd.rating;
        var notes = dvd.notes;
        var row = '<tr>';
            row += '<td>' + title + '</td>';
            row += '<td>' + releaseDate + '</td>';
            row += '<td>' + director + '</td>';
            row += '<td>' + rating + '</td>';
            row += '<td><a onclick="showEditForm(' + dvdId + ')">Edit |</a><a onclick="deleteContact(' + dvdId + ')"> Delete</a></td>';
            row += '/tr>';

        contentRows.append(row);
      });
    },
    error: function() {
      $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service.. Please try again later.'))
    }
  });
}

function createDvd() {
  $.ajax({
    type: 'POST',
    url: 'http://localhost:8080/dvd',
    data: JSON.stringify({
      title: $('#add-title').val(),
      releaseDate: $('#add-releaseDate').val(),
      director: $('#add-director').val(),
      rating: $('#add-rating').val(),
      notes: $('#add-notes').val()
    }),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    'dataType': 'json',
    success: function() {
      //$('#errorMessages').empty();
      $('#add-title').val('');
      $('#add-releaseDate').val('');
      $('#add-director').val('');
      $('#add-rating').val('');
      $('#add-notes').val('');
      loadDvds();
    },
    error: function() {
      $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service.. Please try again later.'))
    }
  })
}

function clearDvdTable() {
  $('#contentRows').empty();
}

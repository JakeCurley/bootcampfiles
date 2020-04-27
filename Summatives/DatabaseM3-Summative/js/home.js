$(document).ready(function(){
  clearItem();
  clearChange();
  clearMoney();
  loadItems();
  $('#add-quarter').click(function(event) {
    clearChange();
    var change = .25;
    addMoney(change);
  });
  $('#add-dime').click(function(event) {
    clearChange();
    change = .10;
    addMoney(change);
  });
  $('#add-nickel').click(function(event) {
    clearChange();
    change = .05;
    addMoney(change);
  });
  $('#add-dollar').click(function(event) {
    clearChange();
    change = 1;
    addMoney(change);
  });
  $('#change-button').click(function(event) {
    loadItems();
    var money = $('#enter-money').val();
    if (money == '') {
      clearChange();
    } else {
      returnChangeNoPurchase();
    }
  });

  $(document).on('click', '#test', function(event) {
    clearChange();
    var realId = $(this).find('.id').html();
    $('#hidden-div').html(realId);
    var itemId = $(this).find('.itemId').html();
    chooseItem(itemId);
  });
  $('#buy-button').click(function(event) {
    var realId = $('#hidden-div').html();
    if (realId == '') {
      $('#enter-messages').html('Please make a selection');
    } else {
      buyItem(realId);
    }
  });
});

function loadItems() {
  clearItems();
  var newId = 0;
  $.ajax({
    type: "GET",
    url: "http://tsg-vending.herokuapp.com/items",
    success: function(itemArray) {
      $.each(itemArray, function(index, item) {
          newId++;
          var itemId = item.id;
          var name = item.name;
          var price = item.price;
          var quantity = item.quantity;
          var row = '<a id="test" style="cursor:pointer">';
              row += '<div class="col-sm-3" style="padding-top: 5px">';
              row += '<div class="row text-left"><p style="display:none" class="id">'+itemId+'</p></div>';
              row += '<div class="row text-left"><p style="margin-left: .5vw" class="itemId">'+newId+'</p></div>';
              row += '<div class="row text-center" style="padding-top: 5px"><p>'+name+'</p></div>';
              row += '<div class="row text-center align-middle cost" style="padding-top: 5px"><p>$'+price+'</p></div>';
              row += '<div class="row text-center" style="padding-top: 5px"><p>Quantity Left: '+quantity+'</p></div>';
              row += '</div></a>';

          $('#content').append(row);
      })
    },
    error: function() {
      $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling web service.. Please try again later.'))
    }
  });
}

function addMoney(addMoney) {
  var money = $('#enter-money').val();
  $('#enter-money').empty();
  var newMoney = +money + +addMoney;
  $('#enter-money').val(newMoney.toFixed(2));
}

function returnChangeNoPurchase() {
  var money= $('#enter-money').val();
  var quarter = 0;
  var dime = 0;
  var nickel = 0;
  var penny = 0;

  while (money > 0) {
    while (money >= .25) {
      quarter++;
      money = (+money - .25).toFixed(2);
    }
    while(money >= .10) {
      money = (+money - .10).toFixed(2);
      dime++;
    }
    while (money >= .05) {
      money = (+money - .05).toFixed(2);
      nickel++;
    }
    while (money >= .01) {
      money = (+money - .01).toFixed(2);
      penny++;
    }
  }
  makeChange(quarter, dime, nickel, penny);
}

function chooseItem(item) {
  $('#enter-item').val(item);
}

function buyItem(realId) {
  var money = $('#enter-money').val();
    $.ajax({
      type: 'POST',
      url: 'http://tsg-vending.herokuapp.com/money/'+money+'/item/'+realId,
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'json',
      success: function(message) {
        var quarters = message.quarters;
        var dimes = message.dimes;
        var nickels = message.nickels;
        var pennies = message.pennies;
        loadItems();
        clearMoney();
        clearItem();
        makeChange(quarters, dimes, nickels, pennies);
        $('#enter-messages').html('Thank You!!');


      },
      error: function(xhr, status, error) {
        var message = xhr.responseText;
        var newMessage = message.substring(12, message.length-2);
        $('#enter-messages').html(newMessage);
      }
    })
}

function clearItems() {
  $('#content').text('');
}

function clearItem() {
  $('#enter-item').val('');
}

function clearChange() {
  $('#change-return').val('');
}

function clearMoney() {
  $('#enter-money').val('0.00');
}

function makeChange(quarters, dimes, nickels, pennies) {
  clearChange();
  var addQ = '';
  var addD = '';
  var addN = '';
  var addP = '';
  if (quarters > 0) {
    if (quarters == 1) {
      addQ = quarters + ' quarter';
    } else {
    addQ = quarters + ' quarters';
    }
  }
  if (dimes > 0) {
    if (dimes == 1) {
      addD = dimes + ' dime';
    } else {
    addD = dimes + ' dimes';
    }
  }
  if (nickels > 0) {
    if (nickels == 1) {
      addN = nickels + ' nickel';
    } else {
    addN = nickels + ' nickels';
    }
  }
  if (pennies > 0) {
    if (pennies == 1) {
      addP = pennies + ' penny';
    } else {
    addP = pennies + ' pennies';
    }
  }
  $('#change-return').val(addQ + ' ' + addD + ' ' + addN + ' ' +  addP);
  clearMoney();
}

function validate() {
    var isbn = document.getElementById("isbn-input");
    var pieces = document.getElementById("pieces-input");
    var price = document.getElementById("price-input");

    var flag = true;

    if(isbn.value == "") {
        isbn.style.background = "#ebb5b5";
        flag = false;
    } else {
        isbn.style.background = "#ffffff";
    }

    if(pieces.value == "" || isNaN(pieces.value)) {
        pieces.style.background = "#ebb5b5";
        flag = false;
    } else {
        pieces.style.background = "#ffffff";
    }

    if(price.value != "") {
        if(isNaN(price.value)) {
            price.style.background = "#ebb5b5";
            flag = false;
        } else {
            price.style.background = "#ffffff";
        }
    }

    return flag;
}
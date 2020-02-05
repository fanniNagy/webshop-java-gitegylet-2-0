function init() {
    add_event_listener();
    update_total_price();
    check_for_empty_cart();
}

function check_for_empty_cart() {
    let products = document.querySelector("#products");
    let line_items = products.querySelectorAll(".card");

    if (line_items.length == 0) {
        products.innerHTML = "<h2>Your Cart Is Empty :(</h2>";
        document.querySelector("#total-price-wrapper").remove();
    }
}

function add_event_listener() {
    let add_to_cart_buttons = document.querySelectorAll(".increaseQuantity");
    for (let button of add_to_cart_buttons) {
        button.addEventListener("click", (e) => {handle_add_to_cart(e)});
    }

    let remove_from_cart_buttons = document.querySelectorAll(".decreaseQuantity");
    for (let button of remove_from_cart_buttons) {
        button.addEventListener("click", (e) => {handle_remove_from_cart(e)});
    }
}

function handle_add_to_cart(event) {
    fetch("/add-to-cart", {
        method:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id:event.target.closest("[data-id]").dataset.id})
    })
        .then(() => {
            let quantityDiv = event.target.closest(".quantity").querySelector("#item-quantity");
            console.log(event.target);

            quantityDiv.innerHTML = (parseInt(quantityDiv.innerHTML) + 1).toString();
            update_total_price();
        })
}

function handle_remove_from_cart(event) {
    fetch("/remove-from-cart", {
        method:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id:event.target.closest("[data-id]").dataset.id})
    })
        .then(() => {
        let quantityDiv = event.target.closest(".quantity").querySelector("#item-quantity");
        let quantity = parseInt(quantityDiv.innerHTML);
        if(quantity > 1){
            quantityDiv.innerHTML = (parseInt(quantityDiv.innerHTML) - 1).toString();
        } else {
            quantityDiv.closest(".card").parentElement
                .parentElement.removeChild(quantityDiv.closest(".card").parentElement);
            check_for_empty_cart();
        }
        update_total_price();
    })
    
}

function update_total_price() {
    let products = document.querySelector("#products");
    let line_items = products.querySelectorAll(".card");
    let total = 0;
    for (let line_item of line_items) {
        let price = parseInt(line_item.dataset.price);
        let quantity = parseInt(line_item.querySelector("#item-quantity").innerHTML);
        total += price * quantity;
    }

    let total_price = document.querySelector("#total-price");
    total_price.innerText = total + ' GAL';
}

window.onload = init;
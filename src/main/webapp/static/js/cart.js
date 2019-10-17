function init() {
    add_event_listener();
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
        }
    })
    
}

window.onload = init;
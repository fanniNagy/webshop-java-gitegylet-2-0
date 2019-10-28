function init() {
    add_event_listener();
    update_cart_size();
}

function update_cart_size() {
    fetch("/api/get-cart-size")
        .then(response => response.json())
        .then(json_response => {
            document.querySelector("#cart_size").innerHTML = json_response.cartSize;
        });
}

function add_event_listener() {
    let add_to_cart_buttons = document.querySelectorAll("[data-id]");
    for (let button of add_to_cart_buttons) {
        button.addEventListener("click", (e) => {
                handle_add_to_cart(e)
            }
        )
        ;
    }
}

function handle_add_to_cart(event) {
    fetch("/add-to-cart", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id: event.currentTarget.dataset.id})
    })
        .then(response => response.json()
        )
        .then(json_response => {
            document.querySelector("#cart_size").innerHTML = json_response.cartSize;
        })
    ;
}

window.onload = init;
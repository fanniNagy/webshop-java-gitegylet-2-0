function add_listener_to_buttons() {
    let add_to_cart_buttons = document.querySelectorAll("[data-id]");
    for (let button of add_to_cart_buttons) {
        button.addEventListener("click", (e) => {handle_add_to_cart(e)});
    }
}

function handle_add_to_cart(event) {
    fetch("/add-to-cart", {
        method:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id:event.currentTarget.dataset.id})
    }).then((response) => console.log(response))
}

window.onload = add_listener_to_buttons;
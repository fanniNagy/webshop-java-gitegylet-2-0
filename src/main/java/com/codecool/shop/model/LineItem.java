package com.codecool.shop.model;

public class LineItem extends BaseModel {
    private Product product;
    private int quantity;
    private User user;

    public LineItem(Product product, User user) {
        super("LineItem" + product.getId());
        this.product = product;
        this.user = user;
        this.quantity = 1;
    }

    public LineItem(Product product, int quantity, User user) {
        super("LineItem" + product.getId());
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

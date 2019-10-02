package com.codecool.shop.model;

public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public int decreaseQuantity() {
       return --this.quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

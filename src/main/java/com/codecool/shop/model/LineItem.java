package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.jdbc.LineItemQuantity;

public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public void increaseQuantity() {
        this.quantity++;
        LineItemQuantity.getInstance().increaseQuantity(product.id);
    }

    public void decreaseQuantity() {
        this.quantity--;
        LineItemQuantity.getInstance().decreaseQuantity(product.id);
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

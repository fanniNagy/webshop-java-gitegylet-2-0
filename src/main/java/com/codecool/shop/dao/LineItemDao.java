package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface LineItemDao {
    void add(LineItem lineItem);
    LineItem find(User user, Product product);
    void increaseQuantity(LineItem lineItem);
    void decreaseQuantity(LineItem lineItem);
    List<LineItem> getAll(User user);
}

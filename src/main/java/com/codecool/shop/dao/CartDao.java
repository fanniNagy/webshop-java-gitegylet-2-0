package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

import java.util.List;

public interface CartDao {

    void add(LineItem lineItem);
    ProductCategory find(int id);
    void remove(int id);

    List<LineItem> getAll();
    Cart getByUser(User user);
    LineItem getLineItemByProductIdIfExists(int productId);

    void removeNullQuantityLineItems();
}

package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

public interface CartDao extends Dao {
    void add(Cart cart);
    ProductCategory find(int id);
    void remove(int id);

    Cart getByUser(User user);
}

package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {
    void add(Order order);
    Order find(int id);
    void remove(int id);

    List<Order> getAll();
    Order getBy(CartDaoMem cart);
}

package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.model.Order;

import java.util.List;

public class OrderDaoJDBC extends DaoJDBC implements OrderDao {

    private static OrderDao instance = null;


    private OrderDaoJDBC() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order getBy(CartDaoMem cart) {
        return null;
    }
}

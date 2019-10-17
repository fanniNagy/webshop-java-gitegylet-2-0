package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

public class CartDaoJDBC extends DaoJDBC implements CartDao {
    private static CartDao instance = null;

    private CartDaoJDBC() {
    }

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Cart cart) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Cart getByUser(User user) {
        return null;
    }
}

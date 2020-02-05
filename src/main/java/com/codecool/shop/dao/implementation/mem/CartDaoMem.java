package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartDaoMem implements CartDao {
    private static CartDaoMem instance;
    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(LineItem lineItem) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }

    @Override
    public Cart getByUser(User user) {
        return null;
    }

    @Override
    public LineItem getLineItemByProductIdIfExists(int productId) {
        return null;
    }

    @Override
    public void removeNullQuantityLineItems() {

    }
}

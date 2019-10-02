package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem {
    private static CartDaoMem instance;
    private List<LineItem> lineItems = new ArrayList<>();

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    public void empty() {
        lineItems.clear();
    }

    public void add(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public List<LineItem> getAll() {
        return lineItems;
    }

    public LineItem getLineItemByProductIdIfExists(int productId) {
        return lineItems
                .stream()
                .filter(lineItem -> lineItem.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);
    }
}

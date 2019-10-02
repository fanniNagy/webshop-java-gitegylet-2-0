package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        removeNullQuantityLineItems();
        return lineItems;
    }

    //TODO Talán ez a baj ha van.
    private void removeNullQuantityLineItems() {
        lineItems.removeAll(lineItems.stream().filter(lineItem -> lineItem.getQuantity() <= 0).collect(Collectors.toList()));
    }

    public LineItem getLineItemByProductIdIfExists(int productId) {
        return lineItems
                .stream()
                .filter(lineItem -> lineItem.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);
    }
}

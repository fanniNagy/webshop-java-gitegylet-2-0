package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {

    private List<LineItem> lineItems = new ArrayList<>();

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
    public void removeNullQuantityLineItems() {
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

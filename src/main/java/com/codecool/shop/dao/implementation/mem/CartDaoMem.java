package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartDaoMem {
    private static CartDaoMem instance;
    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }


}

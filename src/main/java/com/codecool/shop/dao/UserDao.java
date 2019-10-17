package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

public interface UserDao extends Dao {
    void add(User user);
    User find(int id);
    void remove(int id);
}

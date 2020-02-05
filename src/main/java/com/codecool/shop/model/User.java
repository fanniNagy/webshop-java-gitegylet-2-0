package com.codecool.shop.model;

public class User extends BaseModel {
    private String name;
    private String password;
    private String email;


    public User(String name) {
        super(name);
    }

    public User(String name, String description) {
        super(name, description);
    }
}

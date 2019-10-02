package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.CartDaoMem;

public class Order {
    final int orderId;
    CartDaoMem cart = CartDaoMem.getInstance();
    final String name;
    final Address shippingAddress;
    final Address billingAddress;
    final String phoneNumber;
    final byte countryCode;

    public Order(int orderId, CartDaoMem cart, String name, Address shippingAddress, Address billingAddress, String phoneNumber, byte countryCode) {
        this.orderId = orderId;
        this.cart = cart;
        this.name = name;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public CartDaoMem getCart() {
        return cart;
    }

    public String getName() {
        return name;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public byte getCountryCode() {
        return countryCode;
    }
}

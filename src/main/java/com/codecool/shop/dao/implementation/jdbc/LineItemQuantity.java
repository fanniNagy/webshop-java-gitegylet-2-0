package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.model.LineItem;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LineItemQuantity extends DaoJDBC {
    private static LineItemQuantity instance = null;

    private LineItemQuantity() {
    }

    public static LineItemQuantity getInstance() {
        if (instance == null) {
            instance = new LineItemQuantity();
        }
        return instance;
    }

    public void increaseQuantity(int productId) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement("UPDATE line_item SET quantity = quantity + 1 WHERE product_id = ?");
            stmt.setInt(1, productId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void decreaseQuantity(int productId) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement("UPDATE line_item SET quantity = quantity - 1 WHERE product_id = ?");
            stmt.setInt(1, productId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CartDaoJDBC.getInstance().removeNullQuantityLineItems();
    }
}

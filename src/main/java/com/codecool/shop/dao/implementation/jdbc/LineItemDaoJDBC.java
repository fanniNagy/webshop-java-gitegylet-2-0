package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import com.sun.org.apache.bcel.internal.generic.DADD;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoJDBC extends DaoJDBC implements LineItemDao {

    private static LineItemDao instance = null;

    private LineItemDaoJDBC() {
    }

    public static LineItemDao getInstance() {
        if (instance == null) {
            instance = new LineItemDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(LineItem lineItem) {

    }

    @Override
    public LineItem find(User user, Product product) {
        int userId = 1;
        int productId = product.getId();

        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement statement = conn.prepareStatement("SELECT * FROM line_item " +
                    "JOIN cart on line_item.cart_id = cart.id " +
                    "JOIN \"user\" ON cart.user_id = \"user\".id " +
                    "WHERE \"user\".id = ? AND product_id = ?;");
            statement.setInt(1,userId);
            statement.setInt(2, productId);
            @Cleanup ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ProductDao productDao = DaoFactory.getProductDao();
            UserDao userDao = DaoFactory.getUserDao();
            LineItem lineItem = new LineItem(productDao.find(productId), userDao.find(userId));
            lineItem.setQuantity(resultSet.getInt("quantity"));
            return lineItem;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void increaseQuantity(LineItem lineItem) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement("UPDATE line_item SET quantity = quantity + 1 WHERE product_id = ?");
            stmt.setInt(1, lineItem.getProduct().getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decreaseQuantity(LineItem lineItem) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement update_statement = conn.prepareStatement("UPDATE line_item SET quantity = quantity - 1 WHERE product_id = ? RETURNING quantity, product_id;");
            update_statement.setInt(1, lineItem.getProduct().getId());
            ResultSet resultSet = update_statement.executeQuery();
            resultSet.next();
            if (resultSet.getInt("quantity") <= 0) {
                @Cleanup PreparedStatement delete_statement = conn.prepareStatement("DELETE FROM line_item WHERE product_id = ?");
                delete_statement.setInt(1, lineItem.getProduct().getId());
                delete_statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CartDaoJDBC.getInstance().removeNullQuantityLineItems();
    }

    @Override
    public List<LineItem> getAll(User user) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement statement = conn.prepareStatement("SELECT * FROM line_item");
            @Cleanup ResultSet resultSet = statement.executeQuery();
            LineItemDao lineItemDao = DaoFactory.getLineItemDao();
            ProductDao productDao = DaoFactory.getProductDao();
            List<LineItem> result = new ArrayList<>();
            while (resultSet.next()) {
                LineItem lineItem = lineItemDao.find(user, productDao.find(resultSet.getInt("product_id")));
                result.add(lineItem);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

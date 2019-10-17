package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

import java.sql.*;
import java.util.List;

public class CartDaoJDBC extends DaoJDBC implements CartDao {
    private static CartDaoJDBC instance = null;

    private CartDaoJDBC() {
    }

    public static CartDaoJDBC getInstance() {
        if (instance == null) {
            instance = new CartDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(LineItem lineItem) {
        Connection conn = this.getConnection();
        int productId = lineItem.getProduct().getId();

        if (this.getLineItemByProductIdIfExists(productId) != null) {
            try {
                Statement cs = conn.createStatement();

                String sql = "UPDATE Line_item\n" +
                        "\tSET quantity = quantity + 1\n" +
                        "\tWHERE product_id =" + productId;
                cs.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Statement cs = conn.createStatement();
                String sql = "INSERT INTO Line_item(\n" +
                        "\tproduct_id, quantity, cart_id)\n" +
                        "\tVALUES ("+ productId +", 1, 1);";
                cs.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        int productId = lineItem.getProduct().getId();
        try {
            Statement cs = conn.createStatement();
            String sql = "SELECT * FROM Line_item WHERE product_id =" + productId;
            ResultSet resultSet = cs.executeQuery(sql);
            if (!resultSet.next()) {
                Statement cs1 = conn.createStatement();
                String sql1 = "INSERT INTO Line_item(\n" +
                        "\tproduct_id, quantity, cart_id)\n" +
                        "\tVALUES ("+ productId +", 1, 1);";
            } else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeNullQuantityLineItems() {

    }
}

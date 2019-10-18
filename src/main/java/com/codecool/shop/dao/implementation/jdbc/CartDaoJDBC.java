package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.*;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
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
        try {
            @Cleanup Connection conn = this.getConnection();
            int productId = lineItem.getProduct().getId();

            if (this.getLineItemByProductIdIfExists(productId) != null) {
                try {
                    @Cleanup Statement cs = conn.createStatement();

                    String sql = "UPDATE Line_item SET quantity = quantity + 1 WHERE product_id =" + productId;
                    cs.execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    @Cleanup Statement cs = conn.createStatement();
                    String sql = "INSERT INTO Line_item(\n" +
                            "\tproduct_id, quantity, cart_id)\n" +
                            "\tVALUES ("+ productId +", 1, 1);";
                    cs.execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        ProductDao pd = DaoFactory.getProductDao();
        List<LineItem> result = new ArrayList<>();
        try {

            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement statement = conn.prepareStatement("SELECT * FROM line_item");
            @Cleanup ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int prodId = resultSet.getInt("product_id");
                LineItem lineItem = new LineItem(pd.find(prodId));
                result.add(lineItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Cart getByUser(User user) {
        return null;
    }

    @Override
    public LineItem getLineItemByProductIdIfExists(int productId) {
        try {
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM line_item WHERE product_id = ?");
            stmt.setInt(1, productId);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next()) {
                return null;
            } else {
                Product product = ProductDaoJDBC.getInstance().find(productId);
                LineItem lineItem = new LineItem(product);
                lineItem.setQuantity(resultSet.getInt("quantity"));
                return lineItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeNullQuantityLineItems() {
        try {
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM public.line_item WHERE quantity <= 0");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

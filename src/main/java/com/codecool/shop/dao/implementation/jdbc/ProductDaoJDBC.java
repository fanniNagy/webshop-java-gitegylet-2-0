package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC extends DaoJDBC implements ProductDao {
    private static ProductDao instance = null;


    private ProductDaoJDBC() {
    }

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement statement = conn.prepareStatement("SELECT * FROM product WHERE id=?");
            statement.setInt(1, id);
            @Cleanup ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Product product = new Product(  resultSet.getString("name"),
                                            resultSet.getFloat("default_price"),
                                            resultSet.getString("default_currency"),
                                            resultSet.getString("description"),
                                            ProductCategoryDaoJDBC.getInstance().find(resultSet.getInt("product_category_id")),
                                            SupplierDaoJDBC.getInstance().find(resultSet.getInt("supplier_id")));
            product.setId(id);
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> result = new ArrayList<>();
        try {
            @Cleanup Connection connection = this.getConnection();
            @Cleanup PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE supplier_id= ?");
            statement.setInt(1, supplier.getId());
            @Cleanup ResultSet resultSet = statement.executeQuery();
            ProductDao productDao = DaoFactory.getProductDao();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Product product = productDao.find(id);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> result = new ArrayList<>();
        try {
            @Cleanup Connection connection = this.getConnection();
            @Cleanup PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product_category_id= ?");
            statement.setInt(1, productCategory.getId());
            @Cleanup ResultSet resultSet = statement.executeQuery();
            ProductDao productDao = DaoFactory.getProductDao();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Product product = productDao.find(id);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

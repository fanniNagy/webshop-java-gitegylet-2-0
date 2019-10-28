package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ProductCategoryDaoJDBC extends DaoJDBC implements ProductCategoryDao {
    private static ProductCategoryDao instance = null;


    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDao getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement statement = conn.prepareStatement("SELECT * FROM category WHERE id=?");
            statement.setInt(1,id);
            @Cleanup ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ProductCategory productCategory = new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));
            productCategory.setId(id);
            return productCategory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    @Override
    public HashMap<ProductCategory, List<Product>> getProductCategoryMap() {
        HashMap<ProductCategory, List<Product>> productCategoryMap = new HashMap<>();
        List <ProductCategory> ProductCategories = getAll();
        for (ProductCategory productCategory : ProductCategories) {
            productCategoryMap.put(productCategory, productCategory.getProducts());
        }
        return productCategoryMap;
    }

}

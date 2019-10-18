package com.codecool.shop.dao.implementation.jdbc;

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
            statement.setInt(1,id);
            @Cleanup ResultSet resultSet = statement.executeQuery();
            return new Product(resultSet.getString("name"),resultSet.getFloat("default_price"), resultSet.getString("default_currency"), resultSet.getString("description"), ProductCategoryDaoJDBC.getInstance().find(resultSet.getInt("product_category_id")), SupplierDaoJDBC.getInstance().find(resultSet.getInt("supplier_id")));
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
        try{
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE supplier_id= ?");
            statement.setInt(1, supplier.getId());
            ResultSet resultSet = statement.executeQuery();
            while (!resultSet.next()) {
                String name = resultSet.getString("name");
                Float defaultPrice = resultSet.getFloat("default_price");
                String currencyString = resultSet.getString("default_currency");
                String description = resultSet.getString("description");
                ProductCategory productCategory = ProductCategoryDaoJDBC.getInstance().find(resultSet.getInt("product_category_id"));

                Product product = new Product(name, defaultPrice, currencyString, description, productCategory, supplier);
                result.add(product);
            }
            return result;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> result = new ArrayList<>();
        try{
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product_category_id= ?");
            statement.setInt(1, productCategory.getId());
            ResultSet resultSet = statement.executeQuery();
            while (!resultSet.next()) {
                String name = resultSet.getString("name");
                Float defaultPrice = resultSet.getFloat("default_price");
                String currencyString = resultSet.getString("default_currency");
                String description = resultSet.getString("description");
                Supplier supplier = new Supplier(resultSet.getString("name"), resultSet.getString("description"));
                Product product = new Product(name, defaultPrice, currencyString, description, productCategory, supplier);
                result.add(product);
            }
            return result;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

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
        return null;
    }
}

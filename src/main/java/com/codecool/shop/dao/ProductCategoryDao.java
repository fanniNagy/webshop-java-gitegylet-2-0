package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.HashMap;
import java.util.List;

public interface ProductCategoryDao extends Dao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    void remove(int id);

    List<ProductCategory> getAll();
    public HashMap<ProductCategory, List<Product>> getProductCategoryMap ();

}

package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.*;

public final class DaoFactory {
    private final static String DAO_TYPE = "JDBC";

    public static CartDao getCartDao() {
        if (DAO_TYPE.equals("JDBC")){
            return CartDaoJDBC.getInstance();
        } else if(DAO_TYPE.equals("MEM")) {
            return CartDaoMem.getInstance();
        }
        return null;
    }

    public static OrderDao getOrderDao() {
        if (DAO_TYPE.equals("JDBC")){
            return OrderDaoJDBC.getInstance();
        } else if(DAO_TYPE.equals("MEM")) {
            return OrderDaoMem.getInstance();
        }
        return null;
    }

    public static ProductCategoryDao getProductCategoryDao() {
        if (DAO_TYPE.equals("JDBC")){
            return ProductCategoryDaoJDBC.getInstance();
        } else if(DAO_TYPE.equals("MEM")) {
            return ProductCategoryDaoMem.getInstance();
        }
        return null;
    }

    public static ProductDao getProductDao() {
        if (DAO_TYPE.equals("JDBC")){
            return ProductDaoJDBC.getInstance();
        } else if(DAO_TYPE.equals("MEM")) {
            return ProductDaoMem.getInstance();
        }
        return null;
    }

    public static SupplierDao getSupplierDao() {
        if (DAO_TYPE.equals("JDBC")){
            return SupplierDaoJDBC.getInstance();
        } else if(DAO_TYPE.equals("MEM")) {
            return SupplierDaoMem.getInstance();
        }
        return null;
    }

    public static UserDao getUserDao() {
        if (DAO_TYPE.equals("JDBC")){
            return UserDaoJDBC.getInstance();
        } else if(DAO_TYPE.equals("MEM")) {
            return null;
        }
        return null;
    }
}

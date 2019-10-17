package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.jdbc.*;

public final class DaoFactory {
    private final static String DAO_TYPE = "JDBC";

    public static Dao getDao(DaoCategory daoCategory) {
        if (DAO_TYPE.equals("JDBC")) {
            switch (daoCategory){
                case CART:
                    return CartDaoJDBC.getInstance();
                case ORDER:
                    return OrderDaoJDBC.getInstance();
                case PRODUCT_CATEGORY:
                    return ProductCategoryDaoJDBC.getInstance();
                case PRODUCT:
                    return ProductDaoJDBC.getInstance();
                case SUPPLIER:
                    return SupplierDaoJDBC.getInstance();
                case USER:
                    return UserDaoJDBC.getInstance();
            }
        } /*else if (DAO_TYPE.equals("MEM")) {
            switch (daoCategory) {
                case CART:
                    break;
                case ORDER:
                    break;
                case PRODUCT_CATEGORY:
                    break;
                case PRODUCT:
                    break;
                case SUPPLIER:
                    break;
                case USER:
                    break;
            }
        }*/
        return null;
    }
}

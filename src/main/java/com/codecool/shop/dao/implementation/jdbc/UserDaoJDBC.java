package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBC extends DaoJDBC implements UserDao {
    private static UserDao instance = null;


    private UserDaoJDBC() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User find(int id) {
        try {
            @Cleanup Connection conn = this.getConnection();
            @Cleanup PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"user\" WHERE id=?");
            statement.setInt(1,id);
            @Cleanup ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = new User(resultSet.getString("name"));
            user.setId(id);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }
}

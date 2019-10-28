package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/add-to-cart"})
public class AddToCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDao = DaoFactory.getCartDao();
        UserDao userDao = DaoFactory.getUserDao();
        ProductDao productDao = DaoFactory.getProductDao();

        String JSONstring = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(JSONstring, JsonObject.class);
        int id = jsonObject.getAsJsonPrimitive("id").getAsInt();
        LineItem lineItem = cartDao.getLineItemByProductIdIfExists(id);
        if(lineItem != null) {
            String daoType = DaoFactory.getDaoType();
            if (daoType.equals("JDBC")){
                LineItemDao lineItemDao = DaoFactory.getLineItemDao();
                lineItemDao.increaseQuantity(lineItem);
            } else if (daoType.equals("MEM")){
                lineItem.increaseQuantity();
            }
        } else {
            cartDao.add(new LineItem(productDao.find(id), userDao.find(1)));
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("cartSize", cartDao
                .getAll()
                .stream()
                .mapToInt(LineItem::getQuantity)
                .sum());


        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonResponse.toString());
        out.flush();
    }
}

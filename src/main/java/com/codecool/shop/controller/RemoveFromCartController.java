package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.dao.ProductDao;
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

@WebServlet(urlPatterns = {"/remove-from-cart"})
public class RemoveFromCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDao = DaoFactory.getCartDao();
        ProductDao productDao = DaoFactory.getProductDao();

        String JSONstring = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(JSONstring, JsonObject.class);
        int id = jsonObject.getAsJsonPrimitive("id").getAsInt();
        LineItem lineItem = cartDao.getLineItemByProductIdIfExists(id);
        if(lineItem != null) {
            if(lineItem.getQuantity() > 1) {
                lineItem.decreaseQuantity();
            } else {
                lineItem.decreaseQuantity();
                cartDao.removeNullQuantityLineItems();
            }
        } else {
            cartDao.add(new LineItem(productDao.find(id)));
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


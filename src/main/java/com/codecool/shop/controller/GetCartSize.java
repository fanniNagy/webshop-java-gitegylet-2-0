package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.model.LineItem;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/get-cart-size"})
public class GetCartSize extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDao = DaoFactory.getCartDao();

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("cartSize",cartDao
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

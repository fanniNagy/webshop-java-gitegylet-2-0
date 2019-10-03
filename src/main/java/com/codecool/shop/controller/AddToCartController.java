package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import sun.security.util.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/add-to-cart"})
public class AddToCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String JSONstring = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(JSONstring, JsonObject.class);
        int id = jsonObject.getAsJsonPrimitive("id").getAsInt();
        LineItem lineItem = CartDaoMem.getInstance().getLineItemByProductIdIfExists(id);
        if(lineItem != null) {
            lineItem.increaseQuantity();
        } else {
            CartDaoMem.getInstance().add(new LineItem(ProductDaoMem.getInstance().find(id)));
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("cartSize", CartDaoMem.getInstance().getAll().stream().mapToInt(LineItem::getQuantity).sum());


        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonResponse.toString());
        out.flush();
    }
}

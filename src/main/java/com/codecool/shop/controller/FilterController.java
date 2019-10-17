package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/filter"})
public class FilterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filterBy = req.getParameter("filter-by");
        int filterValue = Integer.parseInt(req.getParameter("filter-value"));
        ArrayList<Product> products = new ArrayList<>();
        String header = "";

        if (filterBy.equals("category")) {
            products.addAll(ProductDaoMem.getInstance()
                    .getBy(ProductCategoryDaoMem.getInstance()
                        .find(filterValue)));

            header = products.get(0).getProductCategory().getName();
        } else if (filterBy.equals("supplier")) {
            products.addAll(ProductDaoMem.getInstance()
                    .getBy(SupplierDaoMem.getInstance()
                        .find(filterValue)));

            header = products.get(0).getSupplier().getName();
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", header);
        context.setVariable("products", products);
        engine.process("product/filter.html", context, resp.getWriter());
    }
}

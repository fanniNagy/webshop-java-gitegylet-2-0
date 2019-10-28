package com.codecool.shop.controller;

import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = DaoFactory.getProductDao();
        ProductCategoryDao productCategoryDao = DaoFactory.getProductCategoryDao();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductCategory productCategory = productCategoryDao.find(1);
        context.setVariable("category", productCategory);
        context.setVariable("products", productDao.getBy(productCategory));
        engine.process("product/index.html", context, resp.getWriter());
    }

}

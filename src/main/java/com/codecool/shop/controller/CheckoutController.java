package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/checkout"})

public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/checkout.html", context, resp.getWriter());
        String checkbox = req.getParameter("sameadresses");

        String[] billingAdress = new String[5];
        billingAdress[0] = req.getParameter("country");
        billingAdress[1] = req.getParameter("city");
        billingAdress[2] = req.getParameter("zipcode");
        billingAdress[3] = req.getParameter("street");
        billingAdress[4] = req.getParameter("building");

        String[] shippingAdress = new String[5];

        if (checkbox != null) {
            shippingAdress[0] = billingAdress[0];
            shippingAdress[1] = billingAdress[1];
            shippingAdress[2] = billingAdress[2];
            shippingAdress[3] = billingAdress[3];
            shippingAdress[4] = billingAdress[4];
        } else {
            shippingAdress[0] = req.getParameter("country");
            shippingAdress[1] = req.getParameter("city");
            shippingAdress[2] = req.getParameter("zipcode");
            shippingAdress[3] = req.getParameter("street");
            shippingAdress[4] = req.getParameter("building");
        }
    }

}

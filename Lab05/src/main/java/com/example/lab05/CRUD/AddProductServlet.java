package com.example.lab05.CRUD;

import com.example.lab05.DAO.ProductDAO;
import com.example.lab05.Model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addProduct", value = "/home/add-product")
public class AddProductServlet extends HttpServlet {
    private static ProductDAO productDAO = new ProductDAO();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Product p = (Product) productDAO.get(name);
        if(p != null) {
            resp.getWriter().write(createObjectJson(400, "Product already exist", new Product()));
        }
        else {
            Product newP = new Product(name, price);
            if(productDAO.add(newP)) {
                resp.getWriter().write(createObjectJson(200, "Add successful.", newP));
            }
            else
                resp.getWriter().write(createObjectJson(300, "Add fail.", new Product()));
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }


    public static String createObjectJson(int statusCode, String message, Product p) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("statusCode", statusCode);
        jsonResponse.addProperty("message", message);
        Gson gson = new Gson();
        JsonObject productJson = gson.toJsonTree(p).getAsJsonObject();
        jsonResponse.add("product", productJson);
        return jsonResponse.toString();
    }
}

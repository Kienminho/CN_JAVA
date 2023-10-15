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

@WebServlet(name = "deleteProduct", value = "/home/delete-product")
public class DeleteProductServlet extends HttpServlet {
    private static ProductDAO productDAO = new ProductDAO();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(productDAO.deleteById(id)) {
            resp.getWriter().write(createObjectJson(200, "Delete successful"));
        }
        else
            resp.getWriter().write(createObjectJson(300, "Delete fail"));

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public static String createObjectJson(int statusCode, String message) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("statusCode", statusCode);
        jsonResponse.addProperty("message", message);

        return jsonResponse.toString();
    }
}

package com.example.lab05;

import com.example.lab05.DAO.ProductDAO;
import com.example.lab05.Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "homeServlet", value = "/home")
public class HomeServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();
    @Override
    public void init() throws ServletException {

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    System.out.println("Cookie: "+cookie.getValue());
                    req.setAttribute("username", cookie.getValue());
                    break;
                }
            }
        }

        //hiển thị danh sách sản phẩm
        List<Product> list = productDAO.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

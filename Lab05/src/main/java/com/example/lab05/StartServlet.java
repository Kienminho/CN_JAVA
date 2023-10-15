package com.example.lab05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "startServlet", value = "")

public class StartServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    //chuyển hướng trang đến trang login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(23);

        resp.sendRedirect("/auth/login");
    }

    @Override
    public void destroy() {

    }
}

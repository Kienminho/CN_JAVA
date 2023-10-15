package com.example.lab05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/auth/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);

        resp.sendRedirect("/auth/login");
    }

    @Override
    public void destroy() {

    }
}

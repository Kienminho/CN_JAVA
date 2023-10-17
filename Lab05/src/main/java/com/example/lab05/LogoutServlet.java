package com.example.lab05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/auth/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // lấy session
        if(session != null) {
            session.invalidate();
        }
        Cookie[] cookies = req.getCookies(); //lấy cookies
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    Cookie c = new Cookie("username", "");
                    c.setMaxAge(0);
                    c.setPath("/");
                    resp.addCookie(c);

                }
            }
        }


        resp.sendRedirect("/auth/login");
    }

    @Override
    public void destroy() {

    }
}

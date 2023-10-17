package com.example.lab05;

import java.io.*;
import com.google.gson.*;


import com.example.lab05.DAO.UserDAO;
import com.example.lab05.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;

@WebServlet(name = "loginServlet", value = "/auth/login")
public class LoginServlet extends HttpServlet {
    private static int maxAgeCookie = 30 * 24 * 60 * 60;
    private static int statusCode = 0;
    private static String message = null;
    private static UserDAO userDAO = new UserDAO();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies(); //lấy cookies
        String savedUsername = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    savedUsername = cookie.getValue();
                    System.out.println(savedUsername);

                }
            }
        }
        if(savedUsername != null || session.getAttribute("username") != null) {
            System.out.println(35);
            //req.setAttribute("username", savedUsername);
            resp.sendRedirect("/home");
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean rememberMe = req.getParameter("saveUser") != null;
        System.out.println(username + " "+ password);
        User userExist = (User) userDAO.get(username);
        if(userExist != null) {
            statusCode =200;
            if(password.equals(userExist.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                // thời gian tồn tại của session là 30p
                session.setMaxInactiveInterval(30*60);
                if(rememberMe) {
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(maxAgeCookie);
                    cookie.setPath("/");
                    res.addCookie(cookie);
                }
                message = "/home";

            }
            else {
                statusCode =300;
                message = "Incorrect password";
            }
            res.getWriter().write(createObjectJson(statusCode, message));
            res.getWriter().flush();
        }
        else {
            statusCode = 400;
            message = "User does not exist, please register ";
            res.getWriter().write(createObjectJson(statusCode, message));
        }
    }

    public void destroy() {
    }

    public static String createObjectJson(int statusCode, String message) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("statusCode", statusCode);
        jsonResponse.addProperty("message", message);
        return jsonResponse.toString();
    }
}
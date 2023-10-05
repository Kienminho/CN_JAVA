package com.example.lab04;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/Login-Servlet")
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> userAccounts;

    public void init() {
        userAccounts = new HashMap<>();
        userAccounts.put("admin", "admin");
        userAccounts.put("Kien", "123456");
        userAccounts.put("Ngan", "hongngu");
    }


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isValid = validateLogin(username, password);

        if(isValid) {
            out.println("<html><body><h2>Name/Password match</h2></body></html>");
        }
        else {
            out.println("<html><body><h2>Name/Password does not match</h2></body></html>");
        }

    }

    private boolean validateLogin(String username, String password) {
        // Check if the provided username and password match any pre-built accounts
        return userAccounts.containsKey(username) && userAccounts.get(username).equals(password);
    }

    public void destroy() {
    }
}
package com.example.lab05;

import com.example.lab05.DAO.UserDAO;
import com.example.lab05.Model.User;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "registerServlet", value ="/auth/register")
public class RegisterServlet extends HttpServlet {
    private static UserDAO userDAO = new UserDAO();

    @Override
    public void init() throws ServletException {
        super.init();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("username") !=null) {
            resp.sendRedirect("/home");
            return;
        }
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        //kiểm tra user tồn tại hay không
        User userExist = (User) userDAO.get(username);
        if(userExist != null) {
            resp.getWriter().write(createObjectJson(404, "User already exists"));
            return;
        }

        User newUser = new User(username, email, password);
        if(userDAO.add(newUser)) {
            resp.getWriter().write(createObjectJson(200, "/auth/login"));
        }
        else
            resp.getWriter().write(createObjectJson(400, "Register fail"));




    }
    @Override
    public void destroy() {

    }

    public static String createObjectJson(int statusCode, String message) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("statusCode", statusCode);
        jsonResponse.addProperty("message", message);
        return jsonResponse.toString();
    }
}

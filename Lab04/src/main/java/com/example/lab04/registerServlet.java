package com.example.lab04;

import com.example.lab04.Model.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
     private static Map<String, Object> response = new HashMap<>();

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        out.println(name);
        String email = req.getParameter("email");
        out.println(email);
        String birthday = req.getParameter("birthday");

        String birthtime = req.getParameter("birthtime");

        String gender = req.getParameter("gender");

        String country = req.getParameter("country");

        String[] favoriteIDE = req.getParameterValues("favorite_ide[]");

        String toeic = req.getParameter("toeic");
        String message = req.getParameter("message");
        if(name ==null || email ==null|| birthday ==null||
                birthtime ==null||gender ==null|| country ==null||
                favoriteIDE.length<=0 || toeic ==null|| message ==null) {
            response.put("code", 400);
            response.put("message", "Register fail");
        }
        else {
            //tạo object user
            User user = new User(name, email, birthday, birthtime, gender, country, favoriteIDE, toeic, message);

            response.put("code", 200);
            response.put("message", "Register successful");
            response.put("user", user);
        }



        String json = new Gson().toJson(response);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    public void destroy() {

    }
}

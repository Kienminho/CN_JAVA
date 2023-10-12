package com.example.lab04.Bai5;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "homeServlet", value="/Bai5/*")
public class HomeServlet extends HttpServlet {
     private static String[] arrPage = {"contact", "help", "about"};

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageName = getPageName(req);
        String page = req.getParameter("page");
        if(pageName.isEmpty() && page != null) {
            req.getRequestDispatcher("/"+page+".jsp").forward(req, resp);
            return;
        }
        displayPage(req, resp, pageName,page);
    }

    public static String getPageName(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);
        return pageName;
    }

    public static void displayPage(HttpServletRequest req, HttpServletResponse resp, String pageName, String page) throws ServletException, IOException {

         if (pageName.equals("Bai5")&&(page==null || page.isEmpty())) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
            dispatcher.forward(req, resp);

        } else {

            RequestDispatcher dispatcher = req.getRequestDispatcher("/"+pageName);
            dispatcher.forward(req, resp);
        }
    }


    @Override
    public void destroy() {

    }




}

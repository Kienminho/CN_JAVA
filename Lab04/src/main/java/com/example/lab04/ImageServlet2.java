package com.example.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.*;

@WebServlet(name = "imageServlet2", value = "/image2")
public class ImageServlet2 extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Load an image file (you need to replace this path with your actual image path)
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream("Images/image_2.jpg");

        if (imageStream == null) {
            res.getWriter().println("Image not found");
            return;
        }

        // Set the content type to force download
        res.setContentType("application/octet-stream");

        // Set the content-disposition header to force download
        res.setHeader("Content-Disposition", "attachment; filename=image2.jpg");

        // Write the image to the response
        OutputStream outputStream = res.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = imageStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        imageStream.close();
        outputStream.close();
    }


    @Override
    public void destroy() {

    }
}

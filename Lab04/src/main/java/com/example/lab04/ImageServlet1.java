package com.example.lab04;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;


@WebServlet(name = "imageServlet1", value = "/image1")
public class ImageServlet1 extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load an image file (you need to replace this path with your actual image path)
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream("Images/image_1.jpg");

        if (imageStream == null) {
            response.getWriter().println("Image not found");
            return;
        }

        // Set the content type
        response.setContentType("image/jpeg");

        // Write the image to the response
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = imageStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        imageStream.close();
        outputStream.close();
    }
}

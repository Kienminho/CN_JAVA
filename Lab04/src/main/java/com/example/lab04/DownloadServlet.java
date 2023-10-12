package com.example.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet(name = "downloadServlet", value = "/download")

public class DownloadServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("file");

        //kiểm tra file
        if(fileName ==null || fileName.isEmpty()) {
            resp.getWriter().write("File not found");
            return;
        }
        String applicationPath = getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + "uploads";
        //create a input stream
        InputStream fileStream = new FileInputStream(uploadFilePath +"/"+ fileName);
        System.out.println(fileStream);
        if (fileStream == null) {
            System.out.println(32);

            resp.getWriter().println("File not found");
            return;
        }

        //Speed download
        int speed = getDownloadSpeed(req);

        // Set content type and headers
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Set download speed limit (if specified)
        if (speed > 0) {
            resp.setHeader("X-Throttle", Integer.toString(speed));
        }

        //download file
        OutputStream outputStream = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        fileStream.close();
        outputStream.close();


    }


    private int getDownloadSpeed(HttpServletRequest req) {
        // Get speed parameter from query string, default to 0 if not specified
        String speedParam = req.getParameter("speed");
        return speedParam != null ? Integer.parseInt(speedParam) : 0;
    }


    @Override
    public void destroy() {

    }
}

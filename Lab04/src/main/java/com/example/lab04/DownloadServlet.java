package com.example.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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


        //check file on server
        String filePath = getServletContext().getRealPath("/files/") + File.separator+fileName;

        File file = new File(filePath);

        if(!file.exists()) {
            resp.getWriter().write("File not found on the server");
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

        try (FileInputStream fileInputStream = new FileInputStream(file);
             OutputStream outputStream = resp.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);

                if (speed > 0) {
                    Thread.sleep(1000 / speed);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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

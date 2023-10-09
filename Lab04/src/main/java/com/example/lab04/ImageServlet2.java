package com.example.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "imageServlet2", value = "/image2")
public class ImageServlet2 extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Đường dẫn tới tập tin hình ảnh trên máy chủ
        ServletContext context = getServletContext();
        String webAppPath = context.getRealPath("/");

        // Xây dựng đường dẫn đến thư mục Images
        String imagePath = webAppPath + "Images" + File.separator + "image_2.jpg";


        // Lấy tên tập tin từ đường dẫn hình ảnh
        String fileName = new File(imagePath).getName();

        // Thiết lập các header để yêu cầu trình duyệt tải xuống thay vì hiển thị
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        res.setHeader("Content-Type", "application/octet-stream");
        res.setHeader("Content-Transfer-Encoding", "binary");

        // Đọc dữ liệu từ tập tin và gửi nó đến client
        try (FileInputStream fileInputStream = new FileInputStream(imagePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                res.getOutputStream().write(buffer, 0, bytesRead);
            }
        }
    }

    @Override
    public void destroy() {

    }
}

package com.example.lab04;

import com.example.lab04.Model.Product;
import static com.example.lab04.Model.Product.findProductById;


import com.google.gson.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.net.URLDecoder;



@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    static Gson gson = new Gson();
    private static List<Product> products = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        products.add(new Product("1", "Iphone 11", 400));
        products.add(new Product("2", "Xiaomi mi12 s", 600));
        products.add(new Product("3", "Samsung zflip5", 900));
        products.add(new Product("4", "Ipad gen 9 ", 450));
    }


    // get all product or get product by id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> result = new ArrayList<>();
        String id = req.getParameter("id");
        resp.setContentType("application/json");
        if(id != null ) {

            Product p2 = findProductById(products, id);
            if(p2 != null) {

                result.add(p2);

                resp.getWriter().write(createObjectJson(200, "Đã tìm thấy sản phẩm", result));
            }
            else {
                resp.getWriter().write(createObjectJson(404, "Không tìm thấy sản phẩm", result));
            }
        }
        else  {
            resp.getWriter().write(createObjectJson(200, "Đọc danh sách sản phẩm thành công", products));
        }
    }

    // add product

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");

    // nếu id chưa tồn tại thì sẽ thêm vào
        if (validId(id)) {
            products.add(new Product(id, name, Integer.parseInt(price)));
            resp.getWriter().write(createObjectJson(200, "Thêm sản phẩm thành công", new ArrayList<>()));
        }
        else
            resp.getWriter().write(createObjectJson(300, ("Đã tồn tại sản phẩm có id = "+id), new ArrayList<>()));

    }

    //update product

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ biểu mẫu
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        // Giải mã dữ liệu
        String jsonData = URLDecoder.decode(stringBuilder.toString(), "UTF-8");

        // Tiếp theo, bạn có thể thực hiện xử lý dữ liệu, chẳng hạn như chuyển đổi JSON thành đối tượng Java
        Gson gson = new Gson();
        Product updatedProduct = gson.fromJson(jsonData, Product.class);
        if (!validUpdate(updatedProduct)) {
            resp.getWriter().write(createObjectJson(300, ("Thông tin update cần phải có id, name và price"), new ArrayList<>()));
        }
        else {
            Product p  = findProductById(products, updatedProduct.getId());

            if(p != null) {
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());
                resp.getWriter().write(createObjectJson(200, ("Cập nhật sản phẩm thành công"), new ArrayList<>()));
            }
            else {
                resp.getWriter().write(createObjectJson(404, ("Không có sản phẩm có id = "+updatedProduct.getId()), new ArrayList<>()));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(deleteProduct(products, id))
            resp.getWriter().write(createObjectJson(200, ("Xoá sản phẩm thành công"), new ArrayList<>()));
        else
            resp.getWriter().write(createObjectJson(404, ("Không tìm thấy sản phâ có id = "+id), new ArrayList<>()));

    }

    private static boolean validId(String id) {
        for (Product p: products) {
            if(p.getId().equals(id))
                return false;
        }
        return true;
    }

    private static boolean validUpdate(Product updatedProduct) {
        if (updatedProduct.getId() == null || updatedProduct.getName() == null || updatedProduct.getPrice() ==0) {
            return false;
        }
        return true;
    }

    private static boolean deleteProduct(List<Product> listProduct, String id) {
        for (Product p: products) {
            if(p.getId().equals(id)){
                products.remove(p);
                return true;
            }
        }
        return false;
    }



    private static String createObjectJson(int code, String message, List<Product> data) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("codeError", code);
        jsonResponse.addProperty("message", message);
        if(!data.isEmpty()) {
            JsonArray arr = new JsonArray();
            for (Object item : data) {
                arr.add(gson.toJsonTree(item));
            }
            jsonResponse.add("data", arr);
        }



        return jsonResponse.toString();
    }
}
